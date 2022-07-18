import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  public newsArticles: Array<any> = [];
  public loggedIn = false;
  public searchList: Array<any> = [];
  public categoryName: string = "";
  public val: string = "";
  constructor(private newsService: NewsService,
    private router: Router,
    private _snackBar: MatSnackBar,
    private loginService: LoginService) { }

  ngOnInit(): void {
    this.categoryName = "Top HeadLines";
    this.newsService.getHeadlines().subscribe((data:any)=>{
      console.log(data.articles);
      this.newsArticles = data.articles;
    })
  }

  public search(value: string){
    console.log(value);
    this.val = value.trim();
    if(this.val.length>0){
      this.val = this.val.replace(' ','-');
      console.log(this.val);
      if(this.val.includes('-')){
        console.log(this.val);
        this.categoryName = value.toUpperCase();
        this.newsService.searchNews(this.val.toLowerCase()).subscribe((data:any)=>{
          console.log(data.articles);
          this.newsArticles = data.articles;
        })
      }  
      else{
        this.categoryName = value.toUpperCase();
        this.newsService.searchNewsFromLocalRepo(this.val.toLowerCase()).subscribe((data:any)=>{
          console.log(data.articles);
          this.newsArticles = data.articles;
        })
      }
        console.log(this.newsArticles.length);
        console.log(this.newsArticles);          
    }
  }

  openSnackBar(message: string) {
    this._snackBar.open(message,"Close",{duration: 3000});
  }

  addToFavourite(article:any){
    if(this.loginService.isLoggedIn()){
      this.loginService.addArticle(article)
      .subscribe({
        next:(res)=>{
          this.openSnackBar('Article has been added to favourites successfully!!')
        },
        error:()=>{
          this.openSnackBar("Error while adding to favourites")
        }
      })
    }
    else{
      this.router.navigate(["login"]);
    }
  }
}
