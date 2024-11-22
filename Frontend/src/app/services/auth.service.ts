import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenKey = 'authToken';
  private userRoleSubject = new BehaviorSubject<string | null>(null);
  userRole$ = this.userRoleSubject.asObservable();
  private jwtHelper = new JwtHelperService();

  // Decode token using JwtHelperService
  decodeToken(token: string): any {
    return this.jwtHelper.decodeToken(token);
  }

  // Check if the user is logged in based on token presence
  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }

  // Retrieve the token from localStorage
  getToken(): string | null {
    const token = localStorage.getItem(this.tokenKey);
    console.log('Token from localStorage:', token); // Debugging
    return token;
  }

  // Get the user role from the token
  getUserRole(): string | null {
    const token = localStorage.getItem(this.tokenKey);
    if (token) {
      try {
        const decodedToken = this.decodeToken(token);
        return decodedToken?.role || null; // Return null if role is undefined
      } catch (e) {
        console.error("Error decoding token", e);
        return null;
      }
    }
    return null; // Return null if no token
  }

  // Get the user ID from the token
  getUserId(): number {
    const token = localStorage.getItem(this.tokenKey);
    if (token) {
      const decodedToken = this.decodeToken(token);
      return decodedToken.UserId || 0; // Return 0 if UserId is not found
    }
    return 0; // Return 0 if no token
  }

  // Log out the user (remove token and reset role)
  logout() {
    localStorage.removeItem(this.tokenKey);
    this.userRoleSubject.next(null); // Reset the role
  }

  // Save the token in localStorage and update the role
  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
    this.updateUserRole(token); // Update the role after saving the token
  }

  // Update the user role in the BehaviorSubject
  updateUserRole(token: string): void {
    try {
      const decodedToken = this.decodeToken(token);
      const role = decodedToken.role || null; // Extract the role from the decoded token
      this.userRoleSubject.next(role); // Update the role in the subject
    } catch (e) {
      console.error("Error decoding token", e);
      this.userRoleSubject.next(null); // Reset the role if decoding fails
    }
  }
}
