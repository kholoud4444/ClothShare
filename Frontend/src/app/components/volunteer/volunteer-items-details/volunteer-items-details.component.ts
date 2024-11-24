import {Component, OnInit} from '@angular/core';

import {VolunteerService} from '../../../services/volunteer.service';
import {ItemDtoForProduct} from '../../interfaces/item-dto-for-product';
import {Button} from 'primeng/button';
import {KeyValuePipe, NgForOf, NgIf, NgOptimizedImage} from '@angular/common';
import {TagModule} from 'primeng/tag';
import {PaginatorModule} from 'primeng/paginator';
import {ItemService} from '../../../services/item.service';
import {
  ClothingSize,
  ClothingType,
  GenderSuitability,
  ItemDto,
  ItemDtoForEditItem,
  ItemState, ItemStatus
} from '../../interfaces/item-dto';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthService} from '../../../services/auth.service';
import {RouterLink, RouterLinkActive} from '@angular/router';

@Component({
  selector: 'app-volunteer-items-details',
  standalone: true,
  imports: [
    Button,
    NgForOf,
    TagModule,
    NgOptimizedImage,
    PaginatorModule,
    NgIf,
    KeyValuePipe,
    ReactiveFormsModule,
    RouterLinkActive,
    RouterLink
  ],
  templateUrl: './volunteer-items-details.component.html',
  styleUrl: './volunteer-items-details.component.scss'
})
export class VolunteerItemsDetailsComponent implements OnInit {
  items: ItemDtoForProduct[] = [];
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 10;
  loading: boolean = false;
  num: number = 0;
  currentPage: number = 1;

  form: FormGroup ;// Current page number


  constructor(private volunteerService: VolunteerService, private itemService: ItemService,
              private fb: FormBuilder,private authService: AuthService) {
    this.form = this.fb.group({
      type: ['', Validators.required],
      state: ['', Validators.required],
      size: ['', Validators.required],
      genderSuitability: ['', Validators.required],
      description: ['', Validators.required],
      itemId:['',Validators.required],
    });
  }




  ngOnInit(): void {
    this.getItems();
  }

  // Method to fetch items with pagination
  getItems(): void {
    this.loading = true;
    this.volunteerService.getAllItemDetailsByVolunteerId(this.authService.getUserId(), this.pageNo, this.pageSize).subscribe({
      next: (response) => {
        this.items = response.content;
        this.totalRecords = response.totalElements;
        this.loading = false;

        // For each item, fetch the image URL using getPhoto method
        this.items.forEach(item => {
          this.loadImage(item);
        });
      },
      error: (err) => {
        console.error('Error fetching items:', err);
        this.loading = false;
      },
    });
  }

  // Method to load image for an item
  loadImage(item: ItemDtoForProduct): void {
    if (item.imageUrl) {
      this.itemService.getPhoto(item.imageUrl).subscribe({
        next: (imageUrl: string) => {
          item.imageUrl = imageUrl; // Set the image URL for each item
        },
        error: (err: any) => {
          console.error('Error loading image for item', item.itemId, err);
        }
      });
    }
  }

  onPageChange(event: any): void {
    this.pageNo = event.first / event.rows; // Correcting the page number calculation
    this.pageSize = event.rows;
    this.currentPage = event.page + 1;
    this.getItems();
  }

  trackByItemId(index: number, item: ItemDtoForProduct): number {
    return item.itemId; // Returning the unique identifier of each item
  }

  getSeverity(status: string): string {
    switch (status) {
      case 'تم_الموافقة':
        return 'success';
      case 'معلق':
        return 'warning';
      case 'مرفوض':
        return 'danger';
      default:
        return 'info';
    }
  }

  deleteItem(itemId: number): void {
    if (confirm("Are you sure you want to delete this item?")) {
      this.volunteerService.deleteVolunteerItemByItemId(itemId).subscribe(
        (response) => {
          // Handle success (e.g., remove item from the UI or show a message)
          console.log(response.message);  // Show success message or remove from the list
          this.items = this.items.filter(item => item.itemId !== itemId);  // Update UI after deletion
        },
        (error) => {
          // Handle error (e.g., show an error message)
          console.error('Error deleting item', error);
        }
      );
    }
  }
  editItem(): void {
    const itemData: ItemDtoForEditItem = {

      type: this.form.value.type,
      size: this.form.value.size,
      state: this.form.value.state,
      genderSuitability: this.form.value.genderSuitability,
      description: this.form.value.description,
      status: ItemStatus.PENDING,  // Default status as Pending
      volunteerId: this.authService.getUserId(),
      itemId :this.form.value.itemId// Optional, adjust as needed
    };
    this.volunteerService.updateItem(itemData.itemId,itemData).subscribe({
      next: (response) => {
        alert('Item updated successfully!');
        this.getItems();
        console.log(response);
      },
      error: (error) => {
        console.error('Item creation failed:', error);
      },
    });
  }

  protected readonly ClothingSize = ClothingSize;
  protected readonly ItemState = ItemState;
  protected readonly GenderSuitability = GenderSuitability;
  protected readonly ClothingType = ClothingType;

  getItemId(itemId: number) {
    this.volunteerService.getItemById(itemId).subscribe({
      next: (response) => {
        this.form.get('type')?.setValue(response.type);
        this.form.get('state')?.setValue(response.state);
        this.form.get('size')?.setValue(response.size);
        this.form.get('genderSuitability')?.setValue(response.genderSuitability);
        this.form.get('description')?.setValue(response.description);
        this.form.get('status')?.setValue("معلق");
        this.form.get("itemId")?.setValue(itemId);

      }
    })

  }
}
