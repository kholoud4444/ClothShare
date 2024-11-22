import {Routes} from '@angular/router';
import {HomeAdminComponent} from './home/home-admin.component';
import {ItemComponent} from '../Shared/item/item.component';
import {ProductsComponent} from '../Shared/products/products.component';
import {VolunteerComponent} from './volunteer/volunteer.component';


export  const adminRoutes: Routes = [
  { path: 'volunteerRequests', component: HomeAdminComponent },
  {path: 'volunteers', component:VolunteerComponent },
  {path:"item/:id" , component:ItemComponent},
  {path:"products" , component:ProductsComponent},

 // {path:"needy" , component:NeedyHistoryComponent},
];
