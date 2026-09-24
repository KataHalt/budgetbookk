package org.example.budgetbookk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Betrag darf nicht leer sein")
    @DecimalMin(value = "0.01", message = "Betrag muss größer als 0 sein")
    private BigDecimal amount;

    @NotNull(message = "Datum darf nicht leer sein")
    private LocalDate bookingDate;

    @NotNull(message = "Typ muss gewählt werden")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private String description;

    @NotNull(message = "Kategorie muss gewählt werden")
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "username", nullable = false)
    private String username;

    @NotNull
    private LocalDate createdAt = LocalDate.now();

    public Transaction() {}
}