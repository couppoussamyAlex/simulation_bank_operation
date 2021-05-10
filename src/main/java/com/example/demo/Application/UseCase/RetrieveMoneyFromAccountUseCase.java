package com.example.demo.Application.UseCase;

import com.example.demo.Adapter.Command.RetrieveMoneyFromAccountCommand;
import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;
import com.example.demo.Application.Service.Exception.NotEnoughMoneyException;

public interface RetrieveMoneyFromAccountUseCase {
    void retrieveMoney(RetrieveMoneyFromAccountCommand retrieveMoneyFromAccountCommand) throws AccountNotFoundException, ClientNotFoundException, NotEnoughMoneyException, BankAccountNotFoundException;
}
