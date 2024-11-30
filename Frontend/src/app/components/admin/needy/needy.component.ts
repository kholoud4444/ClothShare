import { Component } from '@angular/core';
import {PrimeTemplate} from 'primeng/api';
import {TableModule} from 'primeng/table';
import {UserInterface} from '../../../interfaces/user-interface';
import {AdminService} from '../../../services/admin.service';
import {PaginatorModule} from "primeng/paginator";

@Component({
  selector: 'app-needy',
  standalone: true,
    imports: [
        PrimeTemplate,
        TableModule,
        PaginatorModule
    ],
  templateUrl: './needy.component.html',
  styleUrl: './needy.component.scss'
})
export class NeedyComponent {

  users: UserInterface[] = [];
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 5;
  loading: boolean = false;
  you: number=0;

  constructor(private userService: AdminService) {}

  ngOnInit() {
    this.getAllNeedy();
  }

  getAllNeedy() {
    this.loading = true;
    console.log('Fetching Needy...');
    this.userService.getAllUsersByRole('needy', this.pageNo, this.pageSize).subscribe({
      next: (response) => {
        console.log('Response:', response);  // Check if the response structure matches
        this.users = response.content;
        this.totalRecords = response.totalElements;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error fetching users:', err);
        this.loading = false;
      },
    });
  }

  onPageChange(event: any) {
    this.pageNo = event.first / event.rows;  // Correcting calculation
    this.pageSize = event.rows;
    this.getAllNeedy();
  }


}
