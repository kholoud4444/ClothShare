import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiResponse} from '../components/model/request-volunteer-history';


@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http:HttpClient) { }

  public getAllItems(page: number, size: number): Observable<ApiResponse> {
    return this.http.get<ApiResponse>(`/api/admin/allItems?pageNo=${page}&pageSize=${size}`);
  }

  public changeItemRequest(id: number, updatedItem: any): Observable<string> {
    return this.http.put<string>(`/api/admin/changeItemStatus/${id}`, updatedItem);
  }





}
