import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from './components/auth/login/login.component';
import {RegisterComponent} from './components/auth/register/register.component';
import {NavComponent} from './components/Shared/navbar/nav.component';
import {HeaderComponent} from './components/pages/header/header.component';
import {AboutUsComponent} from './components/Shared/about-us/about-us.component';
import {FooterComponent} from './components/Shared/footer/footer.component';
import {ContactUsComponent} from './components/Shared/contact-us/contact-us.component';
import {ItemComponent} from './components/Shared/item/item.component';
import {ProductsComponent} from './components/Shared/products/products.component';
import {NeedyHistoryComponent} from './components/needy/needy-history/needy-history.component';
import {HomeAdminComponent} from './components/admin/home/home-admin.component';
import {
  VolunteerItemsDetailsComponent
} from './components/volunteer/volunteer-items-details/volunteer-items-details.component';
import {VolunteerComponent} from './components/admin/volunteer/volunteer.component';



@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, RegisterComponent, NavComponent, HeaderComponent, AboutUsComponent, FooterComponent, ContactUsComponent, ItemComponent, ProductsComponent, NeedyHistoryComponent, HomeAdminComponent, VolunteerComponent, VolunteerItemsDetailsComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Frontend';
}
