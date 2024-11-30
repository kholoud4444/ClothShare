import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserInterface} from '../interfaces/user-interface';
@Injectable({
  providedIn: 'root'
})
export class ProfilerService {
  constructor(private http:HttpClient) { }

  public updateUser(updatedUser: UserInterface, userId: number): Observable<UserInterface> {
    return this.http.put<UserInterface>(`http://localhost:8080/user/${userId}`, updatedUser);
  }

  public deleteUser(userId: number): Observable<string> {
    return this.http.delete(`http://localhost:8080/user/${userId}`, { responseType: 'text' });
  }

  public getUserById(userId: number): Observable<UserInterface> {
      return this.http.get<UserInterface>(`http://localhost:8080/user/details/${userId}`);
  }
}
