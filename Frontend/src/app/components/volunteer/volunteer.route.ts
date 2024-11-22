import {Routes} from '@angular/router';
import {AddProductComponent} from './add-product/add-product.component';
 import {VolunteerHistoryComponent} from './volunteer-history/volunteer-history.component';
import {ProductsComponent} from '../Shared/products/products.component';
import {ItemComponent} from '../Shared/item/item.component';

export const volunteerRoutes: Routes = [
  { path: '', component: VolunteerHistoryComponent },
  { path: 'addProduct', component: AddProductComponent },
  {path:"products" , component:ProductsComponent},
  {path:"item/:id" , component:ItemComponent}
];
