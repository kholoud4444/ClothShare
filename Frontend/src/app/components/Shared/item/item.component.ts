import {Component, OnInit} from '@angular/core';
import {Button, ButtonDirective} from 'primeng/button';
import {FormsModule} from '@angular/forms';
import {NgForOf, NgIf, NgOptimizedImage} from '@angular/common';
import {ActivatedRoute, RouterLink} from '@angular/router';
import {ItemService} from '../../../services/item.service';
import {ItemDtoForProduct} from '../../interfaces/item-dto-for-product';
import {AuthService} from '../../../services/auth.service';
import {Request} from '../../interfaces/request';
import {CreateRequest} from '../../interfaces/CreateRequest';

@Component({
  selector: 'app-item',
  standalone: true,
  imports: [
    ButtonDirective,
    FormsModule,
    NgIf,
    NgForOf,
    NgOptimizedImage,
    RouterLink,
    Button
  ],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent implements OnInit {

  itemId!: number;
  item!: ItemDtoForProduct; // Item details to display
  loading: boolean = false;
  userRole: string | null = null;
  reason: string = '';

  constructor(private route: ActivatedRoute, private itemService: ItemService,private authService:AuthService) {}

  ngOnInit(): void {
    this.userRole=this.authService.getUserRole();

    // Get the itemId from the route parameters
    this.route.params.subscribe((params) => {
      this.itemId = +params['id']; // Convert 'id' to a number
      this.getItemDetails();
    });
  }


  // Fetch item details from the service
  getItemDetails(): void {
    this.loading = true;
    this.itemService.getItemById(this.itemId).subscribe({
      next: (item) => {
        this.item = item;
        this.loading = false;

        // Fetch the image asynchronously
        if (item.imageUrl) {
          this.itemService.getPhoto(item.imageUrl).subscribe({
            next: (imageUrl) => {
              this.item.imageUrl = imageUrl; // Update the item's image URL
            },
            error: (err) => {
              console.error('Error fetching image:', err);
            }
          });
        }
      },
      error: (err) => {
        console.error('Error fetching item details:', err);
        this.loading = false;
      }
    });
  }

  submitRequest(): void {
    if (!this.reason.trim()) {
      alert('يرجى كتابة سبب الطلب.');
      return;
    }

    const requestPayload: CreateRequest = {
      date: new Date().toISOString().split('T')[0], // Current date in YYYY-MM-DD format
      status: 'قيد_المراجعه', // Default status
      reason: this.reason.trim(), // Reason from the form
      needyId: this.authService.getUserId(), // Replace with dynamic logic to get the logged-in user's ID
      itemId: this.itemId, // Item ID from the current item
    };

    this.itemService.CreateRequest(requestPayload).subscribe({
      next: (response) => {
        console.log('Request submitted successfully:', response);
        alert('تم إرسال الطلب بنجاح!');
        this.reason = ''; // Clear the reason field after successful submission
      },
      error: (err) => {
        console.error('Error submitting request:', err);
        alert('لقد طلبت هذا العنصر من قبل');
      },
    });
  }

}
