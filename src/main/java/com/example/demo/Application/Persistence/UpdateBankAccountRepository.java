package com.example.demo.Application.Persistence;

import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;

public interface UpdateBankAccountRepository {
    void updateBankAccount(BankAccount bankAccount, Float amount) throws ClientNotFoundException, BankAccountNotFoundException;
}
