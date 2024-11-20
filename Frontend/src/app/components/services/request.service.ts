import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {Request} from '../interfaces/request';


@Injectable({
  providedIn: 'root'
})
export class RequestService {
  constructor(private http:HttpClient) { }
  public getAllRequests(needyId: number): Observable<Array<Request>> {
    return this.http
      .get<{ content: Array<Request> }>(`needy/RequestsWithItemDetails/${needyId}`)
      .pipe(map(response => response.content)); // Extract the content array
  }


  /*public searchCustomers(keyword : string):Observable<Array<Request>>{
    return this.http.get<Array<Request>>(environment.backendHost+"/customers/search?keyword="+keyword)
  }*/
  public addrequest(request: Request):Observable<Request>{
    return this.http.post<Request>("/api/registration",request);
  }
  public deleteRequestById(requestId: number): Observable<string> {
    return this.http.delete(`request/${requestId}`, { responseType: 'text' });
  }

}
