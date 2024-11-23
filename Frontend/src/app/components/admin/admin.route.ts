import {Routes} from '@angular/router';
import {HomeAdminComponent} from './home/home-admin.component';


import {VolunteerComponent} from './volunteer/volunteer.component';


export  const adminRoutes: Routes = [
  { path: 'volunteerRequests', component: HomeAdminComponent },
  {path: 'volunteers', component:VolunteerComponent },

  // {path:"products" , component:ProductsComponent},

];
