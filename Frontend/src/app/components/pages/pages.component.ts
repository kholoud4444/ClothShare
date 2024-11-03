import { Component } from '@angular/core';
import {NavComponent} from '../nav/nav.component';
import {RouterOutlet} from '@angular/router';
import {FooterComponent} from '../footer/footer.component';
import {HeaderComponent} from '../header/header.component';

@Component({
  selector: 'app-pages',
  standalone: true,
  imports: [
    NavComponent,
    RouterOutlet,
    FooterComponent,
    HeaderComponent
  ],
  templateUrl: './pages.component.html',
  styleUrl: './pages.component.scss'
})
export class PagesComponent {

}
