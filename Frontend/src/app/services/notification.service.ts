import { Injectable } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private snackBar: MatSnackBar) {}

  showError(message: string): void {
    this.snackBar.open(message, 'Close', {
      duration: 2500,           // Toast duration in milliseconds
      horizontalPosition: 'right',
      verticalPosition: 'top',
      panelClass: ['error-snackbar']  // Apply custom styling if desired
    });
  }
}
