import { Component } from '@angular/core';
import {ButtonDirective} from "primeng/button";
import {InputOtpModule} from "primeng/inputotp";
import {NgClass, NgIf} from "@angular/common";
import {FormsModule} from '@angular/forms';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';
import {VerifyResetPasswordToken} from '../../../interfaces/verify-reset-password-token';

@Component({
  selector: 'app-verify-reset-code',
  standalone: true,
  imports: [
    ButtonDirective,
    InputOtpModule,
    NgIf,
    FormsModule,
    NgClass
  ],
  templateUrl: './verify-reset-code.component.html',
  styleUrl: './verify-reset-code.component.scss'
})
export class VerifyResetCodeComponent {
  VeriyPasswordTokenOtp?: VerifyResetPasswordToken;
  resetCode: string = ''; // OTP token input
  email: string = 'adhamsiliman121@gmail.com'; // Email input
  message: string = ''; // Message to display to the user
  messageClass: string = ''; // To dynamically apply CSS class for success/error messages

  constructor(private loginService: LoginService, private router: Router) {
  }


  verifyResetPasswordCode(): void {
    this.VeriyPasswordTokenOtp = {email: this.email, resetPasswordCode: this.resetCode};

    if (this.resetCode && this.email) {
      // Call the service to verify the email with the OTP
      this.loginService.verifyResetTokenPassword(this.VeriyPasswordTokenOtp).subscribe(
        (response) => {
          // On success, show a success message and navigate to the login page
          this.message = response.message;
          this.messageClass = 'success';
          this.router.navigate(['/NewPassword']); // Redirect to login after success
        },
        (error) => {
          // On error, show an error message
          this.message =  'حدث خطأ حاول مره اخرى';
          this.messageClass = 'error';
          console.error('Error verifying email:', error);
        }
      );
    } else {
      this.message = 'برجاء ادخال الرمز التأكيدي';
      this.messageClass = 'error';
    }

  }
}
