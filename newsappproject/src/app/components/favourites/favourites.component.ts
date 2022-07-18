import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-favourites',
  templateUrl: './favourites.component.html',
  styleUrls: ['./favourites.component.css']
})
export class FavouritesComponent implements OnInit {

  public newsArticles: Array<any> = [];
  constructor(
    private favService: LoginService,
    private _snackBar: MatSnackBar) {
  }
  searchText = '';

  ngOnInit(): void {
    this.favService.getArticleList().subscribe((data:any)=>{
      console.log(data);
      this.newsArticles = data;
    })
  }

  openSnackBar(message: string) {
    this._snackBar.open(message,"Close",{duration: 3000});
  }

  deleteFromFavourite(article:any){
    console.log(article);
    this.favService.deleteArticleFromFavourite(article.articleId)
    .subscribe(
      (res: any)=>{
        window.location.reload();
        this.openSnackBar('Article has been removed from favourites successfully!!')
      },
      ()=>{
        this.openSnackBar("Error while removing article from favourites")
    }) 
  }
}

