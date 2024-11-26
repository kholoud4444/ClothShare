import { Component } from '@angular/core';
import {ButtonDirective} from 'primeng/button';
import {FloatLabelModule} from 'primeng/floatlabel';
import {InputTextModule} from 'primeng/inputtext';
import {NgClass, NgIf} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-forget-password',
  standalone: true,
  imports: [
    ButtonDirective,
    FloatLabelModule,
    InputTextModule,
    NgIf,
    ReactiveFormsModule,
    FormsModule,
    NgClass
  ],
  templateUrl: './forget-password.component.html',
  styleUrl: './forget-password.component.scss'
})
export class ForgetPasswordComponent {
  emailReset: string = ''; // Bind the email input field
  message: string = ''; // Message to display to the user
  messageClass: string = ''; // For applying dynamic styles to the message

  constructor(private loginService: LoginService, private router: Router) {}

  // Method to handle form submission and send reset password token
  sendResetPasswordToken(): void {
    if (!this.isValidEmail(this.emailReset)) {
      // If the email is invalid, show an error message
      this.message = 'Please enter a valid email address.';
      this.messageClass = 'error';
      return;
    }

    // Call the service to send the verification code to the email
    this.loginService.sendPasswordResetToken(this.emailReset).subscribe(
      (response) => {
        // On success, show a success message and redirect to another page if needed
        this.message = response.message || 'Password reset code sent successfully!';
        this.messageClass = 'success';
        // Optional: Redirect user after successful request
        // this.router.navigate(['/some-other-page']);
      },
      (error) => {
        // On error, show an error message
        this.message = error.error.message || 'An unexpected error occurred.';
        this.messageClass = 'error';
        console.error('Error sending password reset token:', error);
      }
    );
  }

  // Simple email validation using a regular expression
  isValidEmail(email: string): boolean {
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
    return emailPattern.test(email);
  }
}
