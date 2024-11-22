import { Component, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { Router } from "@angular/router";
import { Request } from '../../interfaces/request';
import { RequestService } from '../../../services/request.service';
import {AsyncPipe, DatePipe, NgForOf, NgIf} from '@angular/common';
import { map, Observable } from 'rxjs';
import {AuthService} from '../../../services/auth.service';
import {PaginatorModule} from 'primeng/paginator';

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
    PaginatorModule
  ]
})
export class NeedyHistoryComponent implements OnInit {
  requests: Request[] = [];
  totalRecords: number = 0; // Total number of requests
  pageSize: number = 5; // Items per page
  currentPage: number = 0; // Current page index
  errorMessage: string = '';

  constructor(
    private requestService: RequestService,
    private fb: FormBuilder,
    private router: Router,
    private authService: AuthService,
  ) { }

  ngOnInit() {
    console.log( this.authService.getUserId())
    this.loadRequests();
  }
  loadRequests(): void {
    const needyId = this.authService.getUserId();
    this.requestService.getAllRequests(needyId, this.currentPage, this.pageSize).subscribe({
      next: (data) => {
        this.requests = data.content;
        this.totalRecords = data.totalElements;
      },
      error: (err) => {
        console.error('Error loading requests:', err);
        this.errorMessage = 'Failed to load requests. Please try again later.';
      },
    });
  }

  onPageChange(event: any): void {
    this.currentPage = event.page;
    this.pageSize = event.rows;
    this.loadRequests();
  }

  handleDeleteRequest(requestId: number): void {
    const conf = confirm("Are you sure you want to delete this request?");
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
