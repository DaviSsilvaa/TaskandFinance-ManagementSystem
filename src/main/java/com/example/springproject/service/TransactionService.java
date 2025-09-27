package com.example.springproject.service;

import com.example.springproject.dto.TransactionDTO;
import com.example.springproject.exception.ResourceNotFoundException;
import com.example.springproject.model.Category;
import com.example.springproject.model.Transaction;
import com.example.springproject.model.User;
import com.example.springproject.repository.CategoryRepository;
import com.example.springproject.repository.TransactionRepository;
import com.example.springproject.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final SecurityUtils securityUtils;

    public TransactionService(TransactionRepository transactionRepository, CategoryRepository categoryRepository, SecurityUtils securityUtils) {
        this.transactionRepository = transactionRepository;
        this.categoryRepository = categoryRepository;
        this.securityUtils = securityUtils;
    }

    /**
     * Converte a Entidade Transaction para o DTO de Transaction.
     */
    private TransactionDTO convertToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setDescription(transaction.getDescription());
        dto.setAmount(transaction.getAmount());
        dto.setType(transaction.getType().name());
        dto.setTransactionDate(transaction.getTransactionDate());

        if (transaction.getCategory() != null) {
            dto.setCategoryId(transaction.getCategory().getId());
            dto.setCategoryName(transaction.getCategory().getName());
        }
        return dto;
    }

    /**
     * Cria uma nova transação para o usuário autenticado.
     */
    @Transactional
    public TransactionDTO createTransaction(TransactionDTO transactionDTO) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Category category = null;
        if (transactionDTO.getCategoryId() != null) {
            category = categoryRepository.findById(transactionDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria", "ID", transactionDTO.getCategoryId()));
        }

        Transaction transaction = new Transaction();
        transaction.setDescription(transactionDTO.getDescription());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());

        try {
            transaction.setType(Transaction.Type.valueOf(transactionDTO.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de transação (INCOME/EXPENSE) inválido.");
        }

        transaction.setCategory(category);
        transaction.setUser(currentUser);

        Transaction savedTransaction = transactionRepository.save(transaction);
        return convertToDTO(savedTransaction);
    }

    /**
     * Retorna todas as transações do usuário autenticado.
     */
    public List<TransactionDTO> getAllTransactions() {
        User currentUser = securityUtils.getAuthenticatedUser();
        List<Transaction> transactions = transactionRepository.findByUserId(currentUser.getId());

        return transactions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retorna uma transação específica por ID (garantindo a posse pelo usuário).
     */
    public TransactionDTO getTransactionById(Long id) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Transaction transaction = transactionRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transação", "ID", id));

        return convertToDTO(transaction);
    }

    /**
     * Atualiza uma transação existente (garantindo a posse pelo usuário).
     */
    @Transactional
    public TransactionDTO updateTransaction(Long id, TransactionDTO transactionDTO) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Transaction transaction = transactionRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transação", "ID", id));

        Category category = null;
        if (transactionDTO.getCategoryId() != null) {
            category = categoryRepository.findById(transactionDTO.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoria", "ID", transactionDTO.getCategoryId()));
        }

        transaction.setDescription(transactionDTO.getDescription());
        transaction.setAmount(transactionDTO.getAmount());
        transaction.setTransactionDate(transactionDTO.getTransactionDate());

        try {
            transaction.setType(Transaction.Type.valueOf(transactionDTO.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de transação (INCOME/EXPENSE) inválido.");
        }

        transaction.setCategory(category);

        Transaction updatedTransaction = transactionRepository.save(transaction);
        return convertToDTO(updatedTransaction);
    }

    /**
     * Exclui uma transação (garantindo a posse pelo usuário).
     */
    public void deleteTransaction(Long id) {
        User currentUser = securityUtils.getAuthenticatedUser();

        Transaction transaction = transactionRepository.findByIdAndUserId(id, currentUser.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Transação", "ID", id));

        transactionRepository.delete(transaction);
    }


    /**
     * Retorna um resumo financeiro geral do usuário: Saldo, Total de Receitas e Total de Despesas.
     */
    public Map<String, BigDecimal> getFinancialSummary() {
        User currentUser = securityUtils.getAuthenticatedUser();
        List<Transaction> transactions = transactionRepository.findByUserId(currentUser.getId());

        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalExpense = BigDecimal.ZERO;

        for (Transaction t : transactions) {
            if (t.getType() == Transaction.Type.INCOME) {
                totalIncome = totalIncome.add(t.getAmount());
            } else if (t.getType() == Transaction.Type.EXPENSE) {
                totalExpense = totalExpense.add(t.getAmount());
            }
        }

        BigDecimal balance = totalIncome.subtract(totalExpense);

        Map<String, BigDecimal> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("balance", balance);

        return summary;
    }

    /**
     * Retorna um relatório financeiro mensal, agrupando despesas por categoria.
     */
    public Map<String, Object> getMonthlyReport(YearMonth yearMonth) {
        User currentUser = securityUtils.getAuthenticatedUser();

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        List<Transaction> monthlyTransactions = transactionRepository
                .findByUserIdAndTransactionDateBetween(currentUser.getId(), startDate, endDate);

        BigDecimal monthlyExpense = BigDecimal.ZERO;
        Map<String, BigDecimal> expensesByCategory = new HashMap<>();

        for (Transaction t : monthlyTransactions) {
            if (t.getType() == Transaction.Type.EXPENSE) {
                monthlyExpense = monthlyExpense.add(t.getAmount());

                String categoryName = t.getCategory() != null ? t.getCategory().getName() : "Sem Categoria";

                expensesByCategory.merge(categoryName, t.getAmount(), BigDecimal::add);
            }
        }

        Map<String, Object> report = new HashMap<>();
        report.put("month", yearMonth.toString());
        report.put("totalMonthlyExpense", monthlyExpense);
        report.put("expensesByCategory", expensesByCategory);

        return report;
    }
}
