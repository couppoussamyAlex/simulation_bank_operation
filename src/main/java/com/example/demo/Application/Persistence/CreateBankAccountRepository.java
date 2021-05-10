package com.example.demo.Application.Persistence;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;

public interface CreateBankAccountRepository {
    public void createBankAccount(BankAccount bankAccount) throws ClientNotFoundException;
}
