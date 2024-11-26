import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Injecting AuthService and Router using Angular DI system
  const authService = new AuthService(); // Make sure to inject using DI
  const jwtHelper = new JwtHelperService();
  const token = localStorage.getItem('authToken');// Fetch token from localStorage
  // Get the JWT token from the service3

  if (token && !jwtHelper.isTokenExpired(token)) {
    // Clone the request and add Authorization header with the token
    const clonedRequest = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
    console.log(clonedRequest);
    return next(clonedRequest);
  }
  else {
    console.log('No token available');
  }
  // If no token or token expired, proceed without modifying the request
  return next(req);
};
