package com.bankingplatform.service;
import com.bankingplatform.model.Account;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccountService {
    public List <Account> getAccounts(){
        return List.of(
                new Account(1L, "CHECKING", 2500.00),
                new Account(2L, "SAVINGS", 8000.00),
                new Account(3L, "CREDIT", -450.00)
        );
    }
}
