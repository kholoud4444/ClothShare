import { Routes } from '@angular/router';
import {RegisterComponent} from './components/auth/register/register.component';
import {LoginComponent} from './components/auth/login/login.component';
import {AboutUsComponent} from './components/pages/about-us/about-us.component';
import {ItemComponent} from './components/commen/item/item.component';
import {ProductsComponent} from './components/commen/products/products.component';
import {ContactUsComponent} from './components/pages/contact-us/contact-us.component';
import {NeedyHistoryComponent} from './components/needy/needy-history/needy-history.component';
import {PageNotFoundComponent} from './components/commen/page-not-found/page-not-found.component';
import {VolunteerHistoryComponent} from './components/volunteer/volunteer-history/volunteer-history.component';
import {AddProductComponent} from './components/volunteer/add-product/add-product.component';
import {HomeAdminComponent} from './components/admin/home/home-admin.component';
import {AdminGuard} from './components/gaurds/admin.guard';
import {VolunteerGuard} from './components/gaurds/volunteer.guard';
import {NeedyGuard} from './components/gaurds/needy.guard';
import {HomeComponent} from './components/pages/home.component';

export const routes: Routes = [
  {path: "", redirectTo:"home", pathMatch:"full"},
  {path: "home", component:HomeComponent},
  {path: "register", component:RegisterComponent},
  {path: "login", component:LoginComponent},
  {path: "products", component:ProductsComponent},
  { path: 'addproduct', component: AddProductComponent },

  {
    path: 'admin',
    loadChildren: () => import('./components/admin/admin.route').then(m => m.adminRoutes),
    canActivate: [AdminGuard],
    data: { roles: ['ADMIN'] },
  },

  {
    path: 'volunteer',
    loadChildren: () => import('./components/volunteer/volunteer.route').then(m => m.volunteerRoutes),
    canActivate: [VolunteerGuard],
    data: { roles: ['volunteer'] },
  },

  {
    path: 'needy',
    loadChildren: () => import('./components/needy/needy.route').then(m => m.needyRoutes),
    canActivate: [NeedyGuard],
    data: { roles: ['needy'] },
  },

  {path:'**', component:PageNotFoundComponent},

];
