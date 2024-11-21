import { Component, OnInit } from '@angular/core';
import {CurrencyPipe, NgForOf, NgIf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { TagModule } from 'primeng/tag';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';
import {ItemService} from '../../services/item.service';


import {ItemDtoForProduct} from '../../interfaces/item-dto-for-product';

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

  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.getItems();
  }

  // Method to fetch items with pagination
  getItems(): void {
    this.loading = true;
    this.itemService.getAllItems(this.pageNo, this.pageSize).subscribe({
      next: (response) => {
        this.items = response.content;
        this.totalRecords = response.totalElements;
        this.loading = false;

        // For each item, fetch the image URL
        this.items.forEach(item => {
          // Call getPhoto method to fetch the image asynchronously
          this.itemService.getPhoto(item.imageUrl).subscribe(imageUrl => {
            item.imageUrl = imageUrl; // Set the fetched image URL for each item
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
    this.pageNo = event.first / event.rows;  // Correcting the page number calculation
    this.pageSize = event.rows;
    this.getItems();
  }
  trackByItemId(index: number, item: ItemDtoForProduct): number {
    return item.itemId; // Returning the unique identifier of each item
  }
}
