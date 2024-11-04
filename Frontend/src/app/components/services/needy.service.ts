import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Needy} from '../model/needy';
import {environment} from '../../../environment/environment';


@Injectable({
  providedIn: 'root'
})
export class NeedyService{
  constructor(private http:HttpClient) { }

  public getAllNeedy():Observable<Array<Needy>>{
    return this.http.get<Array<Needy>>(environment.backendHost+"/api/needy")
  }
  /*public searchCustomers(keyword : string):Observable<Array<Needy>>{
    return this.http.get<Array<Needy>>(environment.backendHost+"/customers/search?keyword="+keyword)
  }*/
  public addNeedy(needy: Needy):Observable<Needy>{
    return this.http.post<Needy>(environment.backendHost+"/api/needy",needy);
  }
  public deleteNeedyById(id: number){
    return this.http.delete(`${environment.backendHost}/api/needy/${id}`);
  }
}
