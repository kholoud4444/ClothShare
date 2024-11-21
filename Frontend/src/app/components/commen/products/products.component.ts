import { Component, OnInit } from '@angular/core';
import {CurrencyPipe, NgForOf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { TagModule } from 'primeng/tag';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';
import {ItemService} from '../../services/item.service';
import {RequestVolunteerHistory} from '../../interfaces/request-volunteer-history';
import {ItemDto} from '../../interfaces/item-dto';

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
    NgOptimizedImage
  ],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']  // Updated to `styleUrls` (plural) to avoid error
})
export class ProductsComponent implements OnInit {
  items: ItemDto[] = [];
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 5;
  loading: boolean = false;

  constructor(private itemService: ItemService) {}

  ngOnInit(): void {
    this.getItems();
  }

  // Method to fetch items with pagination
  getItems(): void {
    this.loading = true;
    console.log('Fetching items...');
    this.itemService.getAllItems(this.pageNo, this.pageSize).subscribe({
      next: (response) => {
        console.log('Items fetched:', response);
        this.items = response.content;
        this.totalRecords = response.totalElements;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching items:', err);
        this.loading = false;
      },
    });
  }

  // Handle page changes
  onPageChange(event: any): void {
    this.pageNo = event.first / event.rows;  // Correcting the page number calculation
    this.pageSize = event.rows;
    this.getItems();
  }
}
