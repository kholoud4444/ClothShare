import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Login} from '../interfaces/login';
import {VerifyEmail} from '../interfaces/verify-email';
import {VerifyResetPasswordToken} from '../interfaces/verify-reset-password-token';
import {CreateNewPassword} from '../interfaces/createNewPassword';



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
  public verifyResetTokenPassword(verifyResetPasswordToken:VerifyResetPasswordToken): Observable<any> {

    return this.http.put<any>("http://localhost:8080/authentication/verifyResetTokenPassword", verifyResetPasswordToken);
  }
  public updateNewPassword(updateNewPassword:CreateNewPassword): Observable<any> {

    return this.http.put<any>("http://localhost:8080/authentication/updateNewPassword", updateNewPassword);
  }



}
