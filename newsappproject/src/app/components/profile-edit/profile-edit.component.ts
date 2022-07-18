import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-profile-edit',
  templateUrl: './profile-edit.component.html',
  styleUrls: ['./profile-edit.component.css']
})
export class ProfileEditComponent implements OnInit {

  public signupForm!: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private _snackBar: MatSnackBar, 
   // private http: HttpClient,
     private router: Router,
      private loginService: LoginService) { }

  ngOnInit(): void {
    this.signupForm = this.formBuilder.group({
      firstName:['',Validators.required],
      lastName:['',Validators.required],
      city:[''],
      mobile:[''],
      // email:['',Validators.compose([Validators.required,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')])],
      password:['',Validators.compose([Validators.required,
        Validators.minLength(8)
        // Validators.pattern('(?=^.{8,}$)(?=.*\d)(?=.*[!@#$%^&*]+)(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$')
      ])]
    });
  }

  update(){
    this.loginService.updateUser(this.signupForm.value)
    .subscribe( response=>{
        console.log(response);
        console.log(this.signupForm.value);
        this.openSnackBar("Update Successfull");
        this.signupForm.reset();
        this.loginService.logout();
        window.location.reload();
        this.router.navigate(["login"]);
      },
      (error)=>{
        console.log(error);
        console.log(this.signupForm.value);
        this.openSnackBar("Something went wrong")
      }
  )
  }
  openSnackBar(message: string) {
    this._snackBar.open(message,"Close",{duration: 3000});
  }

}
