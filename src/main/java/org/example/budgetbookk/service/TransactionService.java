package org.example.budgetbookk.service;

import org.example.budgetbookk.model.Transaction;
import org.example.budgetbookk.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactionsForUser(String username) {
        return transactionRepository.findAll();
    }

    public Transaction getOwnedTransaction(Long id, String username) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buchung nicht gefunden"));
    }

    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public void delete(Long id, String username) {
        Transaction transaction = getOwnedTransaction(id, username);
        transactionRepository.delete(transaction);
    }
}