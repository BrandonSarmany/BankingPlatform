import { Component, OnInit,  signal } from '@angular/core';
import { AccountService } from './services/account.service';
import { Account } from './models/account';

@Component({
  selector: 'app-root',
  styleUrl: './app.css',
  templateUrl: './app.html',
})
export class App implements OnInit {
  protected readonly title = signal('frontend');
  accountHolder = 'Brandon';

  accounts =  signal<Account[]>([]);

  constructor(private accountService: AccountService) {
  }

  ngOnInit(): void {
    this.accountService.getAccounts().subscribe({
      next: (accounts) => {
        this.accounts.set(accounts);
        console.log(accounts);
      },
      error: (error) => {
        console.error('Failed to load accounts:', error);
      }
    });
  }

  deposit(accountId: number, amount: number): void {
    this.accountService.deposit(accountId, amount).subscribe({
      next: (updatedAccount) => {
        this.accounts.update(accounts =>
          accounts.map(account =>
            account.id === updatedAccount.id ? updatedAccount : account
          )
        );

        console.log('Deposit successful:', updatedAccount);
      },
      error: (error) =>{
        console.error('Deposit failed: ', error);
      },
    })
  }
}
