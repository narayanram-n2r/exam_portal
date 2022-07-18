import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { LoginService } from 'src/app/services/login.service';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  public newsArticles: Array<any> = [];
  public loggedIn = false;
  public categoryName: string = "";
  constructor(
    private newsService: NewsService,
    private category: CategoryService,
    private router: Router,
    private _snackBar: MatSnackBar,
    private loginService: LoginService
  ) {}

  ngOnInit(): void {
    this.categoryName = "Top HeadLines";
    this.newsService.getHeadlines().subscribe((data: any) => {
      console.log(data.articles);
      this.newsArticles = data.articles;
    });
  }
  categoryNews = this.category.categoryNews;
  changeCategory(cat: any) {
    this.categoryName = this.category.categoryNews[cat].cat;
    let url = this.category.categoryNews[cat].url;
    console.log(url);
    this.newsService.getCategoryNews(url).subscribe((data: any) => {
      console.log(data.articles);
      this.newsArticles = data.articles;
    });
  }

  openSnackBar(message: string) {
    this._snackBar.open(message, 'Close', { duration: 3000 });
  }

  addToFavourite(article: any) {
    if (this.loginService.isLoggedIn()) {
      this.loginService.addArticle(article).subscribe({
        next: (res) => {
          this.openSnackBar(
            'Article has been added to favourites successfully!!'
          );
        },
        error: () => {
          this.openSnackBar('Error while adding to favourites');
        },
      });
    } else {
      this.router.navigate(['login']);
    }
  }
}
