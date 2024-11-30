import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {RegisterService} from '../../../services/register.service';
import {Needy} from '../../../interfaces/needy';
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
    private registerService: RegisterService,
    private router: Router
  ) {
    this.registerForm = this.fb.group({
      firstName: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z\u0621-\u064A\u0660-\u0669]{2,10}$/),
      ]),
      lastName: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z\u0621-\u064A\u0660-\u0669]{2,10}$/),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(8),
        Validators.pattern(
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,20}$/
        ),
      ]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern(/^01[1205][\d\u0660-\u0669]{8}$/),
      ]),
      nationalId: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[23][\d\u0660-\u0669]{13}$/),
      ]),
      gender: new FormControl('', [Validators.required]),
      birthDate: new FormControl('', [Validators.required]),
      location: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
    });

    // Trim spaces in real-time
    this.registerForm.get('firstName')?.valueChanges.subscribe((value) => {
      const trimmedValue = value.replace(/\s+/g, '');
      if (value !== trimmedValue) {
        this.registerForm.get('firstName')?.setValue(trimmedValue, {
          emitEvent: false,
        });
      }
    });

    this.registerForm.get('lastName')?.valueChanges.subscribe((value) => {
      const trimmedValue = value.replace(/\s+/g, '');
      if (value !== trimmedValue) {
        this.registerForm.get('lastName')?.setValue(trimmedValue, {
          emitEvent: false,
        });
      }
    });
  }

  ngOnInit(): void {}

  register(): void {
    if (this.registerForm.invalid) {
      this.registerForm.markAllAsTouched();
      return;
    }

    const needyData: Needy = this.registerForm.value;
    this.registerService.addNeedy(needyData).subscribe({
      next: (response) => {
        console.log('Needy registered successfully:', response);
        this.registerService.email = needyData.email;
        this.router.navigate([`/verifyEmail/${needyData.email}`]).then(() => {
          console.error('Navigation Error');
        });
      },
      error: (error) => {
        if (
          error.status === 400 &&
          error.error.details?.constraintViolation
        ) {
          const constraintViolation = error.error.details.constraintViolation;

          // Match the constraint violation dynamically
          if (constraintViolation.includes('national_id')) {
            this.errorMessage = 'الرقم القومي موجود من قبل';
          } else if (constraintViolation.includes('email')) {
            this.errorMessage = 'البريد الإلكتروني موجود من قبل';
          } else if (constraintViolation.includes('phone')) {
            this.errorMessage = 'رقم الهاتف موجود من قبل';
          } else {
            this.errorMessage =
              'حدث خطأ أثناء التسجيل حاول مرة أخرى.';
          }
        } else {
          this.errorMessage =
            'حدث خطأ أثناء التسجيل حاول مرة أخرى.';
        }
      },
    });
  }
}
