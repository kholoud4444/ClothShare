import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {map, Observable} from 'rxjs';
import {ItemDto} from '../components/interfaces/item-dto';
import {PageDto} from '../components/interfaces/volunteer';

import {ItemDtoForProduct} from '../components/interfaces/item-dto-for-product';
import {CreateRequest} from '../components/interfaces/CreateRequest';

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
export class ItemService {


  constructor(private http: HttpClient) {
  }

  // Fetch all items
  getAllItems(pageNo: number, pageSize: number): Observable<PageDto<ItemDtoForProduct>> {

    return this.http.get<PageDto<ItemDtoForProduct>>(`item/all`, {params: {pageNo: pageNo, pageSize: pageSize}})
  }
  getPhoto(fileName: string): Observable<string> {
    return this.http
      .get(`item/getPhoto/${fileName}`, { responseType: 'blob' })
      .pipe(
        map((blob) => URL.createObjectURL(blob)) // Convert Blob to object URL
      );
  }
  getItemById(id: number) {

    return this.http.get<ItemDtoForProduct>(`item/${id}`)
  }
  CreateRequest(request: CreateRequest): Observable<CreateRequest> {

    return this.http.post<CreateRequest>(`request`,request);
  }
}



