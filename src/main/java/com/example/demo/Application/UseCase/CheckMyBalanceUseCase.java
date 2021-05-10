package com.example.demo.Application.UseCase;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Adapter.Query.CheckMyBalanceQuery;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;

public interface CheckMyBalanceUseCase {
    Float checkMyBalance(CheckMyBalanceQuery checkMyBalanceQuery) throws ClientNotFoundException, AccountNotFoundException;
}
