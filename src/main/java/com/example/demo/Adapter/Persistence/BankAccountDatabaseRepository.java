package com.example.demo.Adapter.Persistence;

import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Adapter.Persistence.Jpa.Entity.ClientJpa;
import com.example.demo.Adapter.Persistence.Jpa.Repository.BankAccountRepositoryJpa;
import com.example.demo.Adapter.Persistence.Jpa.Repository.ClientRepositoryJpa;
import com.example.demo.Adapter.Persistence.Jpa.Entity.BankAccountJpa;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.CreateBankAccountRepository;
import com.example.demo.Application.Persistence.FindAccountOfClientInAbankRepository;
import com.example.demo.Application.Persistence.FindClientRepository;
import com.example.demo.Application.Persistence.UpdateBankAccountRepository;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankAccountDatabaseRepository implements CreateBankAccountRepository, UpdateBankAccountRepository, FindAccountOfClientInAbankRepository {
    private final BankAccountRepositoryJpa bankAccountRepositoryJpa;
    private final ClientRepositoryJpa clientRepositoryJpa;
    private final FindClientRepository findClientRepository;

    public BankAccountDatabaseRepository(BankAccountRepositoryJpa bankAccountRepositoryJpa, ClientRepositoryJpa clientRepositoryJpa, FindClientRepository findClientRepository) {
        this.bankAccountRepositoryJpa = bankAccountRepositoryJpa;
        this.clientRepositoryJpa = clientRepositoryJpa;
        this.findClientRepository = findClientRepository;
    }

    @Override
    public void createBankAccount(BankAccount bankAccount) throws ClientNotFoundException {
        Optional<ClientJpa> clientJpa = clientRepositoryJpa.findByNameAndFirstName(bankAccount.getClient().getName(), bankAccount.getClient().getFirstName());
        BankAccountJpa bankAccountJpa = bankAccountRepositoryJpa.save(new BankAccountJpa(bankAccount.getBankName(),bankAccount.getBankId(), bankAccount.getBalance(), clientJpa.orElseThrow(() -> {
            return new ClientNotFoundException(String.format("The client %s %s doesn\'t exist", bankAccount.getClient().getName(), bankAccount.getClient().getFirstName()));
        })));
        System.out.println(bankAccountJpa.getBankName());
    }

    @Override
    public void updateBankAccount(BankAccount bankAccount, Float amount) throws ClientNotFoundException, BankAccountNotFoundException {
        ClientJpa clientJpa = this.clientRepositoryJpa.findByNameAndFirstName(bankAccount.getClient().getName(), bankAccount.getClient().getFirstName()).orElseThrow(() -> {
            return new ClientNotFoundException(String.format("The client %s %s doesn\'t exist", bankAccount.getClient().getName(), bankAccount.getClient().getFirstName()));
        });
        BankAccountJpa bankAccountJpa = this.bankAccountRepositoryJpa.findBankAccountJpaByBankNameAndBankId(bankAccount.getBankName(), bankAccount.getBankId()).orElseThrow(BankAccountNotFoundException::new);
        bankAccountJpa.setBalance(bankAccountJpa.getBalance() + amount);
        BankAccountJpa result = this.bankAccountRepositoryJpa.save(bankAccountJpa);
        System.out.println(bankAccountJpa.getBalance());
    }

    @Override
    public BankAccount findAccountOfClientInAbankRepository(String clientName, String clientFirstName, String bankName) throws AccountNotFoundException, ClientNotFoundException {
        Client client = this.findClientRepository.findClient(clientName, clientFirstName);
        Optional<BankAccount> bankAccount = client.getBankAccounts().stream().filter(account -> bankName.equals(account.getBankName())).findFirst();

        return bankAccount.orElseThrow(() -> {
            return new AccountNotFoundException(String.format("the account %s doesn't exist", bankName));
        });
    }
}
