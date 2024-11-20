import { Component, OnInit } from '@angular/core';
import { FormBuilder } from "@angular/forms";
import { Router } from "@angular/router";
import { Request } from '../../interfaces/request';
import { RequestService } from '../../services/request.service';
import { AsyncPipe, NgForOf, NgIf } from '@angular/common';
import { map, Observable } from 'rxjs';
import {AuthService} from '../../services/auth.service';

@Component({
  selector: 'app-needy-history',
  templateUrl: './needy-history.component.html',
  standalone: true,
  styleUrls: ['./needy-history.component.scss'],
  imports: [
    NgForOf,
    AsyncPipe,
    NgIf
  ]
})
export class NeedyHistoryComponent implements OnInit {
  requests!: Observable<Array<Request>>;
  errorMessage!: string;

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
    this.requests = this.requestService.getAllRequests(this.authService.getUserId());
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
