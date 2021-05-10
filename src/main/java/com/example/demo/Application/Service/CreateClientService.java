package com.example.demo.Application.Service;

import com.example.demo.Adapter.Command.CreateClientCommand;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.CreateClientRepository;
import com.example.demo.Application.UseCase.CreateClientUseCase;
import org.springframework.stereotype.Service;

@Service
public class CreateClientService implements CreateClientUseCase {
    private final CreateClientRepository clientRepository;

    public CreateClientService(CreateClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void createClient(CreateClientCommand createClientCommand) {
        Client client = new Client(createClientCommand.getName(), createClientCommand.getFirstName(), null);
        clientRepository.createClient(client);
    }
}
