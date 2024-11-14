import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {ProductService} from '../../../services/product.service';
import {jwtDecode} from 'jwt-decode';
import {AdminService} from '../../../services/admin.service';
import {map, Observable} from 'rxjs';
import {Request} from '../../model/request';
import {RequestService} from '../../../services/request.service';
import {FormBuilder} from '@angular/forms';
import {ApiResponse, RequestVolunteerHistory} from '../../model/request-volunteer-history';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    RouterLink,
    AsyncPipe,
    NgForOf,
    NgIf
  ],
  templateUrl: './home-admin.component.html',
  styleUrl: './home-admin.component.scss'
})
export class HomeAdminComponent implements OnInit {

  items: RequestVolunteerHistory[] = [];
  errorMessage: string = '';
  currentPage: number = 1;  // Tracks the current page
  totalItems: number = 0;   // Total number of items
  totalPages: number = 0;   // Total number of pages

  constructor(private adminService: AdminService) {
  }


  ngOnInit() {
    this.loadRequests(); // Load items when the component initializes
  }

  // Load the requests based on currentPage and itemsPerPage
  loadRequests() {
    this.adminService.getAllItems(this.currentPage,5).subscribe({
      next: (response: ApiResponse) => {
        this.items = response.content; // Items for the current page
        this.totalItems = response.totalElements; // Total number of items
        this.totalPages = response.totalPages; // Total number of pages
      },
      error: (error) => {
        this.errorMessage = 'Error fetching data';
      }
    });
  }

  // Navigate to the next page
  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.loadRequests();
    }
  }

  // Navigate to the previous page
  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.loadRequests();
    }
  }
}
