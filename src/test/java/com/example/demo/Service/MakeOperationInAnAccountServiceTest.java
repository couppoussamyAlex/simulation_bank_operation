package com.example.demo.Service;

import com.example.demo.Adapter.Command.PutMoneyInMyAccountCommand;
import com.example.demo.Adapter.Exception.BankAccountNotFoundException;
import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Persistence.FindAccountOfClientInAbankRepository;
import com.example.demo.Application.Persistence.UpdateBankAccountRepository;
import com.example.demo.Application.Service.Exception.AccountNotFoundException;
import com.example.demo.Application.Service.MakeOperationInAnAccountService;
import com.example.demo.Persistence.InMemory.InMemoryFindAccountOfClientInAbankRepository;
import com.example.demo.Persistence.InMemory.InMemoryUpdateBankAccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class MakeOperationInAnAccountServiceTest {
    private MakeOperationInAnAccountService makeOperationInAnAccountService;

    @Mock
    private UpdateBankAccountRepository updateBankAccountRepository;

    private FindAccountOfClientInAbankRepository findAccountOfClientInAbankRepository;

    @Before
    public void setUp() {
        this.findAccountOfClientInAbankRepository = new InMemoryFindAccountOfClientInAbankRepository();
        this.updateBankAccountRepository = Mockito.spy(new InMemoryUpdateBankAccountRepository());
        this.makeOperationInAnAccountService = new MakeOperationInAnAccountService(updateBankAccountRepository, findAccountOfClientInAbankRepository);
    }

    @Test
    public void shouldNotSaveMoney() throws ClientNotFoundException, BankAccountNotFoundException, AccountNotFoundException {
        Assert.assertThrows(AccountNotFoundException.class, () -> {
            PutMoneyInMyAccountCommand putMoneyInMyAccountCommand = new PutMoneyInMyAccountCommand(200.F, "Stark", "Tony", "fail_bank_name");
            this.makeOperationInAnAccountService.saveMoney(putMoneyInMyAccountCommand);
        });
    }

    @Test
    public void shouldSaveMoney() throws ClientNotFoundException, BankAccountNotFoundException, AccountNotFoundException {
        PutMoneyInMyAccountCommand putMoneyInMyAccountCommand = new PutMoneyInMyAccountCommand(200.F, "Stark", "Tony", "LCL");
        this.makeOperationInAnAccountService.saveMoney(putMoneyInMyAccountCommand);

        Mockito.verify(this.updateBankAccountRepository, Mockito.times(1)).updateBankAccount(any(), any());
    }
}
