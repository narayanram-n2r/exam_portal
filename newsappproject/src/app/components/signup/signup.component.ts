import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public signupForm!: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar,
     private router: Router,
      private loginService: LoginService
    ) { 
      if(this.loginService.isLoggedIn()){
        this.router.navigate(["articles"]);
      }
    }

  openSnackBar(message: string) {
    this._snackBar.open(message,"Close",{duration: 3000});
  }
  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      firstName:['',Validators.compose([Validators.required,
        Validators.minLength(4)])],
      lastName:['', Validators.compose([Validators.required, Validators.minLength(4)])],
      city:[''],
      mobile:['',Validators.pattern('^((\\+91-?)|0)?[0-9]{10}$')],
      email:['',Validators.compose([Validators.required,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')])],
      password:['',Validators.compose([Validators.required,
        Validators.minLength(8)
      ])]
    });
  }
  signup(){
    this.loginService.register(this.signupForm.value)
    .subscribe( response=>{
        console.log(response);
        console.log(this.signupForm.value);
        this.openSnackBar("Registration Successfull");
        this.signupForm.reset();
        this.router.navigate(["login"]);
      },
      (error)=>{
        console.log(error);
        console.log(this.signupForm.value);
        this.openSnackBar("Something went wrong")
      }
  )
  }

}
