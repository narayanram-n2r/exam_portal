import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public loggedIn = false;
  constructor(private loginService: LoginService) { 
    this.loggedIn = this.loginService.isLoggedIn();
  }

  ngOnInit(): void {
    this.loggedIn = this.loginService.isLoggedIn();
  }

  logout(){
    this.loginService.logout();
    this.loggedIn = this.loginService.isLoggedIn();
  }

}
