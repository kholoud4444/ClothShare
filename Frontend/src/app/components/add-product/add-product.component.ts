import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {PaginatorModule} from 'primeng/paginator';
import {NgIf} from '@angular/common';
import {ProductService} from '../../services/product.service';

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
    state: '',
    size: '',
    description: ''
  };

  message: string = '';
  form: FormGroup;
  imageFile: any;

  constructor(private fb: FormBuilder, private productService: ProductService) {
    this.form = this.fb.group({
      type: [''],
      state: [''],
      description: [''],
      size: [''],
    });
  }
  onImageSelected(event: Event) {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      this.imageFile = file;
    }
  }

  onSubmit() {
    debugger
    if (this.form.valid && this.imageFile) {
      const description = this.form.get('description')?.value;
      this.productService.uploadImage(this.imageFile, this.form.value).subscribe(
        (response) => {
          debugger
          console.log('Image uploaded successfully:', response);
          this.form.reset();
        },
        (error) => {
          debugger
          console.error('Error uploading image:', error);
        }
      );
    }
  }

  onSubmi() {
    // You can replace this with an actual API call for saving the product
    console.log(this.product); // Logs product data for testing
    // After form submission, show the success message
    this.message = 'تم الإضافة بنجاح، في انتظار تأكيد الأدمن';

    // Optional: Reset form (if you want to clear the form after submitting)
    this.product = {
      image: '',
      type: '',
      state: '',
      size: '',
      description: ''
    };

    // You could also display an alert for immediate feedback
    // alert('تم الإضافة بنجاح، في انتظار تأكيد الأدمن');
  }
}
