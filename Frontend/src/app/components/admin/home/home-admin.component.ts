import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from '@angular/router';
import {AsyncPipe, NgForOf, NgIf} from '@angular/common';
import {AdminService} from '../../../services/admin.service';
import {ApiResponse, RequestVolunteerHistory} from '../../interfaces/request-volunteer-history';
import {RequestService} from '../../../services/request.service';
import {PaginatorModule} from "primeng/paginator";

@Component({
  selector: 'app-home',
  standalone: true,
    imports: [
        RouterLink,
        AsyncPipe,
        NgForOf,
        NgIf,
        PaginatorModule
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
  noItemsFound: boolean = false;
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 5;
  loading: boolean = false;

  constructor(private adminService: AdminService,private requestService: RequestService) {}

  ngOnInit() {
    this.loadAllRequests(); // Load all items when the component initializes
  }

  // Load all items from the backend
  loadAllRequests() {
    this.adminService.getAllItems(0, 10000).subscribe({
      next: (response: ApiResponse) => {
        this.allItems = response.content; // Store all items
        this.applyFilter();

        this.allItems.forEach(item => {
          // Call getPhoto method to fetch the image asynchronously
          this.requestService.getPhoto(item.item.imageUrl).subscribe(imageUrl => {
            item.item.imageUrl = imageUrl; // Set the fetched image URL for each item
          });
        });// Initialize with unfiltered items
      },
      error: err => {
        this.errorMessage = 'حدث خطأ في التحميل';
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
    const startIndex = this.pageNo * this.pageSize;
    const endIndex = startIndex + this.pageSize;
    this.items = this.filteredItems.slice(startIndex, endIndex);
    this.totalRecords = this.filteredItems.length;  // Update totalRecords for paginator
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
    let confirmationMessage = '';
    let successMessage = '';

    // Decide the confirmation message based on the status
    if (status === 'تم_الموافقه') {
      confirmationMessage = 'هل أنت متأكد من قبول طلب هذا الملبس؟';
      successMessage = 'تم الموافقة على طلب الملبس';
    } else if (status === 'مرفوض') {
      confirmationMessage = 'هل أنت متأكد من رفض  طلب هذا الملبس؟';
      successMessage = 'تم رفض طلب الملبس';
    } else {
      confirmationMessage = 'هل أنت متأكد من تحديث حالة العنصر؟';
      // Default message
    }

    // Show the confirmation dialog
    const isConfirmed = window.confirm(confirmationMessage);

    if (isConfirmed) {
      const updatedItem = { ...item.item, status };

      // Make sure the service call happens only after the confirmation
      this.adminService.changeItemRequest(item.itemId, updatedItem).subscribe({
        next: () => {
          console.log(`تم تحديث حالة العنصر ID ${item.itemId} إلى: ${status}`);
          alert(successMessage); // Show success alert
          this.loadAllRequests(); // Reload items after the update
        },
        error: (err) => {
          console.error('حدث خطأ أثناء تحديث العنصر:', err);
        },
      });
    } else {
      console.log('تم إلغاء التحديث.');
    }
  }


  onPageChange(event: any): void {
    this.pageNo = event.first / event.rows; // Correcting page number calculation
    this.pageSize = event.rows;
    this.loadAllRequests();
  }


}
