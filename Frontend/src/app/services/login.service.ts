import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from '../components/interfaces/login';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http:HttpClient) { }
  public login(login: Login): Observable<any> {
    return this.http.post<any>("authentication/login", login);
  }

}
