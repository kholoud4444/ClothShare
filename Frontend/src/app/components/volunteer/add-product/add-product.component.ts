import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {PaginatorModule} from 'primeng/paginator';
import {KeyValuePipe, NgForOf, NgIf} from '@angular/common';
import {Router} from '@angular/router';
import { ItemDto, ClothingType, ClothingSize, ItemState, GenderSuitability, ItemStatus } from '../../interfaces/item-dto';
import {AddProductService} from '../../../services/add-product.service';
import {AuthService} from '../../../services/auth.service';
import {NotificationService} from '../../../services/notification.service';
@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [
    FormsModule,
    PaginatorModule,
    ReactiveFormsModule,
    NgIf,
    KeyValuePipe,
    NgForOf
  ],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.scss'
})
export class AddProductComponent {

  form: FormGroup;
  selectedFile: File | null = null;
  imageUrl: string | null = null;
  message: string | null = null;

  ClothingType = ClothingType;
  ItemState = ItemState;
  ClothingSize = ClothingSize;
  GenderSuitability = GenderSuitability;
  ItemStatus = ItemStatus;

  constructor(
    private fb: FormBuilder,
  private addProductService: AddProductService,
  private router: Router,
  private authService: AuthService,
    private notificationService: NotificationService,
  ) {
    this.form = this.fb.group({
      type: ['', Validators.required],
      state: ['', Validators.required],
      size: ['', Validators.required],
      genderSuitability: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  onImageSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  onImageUpload(): void {
    if (!this.selectedFile) {
      alert('Please select an image first.');
      return;
    }

    this.addProductService.uploadImage(this.selectedFile).subscribe({
      next: (response) => {
        this.imageUrl = response.object.imageUrl;
        alert('Image uploaded successfully!');
      },
      error: (error) => {
        console.error('Image upload failed:', error);
        alert('Failed to upload image.');
      },
    });
  }

  onSubmit(): void {
    if (!this.imageUrl) {
      alert('Please upload an image before creating the item.');
      return;
    }

    const itemData: ItemDto = {

      type: this.form.value.type,
      size: this.form.value.size,
      state: this.form.value.state,
      genderSuitability: this.form.value.genderSuitability,
      imageUrl: this.imageUrl,
      description: this.form.value.description,
      status: ItemStatus.PENDING,  // Default status as Pending
      volunteerId: this.authService.getUserId(),  // Optional, adjust as needed
    };

    this.addProductService.createItem(itemData).subscribe({
      next: (response) => {
        alert('Item created successfully!');
        console.log(response);
        this.notificationService.showError("تم اضافة المنتج بنجاح")

      },
      error: (error) => {
        console.error('Item creation failed:', error);
      },
    });
  }
}
