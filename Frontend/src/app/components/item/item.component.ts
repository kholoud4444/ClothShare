import { Component } from '@angular/core';
import {ButtonDirective} from 'primeng/button';
import {Ripple} from 'primeng/ripple';
import {FormsModule} from '@angular/forms';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-item',
  standalone: true,
  imports: [
    ButtonDirective,
    Ripple,
    FormsModule,
    NgIf
  ],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent {
  showRequestForm: boolean = false; // Toggle form visibility
  requestText: string = ''; // Store request text input

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
