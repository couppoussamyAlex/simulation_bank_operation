package com.example.demo.Application.UseCase;

import com.example.demo.Adapter.Command.CreateBankAccountCommand;
import com.example.demo.Adapter.Exception.ClientNotFoundException;

public interface CreateBankAccountUseCase {
    public void createBankAccount(CreateBankAccountCommand createBankAccountCommand) throws ClientNotFoundException;
}
