import {Component, OnInit} from '@angular/core';
import {ButtonDirective} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf, NgOptimizedImage} from '@angular/common';
import {jwtDecode} from 'jwt-decode';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {ItemService} from '../../../services/item.service';
import {ItemDto} from '../../interfaces/item-dto';
@Component({
  selector: 'app-item',
  standalone: true,
  imports: [
    ButtonDirective,
    FormsModule,
    NgIf,
    NgForOf,
    NgOptimizedImage,
    RouterLink
  ],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent implements OnInit {

  items: ItemDto[] = []; // Holds raw item data
  itemsWithPhotos: Array<{ item: ItemDto; photoUrl: string }> = []; // Combines items and photos
  currentPage = 0;
  itemsPerPage = 4;
  totalPages = 0;

  constructor(private itemService: ItemService) {}

  ngOnInit() {
    this.fetchItems();
  }

  // Fetch items with pagination
  fetchItems() {
    this.itemService.getAllItems(this.currentPage, this.itemsPerPage).subscribe({
      next: (response) => {
        this.items = response.content; // Assuming the response includes 'items'
        this.totalPages = response.totalElements; // Assuming the response includes 'totalPages'

        // Clear previous photos and fetch new ones
        this.itemsWithPhotos = [];
        this.items.forEach((item) => {
          this.itemService.getPhoto(item.imageUrl).subscribe({
            next: (photoUrl) => {
              this.itemsWithPhotos.push({ item, photoUrl });
            },
            error: (err) => {
              console.error(`Failed to fetch photo for ${item.imageUrl}`, err);
            },
          });
        });
      },
      error: (err) => {
        console.error('Failed to fetch items', err);
      },
    });
  }

  // Handle pagination
  goToPage(pageIndex: number) {
    if (pageIndex >= 0 && pageIndex < this.totalPages) {
      this.currentPage = pageIndex;
      this.fetchItems();
    }
  }
}
