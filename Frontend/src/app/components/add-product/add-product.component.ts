import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {PaginatorModule} from 'primeng/paginator';
import {NgIf} from '@angular/common';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [
    FormsModule,
    PaginatorModule,
    ReactiveFormsModule,
    NgIf
  ],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.scss'
})
export class AddProductComponent {

  product = {
    image: '',
    type: '',
    condition: '',
    size: '',
    description: ''
  };

  message: string = '';

  onSubmit() {
    // You can replace this with an actual API call for saving the product
    console.log(this.product); // Logs product data for testing
    // After form submission, show the success message
    this.message = 'تم الإضافة بنجاح، في انتظار تأكيد الأدمن';

    // Optional: Reset form (if you want to clear the form after submitting)
    this.product = {
      image: '',
      type: '',
      condition: '',
      size: '',
      description: ''
    };

    // You could also display an alert for immediate feedback
    // alert('تم الإضافة بنجاح، في انتظار تأكيد الأدمن');
  }
}
