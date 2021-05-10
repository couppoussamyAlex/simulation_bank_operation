package com.example.demo.Application.Persistence;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;

public interface FindAccountOfClientInAbankRepository {
    BankAccount findAccountOfClientInAbankRepository(String clientName, String clientFirstName, String bankName) throws AccountNotFoundException, ClientNotFoundException;
}
