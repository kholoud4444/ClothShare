import {Component, inject, OnInit} from '@angular/core';
import {TableModule} from 'primeng/table';

import {AdminService} from '../../../services/admin.service';
import {UserInterface, userResponseDetails} from '../../interfaces/user-interface';



@Component({
  selector: 'app-volunteer',
  standalone: true,
  imports: [
    TableModule
  ],
  templateUrl: './volunteer.component.html',
  styleUrl: './volunteer.component.scss'
})

export class VolunteerComponent implements OnInit {
  users: UserInterface[] = [];
  totalRecords: number = 0;
  pageNo: number = 0;
  pageSize: number = 5;
  loading: boolean = false;


  constructor(private userService: AdminService) {}

  ngOnInit() {
    this.getAllVolunteer();
  }

  getAllVolunteer() {
    this.loading = true;
    console.log('Fetching volunteers...');
    this.userService.getAllUsersByRole('volunteer', this.pageNo, this.pageSize).subscribe({
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
    this.getAllVolunteer();
  }


}
// git
