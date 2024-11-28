import {Component, OnInit} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AuthService} from '../../../services/auth.service';
import {LoginService} from '../../../services/login.service';
import {NgClass, NgIf} from '@angular/common';
import {FloatLabelModule} from 'primeng/floatlabel';
import {ChipsModule} from 'primeng/chips';
import {ButtonDirective} from 'primeng/button';
import {ActivatedRoute, Router} from '@angular/router';
import {VerifyEmail} from '../../interfaces/verify-email';
import {InputOtpModule} from 'primeng/inputotp';
import {RegisterService} from '../../../services/register.service';

@Component({
  selector: 'app-verify-email',
  standalone: true,
  imports: [
    FormsModule,
    NgIf,
    FloatLabelModule,
    ChipsModule,
    ButtonDirective,
    NgClass,
    InputOtpModule
  ],
  templateUrl: './verify-email.component.html',
  styleUrl: './verify-email.component.scss'
})
export class VerifyEmailComponent implements  OnInit {
   VerifyEmailOtp?: VerifyEmail;
  otp: string = ''; // OTP token input
  email: any = ''; // Email input
  message: string = ''; // Message to display to the user
  messageClass: string = ''; // To dynamically apply CSS class for success/error messages

  constructor(private loginService: LoginService, private router: Router,private registerService: RegisterService,private _ActivatedRoute:ActivatedRoute) {

  }
  ngOnInit(): void {
   // this.email=this.registerService.email;
      this.email = this._ActivatedRoute.snapshot.paramMap.get("email");

  }
  /**
   * Sends the email verification token to the provided email address.
   */


  sendVerificationCode(): void {

    if (this.email) {
      this.email=this.registerService.email;
      // Call the service to send the verification code to the email
      this.loginService.sendEmailVerificationToken(this.email).subscribe(
        (response) => {
          // On success, show a success message

          this.message = response.message;
          console.log(this.message);
          this.messageClass = 'success'; // Success class
        },
        (error) => {
          // On error, show an error message
          this.message = error.error.message || 'حدث خطأ حاول مره اخرى';
          this.messageClass = 'error'; // Error class
          console.error('Error sending verification code:', error);
        }
      );
    } else {
      this.message = 'البريد الالكتروني غير صالح';
      this.messageClass = 'error';
    }
  }

  /**
   * Verifies the email using the OTP entered by the user.
   */
  verifyEmail(): void {
    this.VerifyEmailOtp = { email: this.email, otp: this.otp };

    if (this.otp && this.email) {
      // Call the service to verify the email with the OTP
      this.loginService.validateEmailVerificationToken( this.VerifyEmailOtp).subscribe(
        (response) => {
          // On success, show a success message and navigate to the login page
          this.message = response.message;
          this.messageClass = 'success';
          this.router.navigate(['/login']); // Redirect to login after success
        },
        (error) => {
          // On error, show an error message
          this.message = 'حدث خطأ حاول مره اخرى';
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
