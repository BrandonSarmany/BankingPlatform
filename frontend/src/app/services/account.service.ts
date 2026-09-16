import { Injectable } from '@angular/core';
import { Account } from '../models/account';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AccountService{
  constructor(private http: HttpClient) {
  }
  private getAuthHeaders(): HttpHeaders {
    //Demo-only Basic authentication
    return new HttpHeaders({
      Authorization: 'Basic ' + btoa('demo:demo')
    });
  }

  getAccounts(): Observable<Account[]> {
    return this.http.get<Account[]>(
      'http://localhost:8080/accounts',
      { headers: this.getAuthHeaders() }
    );
  }

  deposit(accountId: number, amount: number): Observable<Account> {
    return this.http.post<Account>(
      `http://localhost:8080/accounts/${accountId}/deposit`,
      { amount: amount },
      { headers: this.getAuthHeaders() }
    );
  }
}
