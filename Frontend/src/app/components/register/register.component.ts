import {Component, OnInit} from '@angular/core';
import {Router, RouterLink} from '@angular/router';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {NeedyService} from '../../services/needy.service';
import {Observable} from 'rxjs';
import {Request} from '../model/request';
import {Needy} from '../model/needy';
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
  constructor(
    private fb: FormBuilder,
    private needyService: NeedyService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      phone: ['', Validators.required],
      nationalId: ['', Validators.required],
      gender: ['', Validators.required],
      birthDate: ['', Validators.required],
      location: ['', Validators.required],
      role: ['', Validators.required],
    });
  }

  register(): void {
    if (this.registerForm.valid) {
      const needyData: Needy = this.registerForm.value;
      this.needyService.addNeedy(needyData).subscribe({
        next: (response) => {
          console.log('Needy registered successfully:', response);
          this.router.navigate(['/login']).then(r => "Error");
        },
        error: (error) => {
          console.error('Error registering needy:', error);
        }
      });
    }
  }
}

