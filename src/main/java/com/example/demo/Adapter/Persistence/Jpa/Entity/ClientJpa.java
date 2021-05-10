package com.example.demo.Adapter.Persistence.Jpa.Entity;

import com.example.demo.Application.Domain.Client;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "CLIENT")
public class ClientJpa {

    public ClientJpa() {}

    public ClientJpa(String name, String firstName, List<BankAccountJpa> bankAccounts) {
        this.name = name;
        this.firstName = firstName;
        this.bankAccounts = bankAccounts;
    }

    public static Client toDomain(ClientJpa clientJpa) {
        return new Client(
                clientJpa.getName(),
                clientJpa.getFirstName(),
                clientJpa.getBankAccounts().stream().map((BankAccountJpa::toDomain)).collect(Collectors.toList()));
    }

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private Long id;

    private String name;

    @Column(name = "first_name")
    private String firstName;

    @OneToMany( fetch = FetchType.LAZY, mappedBy="client" )
    private List<BankAccountJpa> bankAccounts;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public List<BankAccountJpa> getBankAccounts() {
        return bankAccounts;
    }
}
