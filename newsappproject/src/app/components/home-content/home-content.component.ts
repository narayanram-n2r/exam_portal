import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { LoginService } from 'src/app/services/login.service';
import { NewsService } from 'src/app/services/news.service';

@Component({
  selector: 'app-home-content',
  templateUrl: './home-content.component.html',
  styleUrls: ['./home-content.component.css']
})
export class HomeContentComponent implements OnInit {

  public newsArticles: Array<any> = [];
  constructor(private newsService: NewsService,
    private router: Router,
    private loginService: LoginService,
    private category: CategoryService) {
      if(this.loginService.isLoggedIn()){
        this.router.navigate(["articles"]);
      }
  }
  searchText = '';

  ngOnInit(): void {
    this.newsService.getHeadlines().subscribe((data:any)=>{
      console.log(data.articles);
      this.newsArticles = data.articles;
    })
  }
  categoryNews = this.category.categoryNews;
}
