package com.example.demo.Persistence.InMemory;

import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Persistence.UpdateBankAccountRepository;

public class InMemoryUpdateBankAccountRepository implements UpdateBankAccountRepository {
    @Override
    public void updateBankAccount(BankAccount bankAccount, Float amount) throws ClientNotFoundException, BankAccountNotFoundException {
        if ("test_fail".equals(bankAccount.getClient().getName())) {
            throw new ClientNotFoundException("error thrown");
        }

        if ("test_fail".equals(bankAccount.getBankName())) {
            throw new BankAccountNotFoundException();
        }
    }
}
