import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

import {Needy} from '../components/interfaces/needy';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http:HttpClient) { }

  public addNeedy(needy: Needy):Observable<Needy>{
    return this.http.post<Needy>("authentication/register",needy);
  }

}
