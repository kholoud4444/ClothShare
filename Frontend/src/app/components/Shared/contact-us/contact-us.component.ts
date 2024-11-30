import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";
import {NgIf} from '@angular/common';
import {AdminService} from '../../../services/admin.service';
import {ContactUs} from '../../../interfaces/contact-us';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-contact-us',
  standalone: true,
  imports: [
    RouterLink,
    RouterLinkActive,
    NgIf,
    FormsModule
  ],
  templateUrl: './contact-us.component.html',
  styleUrl: './contact-us.component.scss'
})
export class ContactUsComponent {
  contactFormData: ContactUs = {
    sentTo: '',
    subject: '', // Optional for the email subject
    message: '',
  };
  contactFormDataWillSend: ContactUs = {
    sentTo: '',
    subject: '', // Optional for the email subject
    message: '',
  };

  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private emailService:AdminService) {}

  // Method to send the email
  sendEmail() {
    this.successMessage = null;
    this.errorMessage = null;
    this.contactFormDataWillSend.message= "my name is "+this.contactFormData.subject + "my email address"+this.contactFormData.sentTo+this.contactFormData.message;
    this.contactFormDataWillSend.sentTo="'kesaa.team@gmail.com";
    this.contactFormDataWillSend.subject="problem";

    // Call the service to send the email
    this.emailService.sendContactUsEmail(this.contactFormDataWillSend).subscribe({
      next: (response) => {
        console.log(response);
        this.successMessage = 'تم إرسال البريد الإلكتروني بنجاح!';
        this.resetForm(); // Optionally reset the form after successful submission
      },
      error: (err) => {
        this.errorMessage = 'فشل في إرسال البريد الإلكتروني. يرجى المحاولة لاحقاً.';
        console.error('Error:', err);
      },
    });
  }

  // Method to reset the form after successful submission
  resetForm() {
    this.contactFormData = {
      sentTo: '',
      subject: '',
      message: '',
    };
  }


}
