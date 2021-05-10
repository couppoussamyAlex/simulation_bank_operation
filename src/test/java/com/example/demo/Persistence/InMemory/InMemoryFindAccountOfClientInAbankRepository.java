package com.example.demo.Persistence.InMemory;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.FindAccountOfClientInAbankRepository;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;

public class InMemoryFindAccountOfClientInAbankRepository implements FindAccountOfClientInAbankRepository {

    @Override
    public BankAccount findAccountOfClientInAbankRepository(String clientName, String clientFirstName, String bankName) throws AccountNotFoundException, ClientNotFoundException {
        if ("fail_bank_name".equals(bankName)) {
            throw new AccountNotFoundException("error thrown");
        }
        if ("fail_client_name".equals(clientName) || "fail_client_first_name".equals(clientFirstName)) {
            throw new ClientNotFoundException("error thrown");
        }
        Client client = new Client(clientName, clientFirstName, null);
        return new BankAccount(bankName, null, client, 800.F);
    }
}
