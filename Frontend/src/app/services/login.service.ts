import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from '../components/interfaces/login';
import {VerifyEmailComponent} from '../components/auth/verify-email/verify-email.component';
import {VerifyEmail} from '../components/interfaces/verify-email';


@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http:HttpClient) { }
  public login(login: Login): Observable<any> {
    return this.http.post<any>("http://localhost:8080/authentication/login", login);
  }


  public validateEmailVerificationToken(verifyEmail:VerifyEmail): Observable<any> {

    return this.http.put<any>("http://localhost:8080/authentication/validateEmailVerificationToken", verifyEmail);
  }
  public sendEmailVerificationToken(email:String): Observable<any> {

    return this.http.put<any>("http://localhost:8080/authentication/sendEmailVerificationToken", email);
  }
  public sendPasswordResetToken(email:String): Observable<any> {

    return this.http.put<any>("http://localhost:8080/authentication/sendPasswordResetToken", email);
  }



}
