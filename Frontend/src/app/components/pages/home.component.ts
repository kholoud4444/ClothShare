import { Component } from '@angular/core';
import {NavComponent} from '../commen/nav/nav.component';
import {RouterOutlet} from '@angular/router';
import {FooterComponent} from '../commen/footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {AboutUsComponent} from './about-us/about-us.component';
import {ProductsComponent} from '../commen/products/products.component';
import {ContactUsComponent} from './contact-us/contact-us.component';
import {VolunteerComponent} from '../admin/volunteer/volunteer.component';

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
    ContactUsComponent,
    VolunteerComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

}
