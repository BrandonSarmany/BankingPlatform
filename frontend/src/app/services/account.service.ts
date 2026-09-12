import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Account } from '../models/account';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AccountService{
  constructor(private http: HttpClient) {

  }

  getAccounts(): Observable<Account[]>{
    return this.http.get<Account[]>('http://localhost:8080/accounts');
  }

  deposit(accountId: number, amount: number): Observable<Account> {
    return this.http.post<Account>(
      `http://localhost:8080/accounts/${accountId}/deposit`,
      { amount }
    );
  }
}
