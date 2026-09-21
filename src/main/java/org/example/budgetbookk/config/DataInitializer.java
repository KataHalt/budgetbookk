package org.example.budgetbookk.config;

import org.example.budgetbookk.model.Category;
import org.example.budgetbookk.model.Transaction;
import org.example.budgetbookk.model.TransactionType;
import org.example.budgetbookk.repository.CategoryRepository;
import org.example.budgetbookk.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(CategoryRepository categoryRepo, TransactionRepository transactionRepo) {
        return args -> {

            if (categoryRepo.count() == 0) {

                Category gehalt = new Category("Gehalt", TransactionType.INCOME);
                Category lebensmittel = new Category("Lebensmittel", TransactionType.EXPENSE);
                Category miete = new Category("Miete", TransactionType.EXPENSE);
                Category freizeit = new Category("Freizeit", TransactionType.EXPENSE);

                categoryRepo.saveAll(List.of(gehalt, lebensmittel, miete, freizeit));

                Transaction t1 = new Transaction();
                t1.setAmount(new BigDecimal("2500.00"));
                t1.setBookingDate(LocalDate.now());
                t1.setType(TransactionType.INCOME);
                t1.setCategory(gehalt);
                t1.setDescription("März Gehalt");

                Transaction t2 = new Transaction();
                t2.setAmount(new BigDecimal("55.40"));
                t2.setBookingDate(LocalDate.now().minusDays(1));
                t2.setType(TransactionType.EXPENSE);
                t2.setCategory(lebensmittel);
                t2.setDescription("Wocheneinkauf");

                transactionRepo.saveAll(List.of(t1, t2));

                System.out.println("Testdaten wurden erfolgreich angelegt.");
            }
        };
    }
}