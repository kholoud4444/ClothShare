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
    this.loadRequests();
  }

  loadRequests(): void {
    this.requests = this.requestService.getAllRequests(  this.authService.getUserId());

  }

  // handleDeleteRequest(n: Request) {
  //   let conf = confirm("Are you sure?");
  //   if (!conf) return;
  //   this.requestService.deleteRequestById(n.requestId).subscribe({
  //     next: () => {
  //       this.loadRequests(); // Reload requests after deletion
  //     },
  //     error: err => {
  //       console.log(err);
  //     }
  //   });
  // }
}
