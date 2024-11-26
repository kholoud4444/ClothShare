import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../../services/auth.service';
import { ProfilerService } from '../../../services/profile.service';
import { InputTextModule } from 'primeng/inputtext';
import { Button } from 'primeng/button';
import { CardModule } from 'primeng/card';
import {NgForOf, NgIf} from '@angular/common';
import { MessageService } from 'primeng/api';
import {UserInterface} from '../../interfaces/user-interface'; // For notifications

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    FormsModule,
    InputTextModule,
    Button,
    CardModule,
    NgIf,
    NgForOf,
  ],
  providers: [MessageService], // Added for user feedback
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss'],
})
export class ProfileComponent implements OnInit {
  user: UserInterface | null = null;
  isEditing = false;
  editProfileForm = { firstName: '',lastName:'',location:'',phone:'', email: '' };

  constructor(
    private authService: AuthService,
    private profileService: ProfilerService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.fetchUserDetails();
  }

  fetchUserDetails(): void {
    const userId = this.authService.getUserId();
    this.profileService.getUserById(userId).subscribe({
      next: (response: UserInterface) => {
        this.user = response;
        this.editProfileForm.firstName = response.firstName;
        this.editProfileForm.lastName=response.lastName;
        this.editProfileForm.location=response.location;
        this.editProfileForm.phone=response.phone;
      },
      error: (err) => {
        console.error('Error fetching user details:', err);
        this.showMessage('error', 'خطأ', 'فشل في استرداد بيانات المستخدم');
      },
    });
  }

  toggleEditMode(): void {
    this.isEditing = true;
  }

  cancelEdit(): void {
    this.isEditing = false;
    if (this.user) {
      this.editProfileForm.firstName = this.user.firstName;
      this.editProfileForm.lastName = this.user.lastName;
      this.editProfileForm.location = this.user.location;
      this.editProfileForm.phone = this.user.phone;
    }
  }

  saveChanges(): void {
    if (!this.user) {
      this.showMessage('error', 'خطأ', 'بيانات المستخدم غير متوفرة');
      return;
    }

    const updatedUser: UserInterface = {
      ...this.user,
      firstName: this.editProfileForm.firstName,
      lastName: this.editProfileForm.lastName,
      location: this.editProfileForm.location,
      phone: this.editProfileForm.phone,
    };

    this.profileService.updateUser(updatedUser, this.user.userId).subscribe({
      next: (response) => {
        this.user = response;
        this.isEditing = false;
        this.showMessage('success', 'نجاح', 'تم تحديث البيانات بنجاح');
      },
      error: (err) => {
        console.error('Error updating user profile:', err);
        this.showMessage('error', 'خطأ', 'فشل في تحديث البيانات');
      },
    });
  }

  deleteUser(): void {
    if (!this.user) {
      this.showMessage('error', 'خطأ', 'بيانات المستخدم غير متوفرة');
      return;
    }

    const confirmed = confirm('هل أنت متأكد أنك تريد حذف هذا الحساب؟');
    if (confirmed) {
      this.profileService.deleteUser(this.user.userId).subscribe({
        next: () => {
          this.showMessage('success', 'نجاح', 'تم حذف الحساب بنجاح');
          this.user = null;
          this.authService.logout();
        },
        error: (err) => {
          console.error('Error deleting user:', err);
          this.showMessage('error', 'خطأ', 'فشل في حذف الحساب');
        },
      });
    }
  }

  private showMessage(severity: string, summary: string, detail: string): void {
    this.messageService.add({ severity, summary, detail });
  }

}
