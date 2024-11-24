import {Routes} from '@angular/router';
import {HomeAdminComponent} from './home/home-admin.component';


import {VolunteerComponent} from './volunteer/volunteer.component';
import {NeedyComponent} from './needy/needy.component';
import {ProductsComponent} from '../Shared/products/products.component';
import {ItemComponent} from '../Shared/item/item.component';


export  const adminRoutes: Routes = [
  { path: '', component: HomeAdminComponent },
  {path: 'volunteers', component:VolunteerComponent },
  {path: 'needy', component:NeedyComponent },
  {path:"products" , component:ProductsComponent},
  {path:"item/:id" , component:ItemComponent},

];
