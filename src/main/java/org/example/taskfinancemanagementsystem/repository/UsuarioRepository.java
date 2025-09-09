package org.example.taskfinancemanagementsystem.repository;

import org.example.taskfinancemanagementsystem.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
