package com.example.springproject.repository;

import com.example.springproject.model.User;  // A sua entidade JPA (não a do Spring Security)
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);  // Método para encontrar usuário por email
}
