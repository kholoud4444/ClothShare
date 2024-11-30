import { Component, OnInit } from '@angular/core';
import { Request } from '../../../interfaces/request';

import {AsyncPipe, DatePipe, NgForOf, NgIf} from '@angular/common';

import {PaginatorModule} from 'primeng/paginator';
import {TableModule} from 'primeng/table';
import {RequestService} from '../../../services/request.service';
import {AuthService} from '../../../services/auth.service';

@Component({
  selector: 'app-needy-history',
  templateUrl: './needy-history.component.html',
  standalone: true,
  styleUrls: ['./needy-history.component.scss'],
  imports: [
    NgForOf,
    AsyncPipe,
    NgIf,
    DatePipe,
    PaginatorModule,
    TableModule
  ]
})
export class NeedyHistoryComponent implements OnInit {
  requests: Request[] = [];
  totalRecords: number = 0; // Total number of requests
  pageSize: number = 5; // Items per page
  currentPage: number = 0; // Current page index
  errorMessage: string = '';
  loading: boolean = false; // Indicates table loading state


  constructor(
    private requestService: RequestService,
    private authService: AuthService,
  ) { }

  ngOnInit() {
    console.log( this.authService.getUserId())
    this.loadRequests();
  }
  loadRequests(): void {
this.loading=true;
    const needyId = this.authService.getUserId();
    this.requestService.getAllRequests(needyId, this.currentPage, this.pageSize).subscribe({
      next: (data) => {

        this.requests = data.content;
        this.totalRecords = data.totalElements;
        this.requests.forEach(item => {
          // Call getPhoto method to fetch the image asynchronously
          this.requestService.getPhoto(item.itemData.imageUrl).subscribe(imageUrl => {
            item.itemData.imageUrl = imageUrl; // Set the fetched image URL for each item
          });
        });
        this.loading=false;
      },
      error: (err) => {
        console.error('Error loading requests:', err);
        this.errorMessage = 'Failed to load requests. Please try again later.';

        this.loading=false;
      },
    });
  }

  onPageChange(event: any): void {
    this.currentPage = event.first / event.rows;
    this.pageSize = event.rows;
    this.loadRequests();
  }

  handleDeleteRequest(requestId: number): void {
    const conf = confirm("هل انت متأكد انك تريد حذف هذا العنصر؟");
    if (!conf) return;

    this.requestService.deleteRequestById(requestId).subscribe({
      next: (response) => {
        console.log(`Response from server: ${response}`); // Should log "Deleted"
        this.loadRequests(); // Reload the list of requests
      },
      error: (err) => {
        console.error("Error while deleting request:", err);
        this.errorMessage = "Failed to delete the request. Please try again.";
      },
    });
  }


}
