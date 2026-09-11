package com.bankingplatform.exceptions;

public class AccountNotFound extends RuntimeException {

    public AccountNotFound(String message) {
        super(message);
    }
}