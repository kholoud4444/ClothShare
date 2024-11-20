import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root',
})
export class VolunteerGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean {
    if (this.authService.getUserRole() !== 'volunteer') {
      const role = this.authService.getUserRole();
      this.router.navigate([`/${role}`]).then(r => "Error to go to volunteer"); // Redirect to the user's dashboard
      return false;
    }
    return true;
  }
}
