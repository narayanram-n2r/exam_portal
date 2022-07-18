import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { NewsService } from 'src/app/services/news.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.css']
})
export class ArticlesComponent implements OnInit {

  public newsArticles: Array<any> = [];
  public loggedIn = false;
  public categoryName: string = "";
  
  constructor(private newsService: NewsService,
    private router: Router,
    private category: CategoryService,
    private _snackBar: MatSnackBar,
    private loginService: LoginService) {
  }
  searchText = '';

  ngOnInit(): void {
    this.categoryName = "Top HeadLines";
    this.newsService.getHeadlines().subscribe((data:any)=>{
      console.log(data.articles);
      this.newsArticles = data.articles;
    })
  }
  categoryNews = this.category.categoryNews;

  openSnackBar(message: string) {
    this._snackBar.open(message,"Close",{duration: 3000});
  }
  changeCategory(cat: any) {
    console.log();
    this.categoryName = this.category.categoryNews[cat].cat;
    let url = this.category.categoryNews[cat].url;
    console.log(url);
    this.newsService.getCategoryNews(url).subscribe((data: any) => {
      console.log(data.articles);
      this.newsArticles = data.articles;
    });
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
