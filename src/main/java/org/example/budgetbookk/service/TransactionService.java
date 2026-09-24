package org.example.budgetbookk.service;

import org.example.budgetbookk.model.Transaction;
import org.example.budgetbookk.repository.TransactionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    // Nur Buchungen des aktuellen Users zurückgeben
    public List<Transaction> getTransactionsForUser(String username) {
        return transactionRepository.findAllByUsername(username);
    }

    // Das geforderte Sicherheitsmuster für Details, Edit und Delete
    public Transaction getOwnedTransaction(Long id, String username) {
        return transactionRepository.findByIdAndUsername(id, username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Buchung nicht gefunden oder keine Berechtigung"));
    }

    public void save(Transaction transaction, String username) {
        transaction.setUsername(username); // Automatisch den angemeldeten User zuweisen
        if (transaction.getCreatedAt() == null) {
            transaction.setCreatedAt(LocalDate.now());
        }
        transactionRepository.save(transaction);
    }

    public void delete(Long id, String username) {
        Transaction transaction = getOwnedTransaction(id, username); // Prüft vorher die Berechtigung
        transactionRepository.delete(transaction);
    }
}