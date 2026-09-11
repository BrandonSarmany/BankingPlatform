package com.bankingplatform.service;
import com.bankingplatform.model.Account;
import com.bankingplatform.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

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

}
