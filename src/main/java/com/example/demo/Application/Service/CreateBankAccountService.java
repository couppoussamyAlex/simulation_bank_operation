package com.example.demo.Application.Service;

import com.example.demo.Adapter.Command.CreateBankAccountCommand;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.CreateBankAccountRepository;
import com.example.demo.Application.Persistence.FindClientRepository;
import com.example.demo.Application.UseCase.CreateBankAccountUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateBankAccountService implements CreateBankAccountUseCase {
    CreateBankAccountRepository createBankAccountRepository;
    FindClientRepository findClientRepository;

    public CreateBankAccountService(CreateBankAccountRepository createBankAccountRepository, FindClientRepository findClientRepository) {
        this.createBankAccountRepository = createBankAccountRepository;
        this.findClientRepository = findClientRepository;
    }

    @Override
    public void createBankAccount(CreateBankAccountCommand createBankAccountCommand) throws ClientNotFoundException {
        Client client = findClientRepository.findClient(createBankAccountCommand.getClientName(), createBankAccountCommand.getClientFirstName());
        createBankAccountRepository.createBankAccount(new BankAccount(createBankAccountCommand.getBankName(), createBankAccountCommand.getBankId(), client, createBankAccountCommand.getInitialDeposit()));
    }
}
