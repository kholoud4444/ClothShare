import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {ApiResponse} from '../components/interfaces/request-volunteer-history';
import {userResponseDetails} from '../components/interfaces/user-interface';



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

  public getAllVolunteer(role: string ,pageNo: number, pageSize: number): Observable<userResponseDetails> {
    // const params = new HttpParams().set('pageNo',pageNo ).set('pageSize',pageSize);

    return this.http.get<userResponseDetails>(`user/getAllByRole/${role}`,{params:{pageNo:pageNo, pageSize:pageSize}});
  }
}
