// home-admin.component.ts
import { Component, OnInit } from '@angular/core';
import { CommonModule, AsyncPipe, NgForOf, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { SidebarModule } from 'primeng/sidebar';
import { MenuModule } from 'primeng/menu';
import { CardModule } from 'primeng/card';
import { DropdownModule, DropdownChangeEvent } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { ImageModule } from 'primeng/image';
import { TagModule } from 'primeng/tag';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { MessageModule } from 'primeng/message';
import { AdminService } from '../../../services/admin.service';
import {ApiResponse, ItemStatus, RequestVolunteerHistory} from '../../model/request-volunteer-history';

@Component({
  selector: 'app-home-admin',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink,
    AsyncPipe,
    NgForOf,
    NgIf,
    SidebarModule,
    MenuModule,
    CardModule,
    DropdownModule,
    TableModule,
    ImageModule,
    TagModule,
    ButtonModule,
    TooltipModule,
    MessageModule
  ],
  templateUrl: './home-admin.component.html',
  styleUrl: './home-admin.component.scss'
})
export class HomeAdminComponent implements OnInit {
  allItems: RequestVolunteerHistory[] = [];
  items: RequestVolunteerHistory[] = [];
  filteredItems: RequestVolunteerHistory[] = [];
  errorMessage: string = '';
  currentPage: number = 0;
  itemsPerPage: number = 5;
  totalItems: number = 0;
  totalPages: number = 0;
  noItemsFound: boolean = false;

  sidebarVisible: boolean = false;
  loading: boolean = false;
  selectedStatus: string = '';

  menuItems: MenuItem[] = [
    {
      label: 'المستفيدين',
      icon: 'pi pi-users',
      routerLink: '/overview'
    },
    {
      label: 'المتطوعين',
      icon: 'pi pi-heart',
      routerLink: '/users'
    },
    {
      label: 'الملابس',
      icon: 'pi pi-shopping-bag',
      routerLink: '/reports'
    },
    {
      label: 'الطلبات',
      icon: 'pi pi-list',
      routerLink: '/settings'
    }
  ];

  statusOptions = [
    { label: 'الكل', value: '' },
    { label: 'تم الموافقة', value: 'تم_الموافقه' },
    { label: 'معلق', value: 'معلق' },
    { label: 'مرفوض', value: 'مرفوض' }
  ];

  constructor(private adminService: AdminService) {}

  ngOnInit() {
    this.loadAllRequests();
  }

  getStatusSeverity(status: ItemStatus): "success" | "secondary" | "info" | "warning" | "danger"  {
    switch (status) {
      case 'تم_الموافقه':
        return 'success';
      case 'معلق':
        return 'warning';
      case 'مرفوض':
        return 'danger';
      default:
        return 'info';
    }
  }

  loadAllRequests() {
    this.loading = true;
    this.adminService.getAllItems(0, 1000).subscribe({
      next: (response: ApiResponse) => {
        this.allItems = response.content;
        this.applyFilter();
        this.loading = false;
      },
      error: (error) => {
        this.errorMessage = 'Error fetching data';
        this.loading = false;
      },
    });
  }

  filterByStatus(event: DropdownChangeEvent) {
    const selectedStatus = event.value;
    if (selectedStatus) {
      this.filteredItems = this.allItems.filter(
        (item) => item.item.status === selectedStatus
      );
    } else {
      this.filteredItems = [...this.allItems];
    }

    this.noItemsFound = this.filteredItems.length === 0;
    this.currentPage = 0;
    this.updatePagination();
  }

  updatePagination() {
    this.totalItems = this.filteredItems.length;
    this.totalPages = Math.ceil(this.totalItems / this.itemsPerPage);

    const startIndex = this.currentPage * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.items = this.filteredItems.slice(startIndex, endIndex);
  }

  nextPage() {
    if (this.currentPage < this.totalPages - 1) {
      this.currentPage++;
      this.updatePagination();
    }
  }

  previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.updatePagination();
    }
  }

  goToPage(pageIndex: number) {
    if (pageIndex >= 0 && pageIndex < this.totalPages) {
      this.currentPage = pageIndex;
      this.updatePagination();
    }
  }

  applyFilter() {
    this.filteredItems = [...this.allItems];
    this.updatePagination();
  }

  changeItemRequest(item: RequestVolunteerHistory, status: string) {
    this.loading = true;
    const updatedItem = { ...item.item, status };

    this.adminService.changeItemRequest(item.itemId, updatedItem).subscribe({
      next: () => {
        this.loadAllRequests();
      },
      error: (err) => {
        console.error('Error updating item:', err);
        this.loading = false;
      },
    });
  }
}
