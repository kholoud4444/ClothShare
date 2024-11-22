import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LoginService} from '../../../services/login.service';
import {Needy} from '../../interfaces/needy';
import {NgIf} from '@angular/common';
import {Login} from '../../interfaces/login';
import {RegisterService} from '../../../services/register.service';
import {jwtDecode} from 'jwt-decode';
import {AuthService} from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {
  errorMessage: string | null = null;

  loginForm!: FormGroup;
  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],

    });
  }

  login(): void {
    if (this.loginForm.valid) {
      const loginData: Login = this.loginForm.value;
      this.loginService.login(loginData).subscribe({
        next: (response) => {
          console.log('Needy login successfully:', response.token);
          localStorage.setItem('authToken', response.token);

          // Decode the token
          const decodedToken = jwtDecode(response.token);
          console.log('Decoded Token:', decodedToken);
          localStorage.setItem('authToken', response.token);

          // Redirect based on user role
          if (this.authService.getUserRole()=== 'admin') {
            this.router.navigate(['/admin']);
          } else if (this.authService.getUserRole()=== 'needy') {
            this.router.navigate(['/needy']);
          } else if (this.authService.getUserRole()=== 'volunteer') {
            this.router.navigate(['/volunteer']);
          } else {
            this.router.navigate(['/']);  // Default route if role not found
          }
          //this.router.navigate(['/home']).then(r => "Error");
          this.errorMessage = null; // Clear error message on success
        },
        error: (error) => {
          console.error('Error login needy:', error);
          this.errorMessage = 'البريد الإلكتروني أو كلمة المرور غير صحيحة'; // Set error message
        }
      });
    }
  }


}
