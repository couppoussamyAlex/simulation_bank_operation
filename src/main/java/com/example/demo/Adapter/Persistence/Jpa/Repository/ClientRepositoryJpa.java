package com.example.demo.Adapter.Persistence.Jpa.Repository;

import com.example.demo.Adapter.Persistence.Jpa.Entity.ClientJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClientRepositoryJpa extends CrudRepository<ClientJpa, Long> {
    public Optional<ClientJpa> findByName(String name);
    public Optional<ClientJpa> findByNameAndFirstName(String name, String firstName);
}
