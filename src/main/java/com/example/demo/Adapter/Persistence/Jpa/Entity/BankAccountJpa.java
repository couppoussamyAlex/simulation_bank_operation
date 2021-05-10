package com.example.demo.Adapter.Persistence.Jpa.Entity;

import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Domain.Client;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT")
public class BankAccountJpa {
    public BankAccountJpa() {}

    public BankAccountJpa(String bankName, String bankId, Float balance, ClientJpa client) {
        this.bankName = bankName;
        this.bankId = bankId;
        this.balance = balance;
        this.client = client;
    }

    public static BankAccount toDomain(BankAccountJpa bankAccountJpa) {
        return new BankAccount(
                bankAccountJpa.getBankName(),
                bankAccountJpa.getBankId(),
                new Client(bankAccountJpa.getClient().getName(), bankAccountJpa.getClient().getFirstName(), null),
                bankAccountJpa.getBalance()
        );
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_id")
    private String bankId;

    @Column(name = "balance")
    private Float balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClientJpa client;

    public String getBankName() {
        return bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public Float getBalance() {
        return balance;
    }

    public ClientJpa getClient() {
        return client;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }
}
