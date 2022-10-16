import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:9090/user/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials:any): Observable<any> {
    return this.http.post(AUTH_API + 'login', {
      username: credentials.email,
      password: credentials.password
    }, httpOptions);
  }

  register(user:any): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      name: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}