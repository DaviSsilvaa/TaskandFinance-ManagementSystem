package com.example.springproject.repository;

import com.example.springproject.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Aqui você pode adicionar métodos personalizados se necessário, como buscar por status, por prazo, etc.
}
