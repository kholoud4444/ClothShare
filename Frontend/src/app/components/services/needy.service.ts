import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Needy} from '../interfaces/needy';


@Injectable({
  providedIn: 'root'
})
export class NeedyService {
  constructor(private http:HttpClient) { }

  public addNeedy(needy: Needy):Observable<Needy>{
    return this.http.post<Needy>("http://localhost:8080/authentication/register",needy);
  }

}
