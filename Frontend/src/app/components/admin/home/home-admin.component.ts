import { Component } from '@angular/core';
import {RouterLink} from '@angular/router';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterLink,
    AsyncPipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './home-admin.component.html',
  styleUrl: './home-admin.component.scss'
})
export class HomeAdminComponent {

}
