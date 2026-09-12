package com.bankingplatform.service;

import com.bankingplatform.exceptions.InvalidAmountException;
import com.bankingplatform.model.Account;
import com.bankingplatform.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.ArgumentMatchers.anyLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        accountService = new AccountService(accountRepository);
    }
    @Test
    void depositShouldIncreaseBalance() {
        //Arrange
        Account account = new Account(
            "CHECKING",
            new BigDecimal("2000.00")
        );
        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(account));

        //Act
        accountService.deposit(1L, new BigDecimal("500.00"));

        // Assert
        assertEquals(
                new BigDecimal("2500.00"),
                account.getBalance()
        );
    }
    @Test
    void transferShouldMoveMoneyBetweenAccounts() {
        //ARRANGE
        Account fromAccount = new Account("CHECKING", new BigDecimal("2000.00"));
        Account toAccount = new Account("SAVINGS", new BigDecimal("1000.00"));

        when(accountRepository.findById(1L))
                .thenReturn(Optional.of(fromAccount));
        when(accountRepository.findById(2L))
                .thenReturn(Optional.of(toAccount));

        //ACT
        accountService.transfer(1L, 2L, new BigDecimal("500.00"));

        //Assert
        assertEquals(
                new BigDecimal("1500.00"),
                fromAccount.getBalance()
        );
        assertEquals(
                new BigDecimal("1500.00"),
                toAccount.getBalance()
        );

        verify(accountRepository).save(fromAccount);
        verify(accountRepository).save(toAccount);
    }
    @Test
    void depositShouldRejectNegativeAmount() {
        // Act & Assert
        assertThrows(
                InvalidAmountException.class,
                () -> accountService.deposit(
                        1L,
                        new BigDecimal("-100.00")
                )
        );
        verify(accountRepository, never()).findById(anyLong());
    }





}