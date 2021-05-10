package com.example.demo.Adapter.DTO;

public class CreateBankAccountDTO {
    private String bankName;
    private String bankId;
    private String clientName;
    private String clientFirstName;
    private Float initialDeposit;

    public String getBankName() {
        return bankName;
    }
    public String getBankId() {
        return bankId;
    }
    public String getClientName() {
        return clientName;
    }
    public String getClientFirstName() { return clientFirstName; }
    public Float getInitialDeposit() { return initialDeposit; }

}
