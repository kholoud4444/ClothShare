import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from '@angular/router';
import {NgClass, NgOptimizedImage} from '@angular/common';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgOptimizedImage,
    NgClass
  ],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss'
})
export class NavComponent {
     imagesrc='assets/images/logo.png';
  isNavbarCollapsed = true; // حالة القائمة المنسدلة

  toggleNavbar() {
    this.isNavbarCollapsed = !this.isNavbarCollapsed; // تبديل حالة القائمة
  }
}
