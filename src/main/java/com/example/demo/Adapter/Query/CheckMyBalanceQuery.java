package com.example.demo.Adapter.Query;

public class CheckMyBalanceQuery {
    private String clientName;
    private String clientFirstName;
    private String BankName;

    public CheckMyBalanceQuery(String clientName, String clientFirstName, String bankName) {
        this.clientName = clientName;
        this.clientFirstName = clientFirstName;
        BankName = bankName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getBankName() {
        return BankName;
    }
}
