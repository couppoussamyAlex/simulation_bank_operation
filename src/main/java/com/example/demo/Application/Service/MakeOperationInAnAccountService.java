package com.example.demo.Application.Service;

import com.example.demo.Adapter.Command.PutMoneyInMyAccountCommand;
import com.example.demo.Adapter.Command.RetrieveMoneyFromAccountCommand;
import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Persistence.FindAccountOfClientInAbankRepository;
import com.example.demo.Application.Persistence.FindClientRepository;
import com.example.demo.Application.Persistence.UpdateBankAccountRepository;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;
import com.example.demo.Application.Service.Exception.NotEnoughMoneyException;
import com.example.demo.Application.UseCase.PutMoneyInMyAccountUseCase;
import com.example.demo.Application.UseCase.RetrieveMoneyFromAccountUseCase;
import org.springframework.stereotype.Service;

@Service
public class MakeOperationInAnAccountService implements PutMoneyInMyAccountUseCase, RetrieveMoneyFromAccountUseCase {
    private final UpdateBankAccountRepository updateBankAccountRepository;
    private final FindAccountOfClientInAbankRepository findAccountOfClientInAbankRepository;

    public MakeOperationInAnAccountService(UpdateBankAccountRepository updateBankAccountRepository, FindAccountOfClientInAbankRepository findAccountOfClientInAbankRepository) {
        this.updateBankAccountRepository = updateBankAccountRepository;
        this.findAccountOfClientInAbankRepository = findAccountOfClientInAbankRepository;
    }

    @Override
    public void saveMoney(PutMoneyInMyAccountCommand putMoneyInMyAccountCommand) throws ClientNotFoundException, AccountNotFoundException, BankAccountNotFoundException {
        BankAccount account = this.findAccountOfClientInAbankRepository.findAccountOfClientInAbankRepository(
                putMoneyInMyAccountCommand.getClientName(),
                putMoneyInMyAccountCommand.getClientFirstName(),
                putMoneyInMyAccountCommand.getBankName()
        );
        this.updateBankAccountRepository.updateBankAccount(account, putMoneyInMyAccountCommand.getAmount());
    }

    @Override
    public void retrieveMoney(RetrieveMoneyFromAccountCommand retrieveMoneyFromAccountCommand) throws AccountNotFoundException, ClientNotFoundException, NotEnoughMoneyException, BankAccountNotFoundException {
        BankAccount account = this.findAccountOfClientInAbankRepository.findAccountOfClientInAbankRepository(
                retrieveMoneyFromAccountCommand.getClientName(),
                retrieveMoneyFromAccountCommand.getClientFirstName(),
                retrieveMoneyFromAccountCommand.getBankName()
        );
        if ((account.getBalance() - retrieveMoneyFromAccountCommand.getAmount()) < 0) {
            throw new NotEnoughMoneyException(String.format("there is not enough money to retrieve %s euros", retrieveMoneyFromAccountCommand.getAmount()));
        }

        this.updateBankAccountRepository.updateBankAccount(account, (retrieveMoneyFromAccountCommand.getAmount() * -1));
    }
}
