package org.example.budgetbookk.repository;

import org.example.budgetbookk.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByUsername(String username);
    Optional<Transaction> findByIdAndUsername(Long id, String username);
}