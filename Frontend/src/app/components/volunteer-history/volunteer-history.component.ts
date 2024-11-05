import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {TableModule} from 'primeng/table';
import {Button} from 'primeng/button';
import {CurrencyPipe, NgForOf, NgIf, NgOptimizedImage} from '@angular/common';
import {RatingModule} from 'primeng/rating';
import {FormsModule} from '@angular/forms';
import {PaginatorModule} from 'primeng/paginator';
import {TagModule} from 'primeng/tag';
import {Product} from './product';


@Component({
  selector: 'app-volunteer-history',
  standalone: true,
  imports: [
    TableModule,
    Button,
    CurrencyPipe,
    RatingModule,
    FormsModule,
    PaginatorModule,
    TagModule,
    NgOptimizedImage,
    NgIf,
    NgForOf
  ],
  templateUrl: './volunteer-history.component.html',
  styleUrl: './volunteer-history.component.scss'
})
export class VolunteerHistoryComponent  implements OnInit {
  products: Product[] = [];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        // @ts-ignore
        this.products = data.products;
      },
      error: (err) => {
        console.error('Failed to fetch products', err);
      }
    });
  }

  getSeverity(status: string): "success" | "secondary" | "info" | "warning" | "danger" | "contrast" | undefined {
    switch (status) {
      case 'In Stock':
        return 'success';
      case 'Low Stock':
        return 'warning';
      case 'Out of Stock':
        return 'danger';
      default:
        return undefined; // You can return 'secondary' or another default if desired
    }
  }


  getStars(rating: number): any[] {
    return Array(Math.round(rating));
  }
}
