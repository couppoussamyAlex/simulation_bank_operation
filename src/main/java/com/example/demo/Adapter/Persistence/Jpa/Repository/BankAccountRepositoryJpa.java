package com.example.demo.Adapter.Persistence.Jpa.Repository;

import com.example.demo.Adapter.Persistence.Jpa.Entity.BankAccountJpa;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankAccountRepositoryJpa extends CrudRepository<BankAccountJpa, Long> {
    Optional<BankAccountJpa> findBankAccountJpaByBankNameAndBankId(String bankName, String bankId);
}
