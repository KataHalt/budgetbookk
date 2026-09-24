package org.example.budgetbookk.controller;

import jakarta.validation.Valid;
import org.example.budgetbookk.model.Transaction;
import org.example.budgetbookk.repository.CategoryRepository;
import org.example.budgetbookk.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final CategoryRepository categoryRepository;

    public TransactionController(TransactionService transactionService, CategoryRepository categoryRepository) {
        this.transactionService = transactionService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listTransactions(Model model, Principal principal) {
        String username = principal.getName();
        model.addAttribute("transactions", transactionService.getTransactionsForUser(username));
        model.addAttribute("username", username);
        return "transactions/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("categories", categoryRepository.findAll());
        return "transactions/form";
    }

    @PostMapping
    public String saveTransaction(@Valid @ModelAttribute("transaction") Transaction transaction,
                                  BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "transactions/form";
        }
        transactionService.save(transaction, principal.getName());
        return "redirect:/transactions";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, Principal principal) {
        Transaction transaction = transactionService.getOwnedTransaction(id, principal.getName());
        model.addAttribute("transaction", transaction);
        model.addAttribute("categories", categoryRepository.findAll());
        return "transactions/form";
    }

    @PostMapping("/{id}")
    public String updateTransaction(@PathVariable Long id,
                                    @Valid @ModelAttribute("transaction") Transaction transaction,
                                    BindingResult bindingResult, Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryRepository.findAll());
            return "transactions/form";
        }
        transaction.setId(id);
        transactionService.save(transaction, principal.getName());
        return "redirect:/transactions";
    }

    @PostMapping("/{id}/delete")
    public String deleteTransaction(@PathVariable Long id, Principal principal) {
        transactionService.delete(id, principal.getName());
        return "redirect:/transactions";
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model, Principal principal) {
        return "transactions/statistics";
    }
}