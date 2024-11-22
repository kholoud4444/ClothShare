import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class NeedyGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.getUserRole() !== 'needy') {
      const role = this.authService.getUserRole();
      this.router.navigate([`/${role}`]).then(err => "Error to go to Needy"); // Redirect to the user's dashboard
      return false;
    }
    return true;
  }
}
