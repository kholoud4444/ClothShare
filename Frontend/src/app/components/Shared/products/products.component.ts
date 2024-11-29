import { Component, OnInit } from '@angular/core';
import {CurrencyPipe, NgForOf, NgIf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { TagModule } from 'primeng/tag';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';
import {ItemService} from '../../../services/item.service';


import {ItemDtoForProduct} from '../../interfaces/item-dto-for-product';
import {AuthService} from '../../../services/auth.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    UpperCasePipe,
    RatingModule,
    FormsModule,
    TagModule,
    CurrencyPipe,
    RouterLink,
    RouterLinkActive,
    PaginatorModule,
    NgForOf,
    NgOptimizedImage,
    NgIf
  ],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']  // Updated to `styleUrls` (plural) to avoid error
})
export class ProductsComponent implements OnInit {
  items: ItemDtoForProduct[] = [];
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 8;
  loading: boolean = false;


  userRole!: string | null;
  sizeVar:string='';
  genderVar:string='';
  stateVar:string='';
  typeVar:string='';
  typeStatus:string='';

  typies: any[] | undefined;
  selectedState: any | undefined = '';

  state: any[] | undefined;
  selectedType: any | undefined = '';

  gender: any[] | undefined;
  selectedGender: any | undefined = '';

  size: any[] | undefined;
  selectedSize: any | undefined = '';

  constructor(private itemService: ItemService, private authService: AuthService) {}

  ngOnInit(): void {
    this.userRole = this.authService.getUserRole();
    this.getItems();
    this.typies = [
      { name: 'الكل' },
      { name: 'قميص' },
      { name: 'فستان' },
      { name: 'بنطلون' },
      { name: 'تنورة' },
      { name: 'جاكيت' },
      { name: 'معطف' }
    ];
    this.state = [
      { name: 'الكل' },
      { name: 'جديد' },
      { name: 'مستعمل' },
    ];

    this.gender = [
      { name: 'الكل' },
      { name: 'ذكر' },
      { name: 'أنثى' },

    ];
    this.size = [
      { name: 'الكل' },
      { name: 'صغير' },
      { name: 'متوسط' },
      { name: 'كبير' },
      { name: 'كبير_جداً' },

    ];

  }

  // Method to fetch items with pagination
  getItems(): void {
    this.loading = true;
    this.itemService.getAllItems(this.pageNo, this.pageSize).subscribe({
      next: (response) => {
        const filteredItems = response.content.filter(
          (item) => item.status === 'تم_الموافقه'
        );
        this.items = filteredItems;
        this.totalRecords = response.totalElements; // Use totalElements from the backend response
        this.loading = false;

        // Fetch the image URL for each item
        this.items.forEach((item) => {
          this.itemService.getPhoto(item.imageUrl).subscribe((imageUrl) => {
            item.imageUrl = imageUrl;
          });
        });
      },
      error: (err) => {
        console.error('Error fetching items:', err);
        this.loading = false;
      },
    });
  }

  onPageChange(event: any): void {
    this.pageNo = event.first / event.rows; // Correcting page number calculation
    this.pageSize = event.rows;
    this.getItems();
  }

  trackByItemId(index: number, item: ItemDtoForProduct): number {
    return item.itemId; // Returning unique identifier
  }

  handleOnFilter($event: any) {

    if (this.selectedType.name && this.selectedType.name!=='الكل')
    {
      this.typeVar=this.selectedType.name;
    }
    else
    {
      this.typeVar='';
    }
    if (this.selectedState.name && this.selectedState.name!=='الكل')
    {
      this.stateVar=this.selectedState.name;
    }
    else
    {
      this.typeVar='';
    }
    if (this.selectedSize.name && this.selectedSize.name!=='الكل')
    {
      this.sizeVar=this.selectedSize.name;
    }
    else
    {
      this.sizeVar='';
    }
    if (this.selectedGender.name && this.selectedGender.name!=='الكل')
    {
      this.genderVar=this.selectedGender.name;
    }
    else
    {
      this.genderVar='';
    }
    this.typeStatus="تم_الموافقه";

    this.itemService.getFilteredItems(this.typeVar,this.stateVar,this.genderVar,this.sizeVar,this.typeStatus,this.pageNo,this.pageSize).subscribe({
      next: (response) => {
        this.items = response.content;
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

}
