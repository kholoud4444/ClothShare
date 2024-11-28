import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {MatSnackBarRef} from '@angular/material/snack-bar';

@Component({
  selector: 'app-snackbar-message',
  standalone: true,
  imports: [
    MatButton
  ],
  templateUrl: './snackbar-message.component.html',
  styleUrl: './snackbar-message.component.scss'
})
export class SnackbarMessageComponent {
  constructor(private snackBarRef: MatSnackBarRef<SnackbarMessageComponent>) {}

  closeSnackbar() {
    this.snackBarRef.dismiss();
  }
}
