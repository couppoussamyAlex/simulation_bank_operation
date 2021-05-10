package com.example.demo.Persistence;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Adapter.Persistence.BankAccountDatabaseRepository;
import com.example.demo.Adapter.Persistence.Jpa.Entity.BankAccountJpa;
import com.example.demo.Adapter.Persistence.Jpa.Entity.ClientJpa;
import com.example.demo.Adapter.Persistence.Jpa.Repository.BankAccountRepositoryJpa;
import com.example.demo.Adapter.Persistence.Jpa.Repository.ClientRepositoryJpa;
import com.example.demo.Application.Domain.BankAccount;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.FindClientRepository;
import com.example.demo.Persistence.InMemory.InMemoryFindClientRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BankAccountDatabaseRepositoryTest {
    private BankAccountDatabaseRepository bankAccountDatabaseRepository;

    @Autowired
    private BankAccountRepositoryJpa bankAccountRepositoryJpa;

    @Autowired
    private ClientRepositoryJpa clientRepositoryJpa;

    private FindClientRepository findClientRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Before
    public void setUp() {
        this.findClientRepository = new InMemoryFindClientRepository();
        this.bankAccountDatabaseRepository = new BankAccountDatabaseRepository(
                this.bankAccountRepositoryJpa,
                this.clientRepositoryJpa,
                this.findClientRepository);
    }

    @Test
    public void shouldNotCreateABankAccount() throws ClientNotFoundException {
        ClientJpa clientJpa = new ClientJpa("COUPPOUSSAMY", "Alex", null);

        Assert.assertThrows(ClientNotFoundException.class, () -> {
            Client client = new Client("COUPPOUSSAMY", "Alex", null);
            BankAccount bankAccount = new BankAccount("LCL", "001", client, 300.00F);
            this.bankAccountDatabaseRepository.createBankAccount(bankAccount);
        });
    }

    @Test
    public void shouldCreateABankAccount() throws ClientNotFoundException {
        ClientJpa clientJpa = new ClientJpa("COUPPOUSSAMY", "Alex", null);
        ClientJpa res = entityManager.persist(clientJpa);
        entityManager.flush();

        Client client = new Client("COUPPOUSSAMY", "Alex", null);
        BankAccount bankAccount = new BankAccount("LCL", "001", client, 300.00F);
        this.bankAccountDatabaseRepository.createBankAccount(bankAccount);

        Assert.assertEquals("LCL", this.bankAccountRepositoryJpa.findBankAccountJpaByBankNameAndBankId("LCL", "001").get().getBankName());
    }
}
