package com.example.demo.Adapter.Controller;

import com.example.demo.Adapter.Command.CreateBankAccountCommand;
import com.example.demo.Adapter.DTO.CreateBankAccountDTO;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.UseCase.CreateBankAccountUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("bank/account")
@RestController
public class CreateBankAccountController {
    CreateBankAccountUseCase createBankAccountUseCase;

    public CreateBankAccountController(CreateBankAccountUseCase createBankAccountUseCase) {
        this.createBankAccountUseCase = createBankAccountUseCase;
    }

    @PostMapping("/create")
    public void createBankAccount(@RequestBody CreateBankAccountDTO createBankAccountDTO) throws ClientNotFoundException {
        CreateBankAccountCommand createBankAccountCommand = new CreateBankAccountCommand(createBankAccountDTO.getBankName(), createBankAccountDTO.getBankId(), createBankAccountDTO.getClientName(), createBankAccountDTO.getClientFirstName(), createBankAccountDTO.getInitialDeposit());
        createBankAccountUseCase.createBankAccount(createBankAccountCommand);
    }
}
