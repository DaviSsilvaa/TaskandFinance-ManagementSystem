package org.example.taskfinancemanagementsystem.repository;

import org.example.taskfinancemanagementsystem.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
}
