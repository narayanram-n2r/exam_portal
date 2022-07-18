import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, retry, Observable, throwError } from 'rxjs';

const USER_NAME = "";

@Injectable({
  providedIn: 'root'
})

export class LoginService {

  //http://localhost:9595
  url="http://localhost:8080"
  constructor(private http: HttpClient) { }

  username: any;
  category: any;
  //calling server to generate token
  public generateToken(credentials: any):Observable<any>{
    return this.http.post<any>("http://localhost:8080/login", credentials); //.pipe(retry(1), catchError(this.handleError));
  }

  public register(user: any):Observable<any>{
    return this.http.post<any>("http://localhost:8080/signup", user); //.pipe(retry(1), catchError(this.handleError));
  }

  public updateUser(user: any):Observable<any>{
    console.log(localStorage.getItem(USER_NAME));
    this.email = localStorage.getItem(USER_NAME);
    return this.http.post<any>(`http://localhost:8080/update/${this.email}`, user); //.pipe(retry(1), catchError(this.handleError));
  }
  //to login user
  loginUser(user: any){ 
    let x = localStorage.getItem("username");
    localStorage.setItem(USER_NAME, user.email);
    return this.http.post("http://localhost:8080/login", user);

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

  //to check if user is logged in or not
  isLoggedIn(){
    let token = localStorage.getItem(USER_NAME);
    if(token==undefined || token==='' || token==null){
      return false;
    }else{
      return true;
    }
  }

  //for logging out
  logout(){
    localStorage.removeItem('token');
    sessionStorage.clear();
    localStorage.removeItem('token');
    localStorage.clear();
    return this.http.get<any>(`${this.url}/logout`).pipe(retry(1), catchError(this.handleError));
  }

  //for getting the token
  getToken(){
    return localStorage.getItem('token')
  }

  email: any;

  addArticle(article: any):Observable<any> {
    console.log("Calling addArticleToFavourite from service class");
    this.email = localStorage.getItem(USER_NAME);
    console.log(article);
    return this.http.post<any>(`http://localhost:8081/${this.email}/article`, article);

  }

  deleteArticleFromFavourite(id:any){
    // this.ccovidservice.de
    console.log("Calling deletePlayerfromFavouritelist from service class");
    this.username=localStorage.getItem(USER_NAME);
    const url = `http://localhost:8081/${this.username}/${id}`;
    console.log("delete url:-"+url);
    return this.http.delete(url)
  }

  getArticleList(): Observable<any> {
    console.log("getFavourite from service class");
    this.email = localStorage.getItem(USER_NAME);
    console.log(this.email);
    const url = `http://localhost:8081/fav/${this.email}`;
    return this.http.get<any>(url);
  }
}