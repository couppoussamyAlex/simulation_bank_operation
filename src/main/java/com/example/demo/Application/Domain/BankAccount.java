package com.example.demo.Application.Domain;

public class BankAccount {
    private String bankName;
    private String bankId;
    private Client client;
    private Float balance;

    public BankAccount(String bankName, String bankId, Client client, Float balance) {
        this.bankName = bankName;
        this.bankId = bankId;
        this.client = client;
        this.balance = balance;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public Client getClient() { return client; }

    public Float getBalance() { return balance; }
}
