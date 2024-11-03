import { Component } from '@angular/core';
import {HeaderComponent} from '../../header/header.component';
import {ContactUsComponent} from '../../contact-us/contact-us.component';
import {AboutUsComponent} from '../../about-us/about-us.component';
import {ProductsComponent} from '../../products/products.component';
import {ItemComponent} from '../../item/item.component';
import {PagesComponent} from '../pages.component';
import {NavComponent} from '../../nav/nav.component';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    HeaderComponent,
    ContactUsComponent,
    AboutUsComponent,
    ProductsComponent,
    ItemComponent,
    PagesComponent,
    NavComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
