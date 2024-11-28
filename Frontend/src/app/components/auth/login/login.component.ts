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
import {NotificationService} from '../../../services/notification.service';

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
    private notificationService: NotificationService,
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
          this.authService.saveToken(response.token);

          const role = this.authService.getUserRole();
          console.log('Redirecting based on role:', role);

          // Redirect based on user role
          if (role=== 'admin') {
            this.router.navigate(['/admin']);
          } else if (role=== 'needy') {
            this.router.navigate(['/needy']);
          } else if (role=== 'volunteer') {
            this.router.navigate(['/volunteer']);
          } else {
            this.router.navigate(['/home']);  // Default route if role not found
          }
          //this.router.navigate(['/home']).then(r => "Error");
          this.errorMessage = null; // Clear error message on success
        },
        error: (error) => {
          console.error('Error login needy:', error);
          if(error.status === 404){
            this.notificationService.showError("البريد الإلكتروني غير متاح")
          }
          this.notificationService.showError(error.error.message);
          this.errorMessage = 'البريد الإلكتروني أو كلمة المرور غير صحيحة'; // Set error message
        }
      });
    }
  }


}
