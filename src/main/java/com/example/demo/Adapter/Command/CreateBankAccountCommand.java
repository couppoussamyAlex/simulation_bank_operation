package com.example.demo.Adapter.Command;

public class CreateBankAccountCommand {
    private String bankName;
    private String bankId;
    private String clientName;
    private String clientFirstName;
    private Float initialDeposit;

    public CreateBankAccountCommand(String bankName, String bankId, String clientName, String clientFirstName, Float initialDeposit) {
        this.bankName = bankName;
        this.bankId = bankId;
        this.clientName = clientName;
        this.clientFirstName = clientFirstName;
        this.initialDeposit = initialDeposit;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankId() {
        return bankId;
    }

    public String getClientName() { return clientName; }

    public String getClientFirstName() { return clientFirstName; }

    public Float getInitialDeposit() { return initialDeposit; }
}
