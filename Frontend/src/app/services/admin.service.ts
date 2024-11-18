import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import {
  ApiResponse,
  ClothingSize, ClothingType,
  GenderSuitability, ItemState,
  RequestVolunteerHistory
} from '../components/model/request-volunteer-history';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  private readonly baseUrl = '/api/admin';

  constructor(private http: HttpClient) {}

  /**
   * Fetches all items with pagination.
   * @param page The page number to fetch.
   * @param size The number of items per page.
   * @returns An Observable of the API response.
   */
  getAllItems(page: number, size: number): Observable<ApiResponse> {
    const url = `${this.baseUrl}/allItems?pageNo=${page}&pageSize=${size}`;
    return this.http.get<ApiResponse>(url).pipe(
      catchError((error) => {
        console.error('Error fetching items:', error);
        return throwError(() => new Error('Failed to fetch items.'));
      })
    );
  }

  /**
   * Updates the status of a specific item.
   * @param id The ID of the item to update.
   * @param updatedItem The updated item data.
   * @returns An Observable of the server's response.
   */
  changeItemRequest(id: number, updatedItem: {
    genderSuitability: GenderSuitability;
    size: ClothingSize;
    imageUrl: string;
    description: string;
    state: ItemState;
    type: ClothingType;
    volunteerId?: number;
    status: string
  }): Observable<string> {
    const url = `${this.baseUrl}/changeItemStatus/${id}`;
    return this.http.put<string>(url, updatedItem).pipe(
      catchError((error) => {
        console.error(`Error updating item with ID ${id}:`, error);
        return throwError(() => new Error('Failed to update item status.'));
      })
    );
  }
}
