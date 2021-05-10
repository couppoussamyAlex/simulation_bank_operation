package com.example.demo.Application.Domain;

import java.util.List;

public class Client {
    private String name;
    private String firstName;
    private List<BankAccount> bankAccounts;

    public Client(String name, String firstName, List<BankAccount> bankAccounts) {
        this.name = name;
        this.firstName = firstName;
        this.bankAccounts = bankAccounts;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }
}
