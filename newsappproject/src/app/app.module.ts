import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from'@angular/material/snack-bar';
import { MatCardModule } from'@angular/material/card';
import { FormsModule, ReactiveFormsModule } from'@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { HttpClientModule } from '@angular/common/http';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { AboutComponent } from './components/about/about.component';
import { FooterComponent } from './components/footer/footer.component';
import { ContactComponent } from './components/contact/contact.component';
import { HomeContentComponent } from './components/home-content/home-content.component';
import { CategoryComponent } from './components/category/category.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { NewsCategoryComponent } from './components/news-category/news-category.component';
import { SearchComponent } from './components/search/search.component';
import { ProfileEditComponent } from './components/profile-edit/profile-edit.component';
import { MatChipsModule } from '@angular/material/chips';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    SignupComponent,
    FavouritesComponent,
    ArticlesComponent,
    AboutComponent,
    FooterComponent,
    ContactComponent,
    HomeContentComponent,
    CategoryComponent,
    ErrorPageComponent,
    NewsCategoryComponent,
    SearchComponent,
    ProfileEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatChipsModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatSnackBarModule,
    MatCardModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
