import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { LoginService } from 'src/app/services/login.service';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-news-category',
  templateUrl: './news-category.component.html',
  styleUrls: ['./news-category.component.css']
})
export class NewsCategoryComponent implements OnInit {

  public newsArticles: Array<any> = [];
  public loggedIn = false;
  id: any;
  constructor(private category: CategoryService,
    private newsService: NewsService,
    private router: Router,
    private _snackBar: MatSnackBar,
    private route: ActivatedRoute,
    private loginService: LoginService) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(
      params => {
        this.id =  params['id'];
      }
    )
    let url = this.category.categoryNews[this.id]['url'];
    this.newsService.getCategoryNews(url).subscribe((data:any)=>{
      console.log(data.articles);
      this.newsArticles = data.articles;
    })
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
