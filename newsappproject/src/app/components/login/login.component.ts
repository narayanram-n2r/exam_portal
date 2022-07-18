import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Route, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

export const TOKEN_Name = 'token';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm!: FormGroup;
  constructor(private formBuilder: FormBuilder, 
    private _snackBar: MatSnackBar,
     private loginService: LoginService,
     private router: Router) { 
      if(this.loginService.isLoggedIn()){
        this.router.navigate(["articles"]);
      }
    }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      email:['',Validators.compose([Validators.required,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')])],
      password:['',Validators.compose([Validators.required,
        Validators.minLength(8)
        //Validators.pattern('/(?=^.{8,}$)(?=.*\d)(?=.*[!@#$%^&*]+)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$/')
      ])]
    })
  }

  openSnackBar(message: string) {
    this._snackBar.open(message,"Close",{duration: 3000});
  }

  cdata: any;

  onSubmit(){
    this.loginService.generateToken(this.loginForm.value)
    .subscribe(data =>{
      this.cdata = data;
        console.log(this.loginForm.value);
        localStorage.setItem(TOKEN_Name, this.cdata['token']);
        localStorage.setItem("username",this.loginForm.value.emailId);
        console.log("Response Received");
        this.loginService.loginUser(this.loginForm.value);
        this.openSnackBar("Login Successfull");
        this.router.navigate(["articles"]). then(() => {
          window. location. reload();
        });
      },
      (error)=>{
        console.log(this.loginForm.value);
        console.log(error);
        this.openSnackBar("User not found. Please register youself first!!")
      }
    )
  }
}
