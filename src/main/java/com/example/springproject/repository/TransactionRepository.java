package com.example.springproject.repository;

import com.example.springproject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retorna todas as transações de um usuário específico.
     */
    List<Transaction> findByUserId(Long userId);

    /**
     * Retorna uma transação específica por ID, garantindo que ela pertença ao usuário.
     */
    Optional<Transaction> findByIdAndUserId(Long id, Long userId);

    /**
     * Retorna as transações de um usuário dentro de um período (para relatórios).
     */
    List<Transaction> findByUserIdAndTransactionDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

}
