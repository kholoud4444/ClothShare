import { Routes } from '@angular/router';
import {RegisterComponent} from './components/auth/register/register.component';
import {LoginComponent} from './components/auth/login/login.component';
import {HomeComponent} from './components/pages/home.component';
import {AdminGuard} from './gaurds/admin.guard';
import {NeedyGuard} from './gaurds/needy.guard';
import {VolunteerGuard} from './gaurds/volunteer.guard';
import {PageNotFoundComponent} from './components/Shared/page-not-found/page-not-found.component';
import {ContactUsComponent} from './components/Shared/contact-us/contact-us.component';
import {VerifyEmailComponent} from './components/auth/verify-email/verify-email.component';
import {ForgetPasswordComponent} from './components/auth/forget-password/forget-password.component';
import {VerifyResetCodeComponent} from './components/auth/verify-reset-code/verify-reset-code.component';
import {NewPasswordComponent} from './components/auth/new-password/new-password.component';

export const routes: Routes = [
  {path: "login", component:LoginComponent},
  {path:"",redirectTo:"/home",pathMatch:"full"},
  {path: "register" , component:RegisterComponent},
  {path: "home" , component:HomeComponent},
  {path:"contactus" , component:ContactUsComponent},
  {path:"verifyEmail/:email",component:VerifyEmailComponent},
  {path:"forgetPassword", component:ForgetPasswordComponent},
  {path:"verifyNewPassword", component:VerifyResetCodeComponent},
  {path:"NewPassword", component:NewPasswordComponent},


  {
    path: 'admin',
    loadChildren: () => import('./components/admin/admin.route').then(m => m.adminRoutes),
    canActivate: [AdminGuard],
    data: { roles: ['ADMIN'] },
  },
  {
    path: 'needy',
    loadChildren: () => import('./components/needy/needy.route').then(m => m.needyRoutes),
    canActivate: [NeedyGuard],
    data: { roles: ['needy'] },
  },
  {
    path: 'volunteer',
    loadChildren: () => import('./components/volunteer/volunteer.route').then(m => m.volunteerRoutes),
    canActivate: [VolunteerGuard],
    data: { roles: ['volunteer'] },
  },


  {path:'**', component:PageNotFoundComponent},

];
