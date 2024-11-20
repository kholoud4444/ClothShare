import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from './components/auth/login/login.component';
import {RegisterComponent} from './components/auth/register/register.component';
import {NavComponent} from './components/commen/nav/nav.component';
import {HeaderComponent} from './components/pages/header/header.component';
import {AboutUsComponent} from './components/pages/about-us/about-us.component';
import {FooterComponent} from './components/commen/footer/footer.component';
import {ContactUsComponent} from './components/pages/contact-us/contact-us.component';
import {ItemComponent} from './components/commen/item/item.component';
import {ProductsComponent} from './components/commen/products/products.component';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, RegisterComponent, NavComponent, HeaderComponent, AboutUsComponent, FooterComponent, ContactUsComponent, ItemComponent, ProductsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Frontend';
}
