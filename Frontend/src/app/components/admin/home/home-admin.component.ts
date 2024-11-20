import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {AdminService} from '../../services/admin.service';
import {ApiResponse, RequestVolunteerHistory} from '../../interfaces/request-volunteer-history';

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
  allItems: RequestVolunteerHistory[] = []; // Holds all items
  items: RequestVolunteerHistory[] = []; // Current page items
  filteredItems: RequestVolunteerHistory[] = []; // Items after filtering
  errorMessage: string = '';
  currentPage: number = 0;
  itemsPerPage: number = 5;
  totalItems: number = 0;
  totalPages: number = 0;
  noItemsFound: boolean = false; // Tracks if no items are found after filtering

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadAllRequests(); // Load all items when the component initializes
  }

  // Load all items from the backend
  loadAllRequests() {
    this.adminService.getAllItems(0, 1000).subscribe({
      next: (response: ApiResponse) => {
        this.allItems = response.content; // Store all items
        this.applyFilter(); // Initialize with unfiltered items
      },
      error: err => {
        this.errorMessage = 'Error fetching data';
      },
    });
  }

  // Filter items by status
  filterByStatus(event: Event) {
    const selectedStatus = (event.target as HTMLSelectElement).value;
    if (selectedStatus) {
      this.filteredItems = this.allItems.filter(
        (item) => item.item.status === selectedStatus
      );
    } else {
      this.filteredItems = [...this.allItems]; // Reset to all items if no filter
    }

    // Handle case when no items are found after filtering
    this.noItemsFound = this.filteredItems.length === 0;

    this.currentPage = 0; // Reset to the first page
    this.updatePagination(); // Update displayed items and total pages
  }

  // Update pagination after filtering or navigation
  updatePagination() {
    this.totalItems = this.filteredItems.length;
    this.totalPages = Math.ceil(this.totalItems / this.itemsPerPage);

    const startIndex = this.currentPage * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.items = this.filteredItems.slice(startIndex, endIndex); // Paginate filtered items
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

  // Apply the initial filter (to load unfiltered items by default)
  applyFilter() {
    this.filteredItems = [...this.allItems]; // Start with all items
    this.updatePagination();
  }

  // Update the item status and reload data
  changeItemRequest(item: RequestVolunteerHistory, status: string) {
    const updatedItem = { ...item.item, status }; // Prepare updated data

    this.adminService.changeItemRequest(item.itemId, updatedItem).subscribe({
      next: () => {
        console.log(`Item ID ${item.itemId} status updated to ${status}`);
        this.loadAllRequests(); // Reload all items to reflect the change
      },
      error: (err) => {
        console.error('Error updating item:', err);
      },
    });
  }
}


