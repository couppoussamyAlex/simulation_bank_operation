package com.example.demo.Application.UseCase;

import com.example.demo.Adapter.Command.PutMoneyInMyAccountCommand;
import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;

public interface PutMoneyInMyAccountUseCase {
    void saveMoney(PutMoneyInMyAccountCommand putMoneyInMyAccountCommand) throws ClientNotFoundException, AccountNotFoundException, BankAccountNotFoundException;
}
