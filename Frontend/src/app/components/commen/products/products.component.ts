import { Component, OnInit } from '@angular/core';
import { IProduct, ProductInventoryStatus } from '../../interfaces/iproducts';
import {CurrencyPipe, NgForOf, NgOptimizedImage, UpperCasePipe} from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { TagModule } from 'primeng/tag';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';
import {ProductService} from '../../services/product.service';
import {RequestVolunteerHistory} from '../../interfaces/request-volunteer-history';

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

  // @ts-ignore
  // @ts-ignore
  products: Array<IProduct> = [
    // Sample product data for demonstration; replace with actual data

  ]
  items: any[] = []; // Current page items
  filteredItems: any[] = []; // Items after filtering



  currentPage: number = 0;
  itemsPerPage: number = 4;
  totalItems: number = 0;
  totalPages: number = 0;

  paginatedProducts: Array<IProduct> = [];  // Array to hold products for the current page
  allProducts:any[]=[];
  constructor(private _ProductService:ProductService) {
  }
  ngOnInit() {
    debugger
    // this.updatePaginatedProducts(0);
    this._ProductService.getAllProduct(0, 1000).subscribe({
      next:(res)=>{
        debugger
        this.allProducts = res.content;
        this.applyFilter(); // Initialize with unfiltered items
        console.log(res)
      },
      error:(err)=>{
        console.log(err);
      }
    })
    // Initialize paginated products for the first page
  }

  applyFilter() {
    this.filteredItems = [...this.allProducts]; // Start with all items
    this.updatePagination();
  }

  paginate(event: any) {
    const page = event.page;
    this.updatePaginatedProducts(page);
  }

  updatePaginatedProducts(page: number) {
    const start = page * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    this.paginatedProducts = this.products.slice(start, end);
  }


  // Navigate to the next page
  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.updatePagination();
    }
  }

  // Navigate to the previous page
  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.updatePagination();
    }
  }

  // Navigate to a specific page
  goToPage(pageIndex: number) {
    if (pageIndex >= 0 && pageIndex < this.totalPages) {
      this.currentPage = pageIndex;
      this.updatePagination();
    }
  }

  updatePagination() {
    debugger
    this.totalItems = this.allProducts.length;
    this.totalPages = Math.ceil(this.totalItems / this.itemsPerPage);

    const startIndex = this.currentPage * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.items = this.allProducts.slice(startIndex, endIndex);
    debugger
    // Paginate filtered items
  }

}



// import { Component, OnInit } from '@angular/core';
// import { IProduct, ProductInventoryStatus } from './iproducts';
// import { CurrencyPipe, UpperCasePipe } from '@angular/common';
// import { RatingModule } from 'primeng/rating';
// import { FormsModule } from '@angular/forms';
// import { TagModule } from 'primeng/tag';
// import { RouterLink, RouterLinkActive } from '@angular/router';
// import { PaginatorModule } from 'primeng/paginator';
//
// @Component({
//   selector: 'app-products',
//   standalone: true,
//   imports: [
//     UpperCasePipe,
//     RatingModule,
//     FormsModule,
//     TagModule,
//     CurrencyPipe,
//     RouterLink,
//     RouterLinkActive,
//     PaginatorModule
//   ],
//   templateUrl: './products.component.html',
//   styleUrls: ['./products.component.scss']
// })
// export class ProductsComponent implements OnInit {
//
//   products: Array<IProduct> = [
//     {
//       id: 1,
//       name: 'VolunteerHistoryInterface 1',
//       description: 'Description 1',
//       category: 'Category 1',
//       price: 100,
//       inventoryStatus: ProductInventoryStatus.IN_STOCK,
//       code: 'P001',
//       image: 'https://primefaces.org/cdn/primeng/images/demo/product/green-t-shirt.jpg',
//       quantity: 50,
//       rating: 4
//     },
//     {
//       id: 2,
//       name: 'VolunteerHistoryInterface 2',
//       description: 'Description 2',
//       category: 'Category 2',
//       price: 150,
//       inventoryStatus: ProductInventoryStatus.OUT_OF_STOCK,
//       code: 'P002',
//       image: 'https://primefaces.org/cdn/primeng/images/demo/product/brown-purse.jpg',
//       quantity: 0,
//       rating: 3
//     },
//     // Add more products as needed
//   ];
//
//   itemsPerPage = 3;  // Number of products per page
//   paginatedProducts: Array<IProduct> = [];
//
//   ngOnInit() {
//     console.log("Initial products:", this.products);
//     this.updatePaginatedProducts(0);  // Initialize paginated products for the first page
//   }
//
//   paginate(event: any) {
//     const page = event.page;
//     this.updatePaginatedProducts(page);
//   }
//
//   updatePaginatedProducts(page: number) {
//     const start = page * this.itemsPerPage;
//     const end = start + this.itemsPerPage;
//     this.paginatedProducts = this.products.slice(start, end);
//     console.log("Paginated products:", this.paginatedProducts);
//   }
// }
