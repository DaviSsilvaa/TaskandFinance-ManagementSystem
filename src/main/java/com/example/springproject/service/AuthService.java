package com.example.springproject.service;

import com.example.springproject.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String login(UserDTO userDTO) {
        // Lógica de autenticação
        return "Token JWT";
    }

    public String register(UserDTO userDTO) {
        // Lógica de registro de usuário
        return "Usuário registrado com sucesso";
    }

    public String logout() {
        // Lógica de logout
        return "Usuário deslogado";
    }
}
