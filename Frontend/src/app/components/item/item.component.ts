import {Component, OnInit} from '@angular/core';
import {ButtonDirective} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';
import {jwtDecode} from 'jwt-decode';
@Component({
  selector: 'app-item',
  standalone: true,
  imports: [
    ButtonDirective,
    FormsModule,
    NgIf
  ],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent implements OnInit {
  showRequestForm: boolean = false; // Toggle form visibility
  requestText: string = '';
  isNeedyUser: boolean = false; // Flag to track if the user is 'needy'

  ngOnInit() {
    this.checkUserRole();
  }

  checkUserRole() {
    const token = localStorage.getItem('authToken');
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        this.isNeedyUser = decodedToken.role === 'needy';
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    }
  }// Store request text input

  submitRequest() {
    if (this.requestText.trim()) {
      console.log('Request Submitted:', this.requestText);
      // You can replace the console.log with any further actions (e.g., API call)
      alert('تم إرسال الطلب بنجاح!');
      this.showRequestForm = false; // Hide the form after submission
      this.requestText = ''; // Clear the input field
    }
  }
}
