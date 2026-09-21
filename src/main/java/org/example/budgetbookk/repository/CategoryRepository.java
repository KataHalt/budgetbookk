package org.example.budgetbookk.repository;

import org.example.budgetbookk.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}