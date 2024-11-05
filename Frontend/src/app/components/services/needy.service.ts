import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Needy} from '../model/needy';


@Injectable({
  providedIn: 'root'
})
export class NeedyService{
  constructor(private http:HttpClient) { }

  public getAllNeedy():Observable<Array<Needy>>{
    return this.http.get<Array<Needy>>("/api/request")
  }
  /*public searchCustomers(keyword : string):Observable<Array<Needy>>{
    return this.http.get<Array<Needy>>(environment.backendHost+"/customers/search?keyword="+keyword)
  }*/
  public addNeedy(needy: Needy):Observable<Needy>{
    return this.http.post<Needy>("/api/needy",needy);
  }
  public deleteNeedyById(id: number) {
    return this.http.delete("/api/needy/${id}");
  }

}
