import {Routes} from '@angular/router';
import {NeedyHistoryComponent} from './needy-history/needy-history.component';
import {ProductsComponent} from '../Shared/products/products.component';
import {ItemComponent} from '../Shared/item/item.component';
import {ProfileComponent} from '../Shared/profile/profile.component';

 export const needyRoutes: Routes = [
   { path:'', component: NeedyHistoryComponent },
   {path:"item/:id" , component:ItemComponent},
   {path:"products" , component:ProductsComponent},
   {path:"profile" , component:ProfileComponent},
];
