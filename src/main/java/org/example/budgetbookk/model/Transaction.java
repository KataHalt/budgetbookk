package org.example.budgetbookk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "transactions")
public class Transaction {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal amount;

    @Setter
    @NotNull
    private LocalDate bookingDate;

    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Setter
    @Size(max = 200)
    private String description;

    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Column(nullable = false)
    private LocalDate createdAt = LocalDate.now();

    public Transaction() {
    }

}