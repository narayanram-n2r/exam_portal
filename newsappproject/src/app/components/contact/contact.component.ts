import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  constructor(private loginService: LoginService,
    private router: Router) { 
    if(this.loginService.isLoggedIn()){
      this.router.navigate(["articles"]);
    }
  }

  ngOnInit(): void {
  }

}
