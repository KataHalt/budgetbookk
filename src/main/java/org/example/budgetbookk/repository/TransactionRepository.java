package org.example.budgetbookk.repository;

import org.example.budgetbookk.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByOrderByBookingDateDesc();
}