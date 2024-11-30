import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Needy} from '../interfaces/needy';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http:HttpClient) { }
   email:string="";
  public addNeedy(needy: Needy):Observable<Needy>{
    return this.http.post<Needy>("http://localhost:8080/authentication/register",needy);
  }

}
