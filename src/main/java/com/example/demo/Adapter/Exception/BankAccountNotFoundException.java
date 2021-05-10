package com.example.demo.Adapter.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankAccountNotFoundException extends Exception {
    public BankAccountNotFoundException() {
        super("The account doesn't exist");
    }
}
