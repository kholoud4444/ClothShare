import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Request} from '../components/model/request';


@Injectable({
  providedIn: 'root'
})
export class RequestService {
  constructor(private http:HttpClient) { }

  public getAllRequests():Observable<Array<Request>>{
    return this.http.get<Array<Request>>("/api/request")
  }
  /*public searchCustomers(keyword : string):Observable<Array<Request>>{
    return this.http.get<Array<Request>>(environment.backendHost+"/customers/search?keyword="+keyword)
  }*/
  public addrequest(request: Request):Observable<Request>{
    return this.http.post<Request>("/api/registration",request);
  }
  public deleteRequestById(requestId: number) {
    return this.http.delete("/api/request/${requestId}");
  }
}
