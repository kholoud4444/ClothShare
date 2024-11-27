import { Component } from '@angular/core';
import {ButtonDirective} from 'primeng/button';
import {FloatLabelModule} from 'primeng/floatlabel';
import {InputTextModule} from 'primeng/inputtext';
import {NgClass, NgIf} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {LoginService} from '../../../services/login.service';
import {Router} from '@angular/router';
import {PasswordModule} from 'primeng/password';
import {CreateNewPassword} from '../../interfaces/createNewPassword';
import {InputOtpModule} from 'primeng/inputotp';

@Component({
  selector: 'app-new-password',
  standalone: true,
  imports: [
    ButtonDirective,
    FloatLabelModule,
    InputTextModule,
    NgIf,
    ReactiveFormsModule,
    FormsModule,
    NgClass,
    PasswordModule,
    InputOtpModule
  ],
  templateUrl: './new-password.component.html',
  styleUrl: './new-password.component.scss'
})
export class NewPasswordComponent {
  newpassword?:CreateNewPassword;
  email: string = "adhamsiliman121@gmail.com";// Bind the email input field
  message: string = '';
  newPassword:string = ""// Message to display to the user
  messageClass: string = ''; // For applying dynamic styles to the message

  constructor(private loginService: LoginService, private router: Router) {}

  // Method to handle form submission and send reset password token
  updateNewPassword(): void {
    if (this.newPassword) {
      this.newpassword = { email: this.email, password: this.newPassword };
console.log(this.newpassword);
      // Call the service to verify the email with the OTP
      this.loginService.updateNewPassword(this.newpassword).subscribe(
        (response) => {
          // On success, show a success message and navigate to the login page
          this.message = response.message;
          this.messageClass = 'success';
          this.router.navigate(['/login']); // Redirect to login after success
        },
        (error) => {
          // On error, show an error message
          this.message = error.error.message || 'Verification failed. Please check the email.';
          this.messageClass = 'error';
          console.error('Error verifying email:', error);
        }
      );
    } else {
      this.message = 'Please enter  new password.';
      this.messageClass = 'error';
    }
  }

}
