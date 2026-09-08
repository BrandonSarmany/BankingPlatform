package com.bankingplatform.controller;
import java.util.List;
import com.bankingplatform.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bankingplatform.model.Account;

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
}
