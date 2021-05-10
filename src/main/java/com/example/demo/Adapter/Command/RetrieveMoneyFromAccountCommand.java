package com.example.demo.Adapter.Command;

public class RetrieveMoneyFromAccountCommand {
    private Float amount;
    private String clientName;
    private String clientFirstName;
    private String bankName;

    public RetrieveMoneyFromAccountCommand(Float amount, String clientName, String clientFirstName, String bankName) {
        this.amount = amount;
        this.clientName = clientName;
        this.clientFirstName = clientFirstName;
        this.bankName = bankName;
    }

    public Float getAmount() {
        return amount;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getBankName() {
        return bankName;
    }
}
