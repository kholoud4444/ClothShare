import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {NavComponent} from './components/nav/nav.component';
import {HeaderComponent} from './components/header/header.component';
import {AboutUsComponent} from './components/about-us/about-us.component';
import {FooterComponent} from './components/footer/footer.component';
import {ContactUsComponent} from './components/contact-us/contact-us.component';
import {ItemComponent} from './components/item/item.component';
import {ProductsComponent} from './components/products/products.component';



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
