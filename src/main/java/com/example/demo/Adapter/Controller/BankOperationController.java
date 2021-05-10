package com.example.demo.Adapter.Controller;

import com.example.demo.Adapter.Command.PutMoneyInMyAccountCommand;
import com.example.demo.Adapter.Command.RetrieveMoneyFromAccountCommand;
import com.example.demo.Adapter.DTO.RetriveMoneyFromAccountDTO;
import com.example.demo.Adapter.DTO.SaveMoneyInAccountDTO;
import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Adapter.Query.CheckMyBalanceQuery;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;
import com.example.demo.Application.Service.Exception.NotEnoughMoneyException;
import com.example.demo.Application.UseCase.CheckMyBalanceUseCase;
import com.example.demo.Application.UseCase.PutMoneyInMyAccountUseCase;
import com.example.demo.Application.UseCase.RetrieveMoneyFromAccountUseCase;
import org.springframework.web.bind.annotation.*;

@RequestMapping("bank/operation")
@RestController
public class BankOperationController {
    private PutMoneyInMyAccountUseCase putMoneyInMyAccountUseCase;
    private RetrieveMoneyFromAccountUseCase retrieveMoneyFromAccountUseCase;
    private CheckMyBalanceUseCase checkMyBalanceUseCase;

    public BankOperationController(PutMoneyInMyAccountUseCase putMoneyInMyAccountUseCase, RetrieveMoneyFromAccountUseCase retrieveMoneyFromAccountUseCase, CheckMyBalanceUseCase checkMyBalanceUseCase) {
        this.putMoneyInMyAccountUseCase = putMoneyInMyAccountUseCase;
        this.retrieveMoneyFromAccountUseCase = retrieveMoneyFromAccountUseCase;
        this.checkMyBalanceUseCase = checkMyBalanceUseCase;
    }

    @PutMapping("/save/money")
    public void saveMoney(@RequestBody SaveMoneyInAccountDTO saveMoneyInAccountDTO) throws ClientNotFoundException, AccountNotFoundException, BankAccountNotFoundException {
        PutMoneyInMyAccountCommand createBankAccountCommand = new PutMoneyInMyAccountCommand(
                saveMoneyInAccountDTO.getAmount(),
                saveMoneyInAccountDTO.getClientName(),
                saveMoneyInAccountDTO.getClientFirstName(),
                saveMoneyInAccountDTO.getBankName());

        this.putMoneyInMyAccountUseCase.saveMoney(createBankAccountCommand);
    }

    @PutMapping("/retrieve/money")
    public void retrieveMoney(@RequestBody RetriveMoneyFromAccountDTO retriveMoneyFromAccountDTO) throws ClientNotFoundException, AccountNotFoundException, NotEnoughMoneyException, BankAccountNotFoundException {
        RetrieveMoneyFromAccountCommand retrieveMoneyFromAccountCommand = new RetrieveMoneyFromAccountCommand(
                retriveMoneyFromAccountDTO.getAmount(),
                retriveMoneyFromAccountDTO.getClientName(),
                retriveMoneyFromAccountDTO.getClientFirstName(),
                retriveMoneyFromAccountDTO.getBankName());

        this.retrieveMoneyFromAccountUseCase.retrieveMoney(retrieveMoneyFromAccountCommand);
    }

    @GetMapping("/check")
    public Float checkMyBalance(@RequestParam String bankName, @RequestParam String clientName, @RequestParam String clientFirstName) throws ClientNotFoundException, AccountNotFoundException, NotEnoughMoneyException, BankAccountNotFoundException {
        CheckMyBalanceQuery checkMyBalanceQuery = new CheckMyBalanceQuery(clientName, clientFirstName, bankName);

        return this.checkMyBalanceUseCase.checkMyBalance(checkMyBalanceQuery);
    }
}
