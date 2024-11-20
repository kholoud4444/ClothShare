import {Routes} from '@angular/router';
import {NeedyHistoryComponent} from './needy-history/needy-history.component';
import {ProductsComponent} from '../commen/products/products.component';
import {ItemComponent} from '../commen/item/item.component';

 export const needyRoutes: Routes = [
   { path: '', component: NeedyHistoryComponent },
   {path:"products" , component:ProductsComponent},
   {path:"item/:id" , component:ItemComponent}
];
