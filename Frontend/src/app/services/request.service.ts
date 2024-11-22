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
        `needy/RequestsWithItemDetails/${needyId}?page=${page}&size=${size}`
      );
  }
  public addrequest(request: Request):Observable<Request>{
    return this.http.post<Request>("/api/registration",request);
  }
  public deleteRequestById(requestId: number): Observable<string> {
    return this.http.delete(`request/${requestId}`, { responseType: 'text' });
  }

}
