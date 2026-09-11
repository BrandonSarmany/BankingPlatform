package com.bankingplatform.service;
import com.bankingplatform.model.Account;
import com.bankingplatform.repository.AccountRepository;
import org.springframework.stereotype.Service;
import com.bankingplatform.exceptions.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class AccountService {
    public List <Account> getAccounts(){
        return accountRepository.findAll();
    }
    public Optional<Account> getAccountById(Long id){
        return accountRepository.findById(id);
    }
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> updateAccount(Long id, Account updatedAccount) {
        return accountRepository.findById(id)
                .map(existingAccount ->{
                    existingAccount.setType(updatedAccount.getType());
                    existingAccount.setBalance(updatedAccount.getBalance());

                    return accountRepository.save(existingAccount);
                });
    }

    public boolean deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            return false;
        }
        accountRepository.deleteById(id);
        return true;
    }

    public Optional<Account> deposit(Long id, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Deposit amount must be greater than zero");
        }
        return accountRepository.findById(id)
                .map(existingAccount ->{
                    BigDecimal currentBalance = existingAccount.getBalance();
                    existingAccount.setBalance(currentBalance.add(amount));

                    return accountRepository.save(existingAccount);
                });
    }

    public Optional<Account> withdrawal (Long id, BigDecimal amount){
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Withdraw amount must be greater than zero");
        }
        return accountRepository.findById(id)
                .map(existingAccount ->{
                   BigDecimal currentBalance = existingAccount.getBalance();
                   if(currentBalance.compareTo(amount) < 0){
                       throw new InsufficientFundsException("Withdraw amount is higher than your current balance");
                   }
                   existingAccount.setBalance(currentBalance.subtract(amount));
                   return accountRepository.save(existingAccount);
                });
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        if(amount.compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Transfer amount must be greater than zero");
        }
        if(Objects.equals(toAccountId, fromAccountId)){
            throw new InvalidAction("Cannot transfer funds to the same account");
        }
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new AccountNotFound("Source account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new AccountNotFound("Destination account not found"));

        BigDecimal fromCurrentBalance = fromAccount.getBalance();

        if(fromCurrentBalance.compareTo(amount) < 0){
            throw new InsufficientFundsException("Transfer amount is higher than current balance");
        }

        fromAccount.setBalance(fromCurrentBalance.subtract(amount));

        BigDecimal toCurrentBalance = toAccount.getBalance();

        toAccount.setBalance(toCurrentBalance.add(amount));

        accountRepository.save(toAccount);
        accountRepository.save(fromAccount);
    }

}
