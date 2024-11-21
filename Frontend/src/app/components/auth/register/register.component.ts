import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {RegisterService} from '../../services/register.service';
import {Observable} from 'rxjs';
import {Request} from '../../interfaces/request';
import {Needy} from '../../interfaces/needy';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    RouterLink,
    FormsModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  errorMessage: string | null = null;
  constructor(
    private fb: FormBuilder,
    private needyService: RegisterService,
    private router: Router
) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      phone: ['', Validators.required],
      nationalId: ['', Validators.required],
      gender: ['', Validators.required],
      birthDate: ['', Validators.required],
      location: ['', Validators.required],
      role: ['', Validators.required],
    });
  }

  register(): void {
    if (this.registerForm.invalid) {
    this.registerForm.markAllAsTouched();
    return;
  }

  const needyData: Needy = this.registerForm.value;
  this.needyService.addNeedy(needyData).subscribe({
    next: (response) => {
      console.log('Needy registered successfully:', response);
      this.router.navigate(['/login']).then(r => "Error");
    },
    error: (error) => {
      if (error.status === 400) {
        this.errorMessage = "The email or another unique field already exists."; // Customize this message as needed
      } else {
        this.errorMessage = "An unexpected error occurred. Please try again later.";
      }
    }
  });
}
}
