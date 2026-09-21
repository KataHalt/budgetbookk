package org.example.budgetbookk.repository;

import org.example.budgetbookk.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}