import { Component } from '@angular/core';
import {NavComponent} from '../nav/nav.component';
import {RouterOutlet} from '@angular/router';
import {FooterComponent} from '../footer/footer.component';
import {HeaderComponent} from '../header/header.component';
import {AboutUsComponent} from '../about-us/about-us.component';
import {ProductsComponent} from '../products/products.component';
import {ContactUsComponent} from '../contact-us/contact-us.component';

@Component({
  selector: 'app-pages',
  standalone: true,
  imports: [
    NavComponent,
    RouterOutlet,
    FooterComponent,
    HeaderComponent,
    AboutUsComponent,
    ProductsComponent,
    ContactUsComponent
  ],
  templateUrl: './pages.component.html',
  styleUrl: './pages.component.scss'
})
export class PagesComponent {

}
