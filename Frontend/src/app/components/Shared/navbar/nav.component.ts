import { Component, OnInit } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { NgClass, NgIf, NgOptimizedImage } from '@angular/common';
import {jwtDecode} from 'jwt-decode';
import {AuthService} from  '../../../services/auth.service';

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

  isNavbarCollapsed = true; // State for dropdown toggle
  decodedToken: any = null; // Decoded token variable
  userRole: string | null = null;

  constructor(private authService: AuthService) {}
  toggleNavbar(): void {
    this.isNavbarCollapsed = !this.isNavbarCollapsed; // Toggle dropdown state
  }
  logout(): void {
    this.authService.logout();
  }

  ngOnInit(): void {
    const token = this.authService.getToken();
    if (token) {
      this.authService.updateUserRole(token);  // Ensure role is updated after reload
    }

    this.authService.userRole$.subscribe((role) => {
      this.userRole = role;
      console.log(this.userRole, token); // Log the role after it's updated
    })


  }
}
