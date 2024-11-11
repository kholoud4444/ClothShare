import { Component, OnInit } from '@angular/core';
import { IProduct, ProductInventoryStatus } from './iproducts';
import { CurrencyPipe, UpperCasePipe } from '@angular/common';
import { RatingModule } from 'primeng/rating';
import { FormsModule } from '@angular/forms';
import { TagModule } from 'primeng/tag';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { PaginatorModule } from 'primeng/paginator';

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
    PaginatorModule
  ],
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']  // Updated to `styleUrls` (plural) to avoid error
})
export class ProductsComponent implements OnInit {

  // @ts-ignore
  // @ts-ignore
  products: Array<IProduct> = [
    // Sample product data for demonstration; replace with actual data
    {
      id: 1,
      name: 'Product 1',
      description: 'Description 1',
      category: 'Category 1',
      price: 100,
      inventoryStatus: ProductInventoryStatus.IN_STOCK,
      code: '',
      image: '',
      quantity: 0,
      rating: 0
    },
    {
      id: 2,
      name: 'Product 2',
      description: 'Description 2',
      category: 'Category 2',
      price: 150,
      inventoryStatus: ProductInventoryStatus.OUT_OF_STOCK,
      code: '',
      image: '',
      quantity: 0,
      rating: 0
    },
    // Add more products as needed
  ];

  itemsPerPage = 1;                // Number of products per page
  paginatedProducts: Array<IProduct> = [];  // Array to hold products for the current page

  ngOnInit() {
    this.updatePaginatedProducts(0);  // Initialize paginated products for the first page
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
//       name: 'Product 1',
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
//       name: 'Product 2',
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
