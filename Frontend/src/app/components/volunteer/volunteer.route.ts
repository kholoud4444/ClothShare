import {Routes} from '@angular/router';
import {AddProductComponent} from './add-product/add-product.component';
import {VolunteerItemsDetailsComponent} from './volunteer-items-details/volunteer-items-details.component';
import {ProductsComponent} from '../Shared/products/products.component';
import {ItemComponent} from '../Shared/item/item.component';
import {
  GetItemRequestWithNeedyDetailsComponent
} from './get-item-request-with-needy-details/get-item-request-with-needy-details.component';
import {ProfileComponent} from '../Shared/profile/profile.component';



export const volunteerRoutes: Routes = [
  { path: '', component: VolunteerItemsDetailsComponent },
   { path: 'addProduct', component: AddProductComponent },
   {path:"products" , component:ProductsComponent},
  {path:"item/:id" , component:ItemComponent},
  {path:"request/:id" , component:GetItemRequestWithNeedyDetailsComponent},
  {path:"profile" , component:ProfileComponent},


];
