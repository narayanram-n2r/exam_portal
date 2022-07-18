import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

const URL = `https://newsapi.org/v2/top-headlines?country=us&apiKey=${environment.apikey}`;
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor(private http: HttpClient) { }
  getHeadlines(){
    return this.http.get(URL)
    .pipe(catchError(this.handleError));  //catchError is used to handle error
  }
  getCategoryNews(url:any){
    return this.http.get(url)
    .pipe(catchError(this.handleError)); 
  }

  searchNewsFromLocalRepo(searchKey: string, page: number = 1){
    if (searchKey.length > 0) {
      const url=`https://newsapi.org/v2/top-headlines?q=${searchKey}&apiKey=${environment.apikey}`;
      return this.http.get(url)
      .pipe(catchError(this.handleError));
    }
    return this.http.get(URL)
    .pipe(catchError(this.handleError));
  }
  searchNews(searchKey: string, page: number = 1){
    if (searchKey.length > 0) {
      const url=`https://newsapi.org/v2/top-headlines?sources=${searchKey}&apiKey=${environment.apikey}`;
      return this.http.get(url)
      .pipe(catchError(this.handleError));
    }
    return this.http.get(URL)
    .pipe(catchError(this.handleError));
  }

  getNews(newsName: any){
    return this.http.get(`https://newsapi.org/v2/everything?source=name&apiKey=${environment.apikey}/`);
  }
  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `,
        error.error
      );
    }
    // Return an observable with a user-facing error message.
    return throwError(
      () => new Error('Something bad happened; please try again later.')
    );
  }
  
  
}
