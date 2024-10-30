import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RegisterComponent} from './components/register/register.component';
import {NavComponent} from './components/nav/nav.component';
import {HeaderComponent} from './components/header/header.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, LoginComponent, RegisterComponent, NavComponent, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Frontend';
}
