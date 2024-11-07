import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Needy} from '../components/model/needy';


@Injectable({
  providedIn: 'root'
})
export class NeedyService {
  constructor(private http:HttpClient) { }

  public addNeedy(needy: Needy):Observable<Needy>{
    return this.http.post<Needy>("/api/registration",needy);
  }
}
