package com.example.demo.Application.Persistence;

import com.example.demo.Adapter.Exception.ClientNotFoundException;
import com.example.demo.Application.Domain.Client;

public interface FindClientRepository {
    public Client findClient(String clientName, String clientFirstName) throws ClientNotFoundException;
}
