package com.example.demo.Persistence.InMemory;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.Client;
import com.example.demo.Application.Persistence.FindClientRepository;

public class InMemoryFindClientRepository implements FindClientRepository {
    @Override
    public Client findClient(String clientName, String clientFirstName) throws ClientNotFoundException {
        if(!"test_name".equals(clientName) && !"test_first_name".equals(clientFirstName)) {
            throw new ClientNotFoundException("test_client_not_found_exception");
        }
        return new Client("test_name", "test_first_name", null);
    }
}
