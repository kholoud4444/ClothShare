import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgClass, NgIf, NgOptimizedImage } from '@angular/common';
import {jwtDecode} from 'jwt-decode';

@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgOptimizedImage,
    NgClass,
    NgIf,
  ],
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss'],
})
export class NavComponent implements OnInit {
  imagesrc = 'assets/images/logo.png';
  isNavbarCollapsed = true; // State for dropdown toggle
  decodedToken: any = null; // Decoded token variable

  toggleNavbar(): void {
    this.isNavbarCollapsed = !this.isNavbarCollapsed; // Toggle dropdown state
  }

  ngOnInit(): void {
    const token = localStorage.getItem('authToken'); // Retrieve token from localStorage
    if (token) {
      try {
        this.decodedToken = jwtDecode(token); // Decode the token safely
        console.log('Decoded Token:', this.decodedToken); // Debug log
      } catch (error) {
        console.error('Failed to decode token:', error); // Log errors
        this.decodedToken = null; // Reset in case of failure
      }
    } else {
      console.warn('No auth token found in localStorage'); // Warn if token is missing
    }
  }
}
