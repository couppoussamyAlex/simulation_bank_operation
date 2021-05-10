package com.example.demo.Application.Service;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Adapter.Query.CheckMyBalanceQuery;
import com.example.demo.Application.Persistence.FindAccountOfClientInAbankRepository;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;
import com.example.demo.Application.UseCase.CheckMyBalanceUseCase;
import org.springframework.stereotype.Service;

@Service
public class CheckMyBalanceService implements CheckMyBalanceUseCase {
    FindAccountOfClientInAbankRepository findAccountOfClientInAbankRepository;

    public CheckMyBalanceService(FindAccountOfClientInAbankRepository findAccountOfClientInAbankRepository) {
        this.findAccountOfClientInAbankRepository = findAccountOfClientInAbankRepository;
    }

    @Override
    public Float checkMyBalance(CheckMyBalanceQuery checkMyBalanceQuery) throws ClientNotFoundException, AccountNotFoundException {
        return this.findAccountOfClientInAbankRepository.findAccountOfClientInAbankRepository(
                checkMyBalanceQuery.getClientName(),
                checkMyBalanceQuery.getClientFirstName(),
                checkMyBalanceQuery.getBankName()
        ).getBalance();
    }
}
