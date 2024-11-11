import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {LoginService} from '../../services/login.service';
import {Needy} from '../model/needy';
import {NgIf} from '@angular/common';
import {Login} from '../model/login';
import {NeedyService} from '../../services/needy.service';
import {jwtDecode} from 'jwt-decode';

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
    private router: Router
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

          this.router.navigate(['/products']).then(r => "Error");
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
