import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormGroup} from '@angular/forms';

export interface Product {
  name: string;
  image: string;
  price: number;
  category: string;
  rating: number;
  inventoryStatus: string;
}

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  //  private apiUrl = 'https://fakestoreapi.com/products'; // Replace with your fake API URL
   private apiUrl = 'https://dummyjson.com/products'; // Replace with your fake API URL

  private baseUrl = 'http://localhost:8080'

  constructor(private http: HttpClient) { }

  getProducts(): Observable<{ products: Product[] }> {
    return this.http.get<{ products: Product[] }>(this.apiUrl);
  }


  uploadImage(imageFile: File, form: any): Observable<any> {
    const formData = new FormData();
    formData.append('image', imageFile);
    formData.append('data', JSON.stringify(form));

    return this.http.post(this.baseUrl + '/api/item', formData);
  }
}



