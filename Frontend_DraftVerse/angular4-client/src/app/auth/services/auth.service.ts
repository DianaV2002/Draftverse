import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { SignUpInfo } from '../models/sign-up.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = '/api/auth';

  constructor(private http: HttpClient) {}
  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, credentials).pipe(
      catchError(error => {
        console.error('SignIn failed', error);
        return of({ success: false, message: 'SignIn failed' });
      })
    );;
  }

  register(signUpData: SignUpInfo): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/sign-up`, signUpData).pipe(
      catchError(error => {
        console.error('Signup failed', error);
        return of({ success: false, message: 'Signup failed' });
      })
    );
  }
}
