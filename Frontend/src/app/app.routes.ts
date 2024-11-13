import { Routes } from '@angular/router';
import {RegisterComponent} from './components/register/register.component';
import {LoginComponent} from './components/login/login.component';
import {AboutUsComponent} from './components/about-us/about-us.component';
import {ItemComponent} from './components/item/item.component';
import {ProductsComponent} from './components/products/products.component';
import {PagesComponent} from './components/pages/pages.component';
import {ContactUsComponent} from './components/contact-us/contact-us.component';
import {HomeComponent} from './components/pages/home/home.component';
import {NeedyHistoryComponent} from './components/needy-history/needy-history.component';
import {PageNotFoundComponent} from './components/page-not-found/page-not-found.component';
import {VolunteerHistoryComponent} from './components/volunteer-history/volunteer-history.component';
import {AddProductComponent} from './components/add-product/add-product.component';
import {HomeAdminComponent} from './components/admin/home/home-admin.component';

export const routes: Routes = [
  {path: "login", component:LoginComponent},
  {path:"",redirectTo:"login",pathMatch:"full"},
  {path: "register" , component:RegisterComponent},
  {path: "about" , component:AboutUsComponent},
  {path:"contactUs" , component:ContactUsComponent},
  // {path:"item" , component:ItemComponent},
  {path:"item/:id" , component:ItemComponent},
  {path:"products" , component:ProductsComponent},
  {path:"addProduct" , component:AddProductComponent},
  {path:"needy-history", component:NeedyHistoryComponent},
  {path:'admin', component:HomeAdminComponent},
  {path:"volunteerHistory" , component:VolunteerHistoryComponent},

  {path:'page', component:PagesComponent,
    children:[
      {path:'home', component:HomeComponent},
      {path:'contact' , component:ContactUsComponent}
    ]},

  {path:'**', component:PageNotFoundComponent},

];
