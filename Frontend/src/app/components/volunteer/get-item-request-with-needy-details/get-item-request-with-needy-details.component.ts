import { Component } from '@angular/core';
import {Button} from 'primeng/button';
import {NgClass, NgForOf, NgIf, NgOptimizedImage} from '@angular/common';
import {PaginatorModule} from 'primeng/paginator';
import {RequestWithNeedyInfo} from '../../../interfaces/request-with-needy-info';
import {VolunteerService} from '../../../services/volunteer.service';
import {RequestDto, RequestStatus} from '../../../interfaces/request-dto';
import {RequestStatusDto} from '../../../interfaces/request-status-dto';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-get-item-request-with-needy-details',
  standalone: true,
  imports: [
    Button,
    NgForOf,
    NgIf,
    NgOptimizedImage,
    PaginatorModule,
    NgClass
  ],
  templateUrl: './get-item-request-with-needy-details.component.html',
  styleUrl: './get-item-request-with-needy-details.component.scss'
})
export class GetItemRequestWithNeedyDetailsComponent {
  requestsWithNeedyInfo: RequestWithNeedyInfo[] = [];
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 10;
  itemId!: number;
  loading: boolean = false;
  requestStatus:String = "";
  num: number = 0;
  currentPage: number = 1; // Current page number
  constructor(private route: ActivatedRoute,private volunteerService: VolunteerService) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      this.itemId = +params['id']; // Convert 'id' to a number
      this.loadRequests();
    });
  }

  loadRequests(): void {
    this.volunteerService.getItemRequestsWithNeedyInfo(this.itemId, this.pageNo, this.pageSize)
      .subscribe({
        next: (response) => {
          this.requestsWithNeedyInfo = response.content;
          this.totalRecords = response.totalElements;
          this.loading = false;
        }
      })
  }
  onPageChange(event: any): void {
    this.pageNo = event.page;
    this.pageSize = event.rows;
    this.loadRequests(); // Reload data based on the page change
  }

  acceptRequest(requestId: number) {
    const updatedRequestStatus: RequestStatusDto = {
      status: RequestStatus.تم_الموافقه,


    };

    this.volunteerService.changeRequestStatus(requestId, updatedRequestStatus).subscribe(
      (response) => {
        console.log('Request accepted successfully:', response);
        // Refresh or update the UI here
        this.loadRequests();
        alert('تم قبول الطلب بنجاح');
      },
      (error) => {
        console.error('Error accepting request:', error);
      }
    );
  }

  refuseRequest(requestId: number) {
    const updatedRequestStatus: RequestStatusDto = {
      status: RequestStatus.مرفوض, // For "Accepted"


    };

    this.volunteerService.changeRequestStatus(requestId, updatedRequestStatus).subscribe(
      (response) => {
        console.log('Request refused successfully:', response);
        debugger

        alert('تم حذف الطلب بنجاح');
        // Refresh or update the UI here
        this.loadRequests();
      },
      (error) => {
        console.error('Error refusing request:', error);
      }
    );
  }

  trackByRequestId(index: number, request: RequestWithNeedyInfo): number {
    return request.requestId;  // Track by requestId for better performance
  }
}


