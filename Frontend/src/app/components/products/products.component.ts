import { Component } from '@angular/core';
import {IProduct, ProductInventoryStatus} from './iproducts';
import {CurrencyPipe, UpperCasePipe} from '@angular/common';
import {RatingModule} from 'primeng/rating';
import {FormsModule} from '@angular/forms';
import {TagModule} from 'primeng/tag';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    UpperCasePipe,
    RatingModule,
    FormsModule,
    TagModule,
    CurrencyPipe
  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent {

  products: Array<IProduct> = [
    
  ];

}
