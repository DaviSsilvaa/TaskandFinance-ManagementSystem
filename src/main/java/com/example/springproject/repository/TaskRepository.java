package com.example.springproject.repository;

import com.example.springproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Retorna todas as tarefas associadas a um ID de usuário específico.
     */
    List<Task> findByUserId(Long userId);

    /**
     * Retorna uma tarefa específica por ID, garantindo que ela pertença ao usuário.
     */
    Optional<Task> findByIdAndUserId(Long id, Long userId);
}
