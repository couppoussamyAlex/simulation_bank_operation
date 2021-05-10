package com.example.demo.Adapter.Persistence;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Adapter.Persistence.Jpa.Repository.ClientRepositoryJpa;
import com.example.demo.Adapter.Persistence.Jpa.Entity.BankAccountJpa;
import com.example.demo.Adapter.Persistence.Jpa.Entity.ClientJpa;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.CreateClientRepository;
import com.example.demo.Application.Persistence.FindClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientDatabaseRepository implements CreateClientRepository, FindClientRepository {
    ClientRepositoryJpa clientRepositoryJpa;

    public ClientDatabaseRepository(ClientRepositoryJpa clientRepositoryJpa) {
        this.clientRepositoryJpa = clientRepositoryJpa;
    }

    @Override
    public void createClient(Client client) {
        ClientJpa clientJpa = clientRepositoryJpa.save(new ClientJpa(client.getName(), client.getFirstName(), null));
        System.out.println(clientJpa.getName());
    }

    @Override
    public Client findClient(String clientName, String clientFirstName) throws ClientNotFoundException {
        Optional<ClientJpa> clientJpa = clientRepositoryJpa.findByNameAndFirstName(clientName, clientFirstName);
        if (clientJpa.isEmpty()) {
            throw new ClientNotFoundException(String.format("The client %s %s doesn't exist", clientName, clientFirstName));
        }

        return new Client(
                clientJpa.get().getName(),
                clientJpa.get().getFirstName(),
                clientJpa.get().getBankAccounts().stream().map((BankAccountJpa::toDomain)).collect(Collectors.toList())
        );

    }
}
