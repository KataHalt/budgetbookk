package org.example.budgetbookk.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Setter
    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Setter
    private boolean isActive = true;

    public Category() {
    }

    public Category(String name, TransactionType type) {
        this.name = name;
        this.type = type;
        this.isActive = true;
    }

}