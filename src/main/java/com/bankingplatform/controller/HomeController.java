package com.bankingplatform.controller;
import java.util.List;
import java.util.Optional;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;
import com.bankingplatform.service.AccountService;
import com.bankingplatform.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {
    private final AccountService accountService;

    public HomeController(AccountService accountService){
        this.accountService = accountService;
    }
    @GetMapping("/")
    public String home(){
        return "Banking Platform API is running";
    }

    @GetMapping("/accounts")
    public List <Account> newAccount(){
        return accountService.getAccounts();
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account){
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        return accountService.getAccountById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<Account> updateAccount(
            @PathVariable Long id,
            @RequestBody Account updatedAccount){
        return accountService.updateAccount(id, updatedAccount)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
        boolean deleted = accountService.deleteAccount(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
