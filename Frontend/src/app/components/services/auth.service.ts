import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private tokenKey = 'authToken';

  isLoggedIn(): boolean {
    return !!localStorage.getItem(this.tokenKey);
  }
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey); // Adjust as per your storage mechanism
  }

  getUserRole(): string {
    const token = localStorage.getItem(this.tokenKey);
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1])); // Decode JWT
      return payload.role; // Assuming `role` is part of the JWT payload
    }
    return '';
  }
  getUserId(): number {
    const token = localStorage.getItem(this.tokenKey);
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1])); // Decode JWT
      return payload.UserId; // Assuming `userId` is part of the JWT payload
    }
    return 0;
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
  }
  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }
}
