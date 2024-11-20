import {Routes} from '@angular/router';
import {HomeAdminComponent} from './home/home-admin.component';
import {ItemComponent} from '../commen/item/item.component';
import {ProductsComponent} from '../commen/products/products.component';

export  const adminRoutes: Routes = [
  { path: '', component: HomeAdminComponent },
  {path:"item/:id" , component:ItemComponent},
  {path:"products" , component:ProductsComponent}
];
