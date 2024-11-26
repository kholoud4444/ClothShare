import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Request} from '../components/interfaces/request';


@Injectable({
  providedIn: 'root'
})
export class RequestService {
  constructor(private http:HttpClient) { }
  public getAllRequests(needyId: number, page: number, size: number): Observable<{ content: Array<Request>, totalElements: number }> {
    return this.http
      .get<{ content: Array<Request>, totalElements: number }>(
        `http://localhost:8080/needy/RequestsWithItemDetails/${needyId}?pageNo=${page}&pageSize=${size}`
      );
  }

  public deleteRequestById(requestId: number): Observable<string> {
    return this.http.delete(`http://localhost:8080/request/${requestId}`, { responseType: 'text' });
  }
  getPhoto(fileName: string): Observable<string> {
    return this.http
      .get(`http://localhost:8080/item/getPhoto/${fileName}`, { responseType: 'blob' })
      .pipe(
        map((blob) => URL.createObjectURL(blob)) // Convert Blob to object URL
      );
  }
}
