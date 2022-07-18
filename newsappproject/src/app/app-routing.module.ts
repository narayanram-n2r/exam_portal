import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { ArticlesComponent } from './components/articles/articles.component';
import { CategoryComponent } from './components/category/category.component';
import { ContactComponent } from './components/contact/contact.component';
import { ErrorPageComponent } from './components/error-page/error-page.component';
import { HomeContentComponent } from './components/home-content/home-content.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { FavouritesComponent } from './components/favourites/favourites.component';
import { AuthGuard } from './guard/auth.guard';
import { NewsCategoryComponent } from './components/news-category/news-category.component';
import { ProfileEditComponent } from './components/profile-edit/profile-edit.component';
import { SearchComponent } from './components/search/search.component';

const routes: Routes = [
  {
    path:'',
    component:HomeComponent,
    children: [
      {path:'',redirectTo:"articles", pathMatch:"full"},
      // {path:'', component: HomeContentComponent},
      // {path:'about', component:AboutComponent},
      // {path:'contact', component:ContactComponent},
      {path:'login', component: LoginComponent},
      {path:'signup', component: SignupComponent},
      {path:'articles', component: ArticlesComponent},
      {path:'search', component: SearchComponent},
      {path:'edit', component: ProfileEditComponent, canActivate: [AuthGuard]},
      {path:'category', component: CategoryComponent,
        children: [
          {path:'', component:ArticlesComponent},
          {path:'newsCategory/:id', component:NewsCategoryComponent}]
      },
      {path:'favourite', canActivate: [AuthGuard], component: FavouritesComponent}
      
    ]
  },
  {
    path        : '**',
    pathMatch   : 'full',
    component   : ErrorPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
