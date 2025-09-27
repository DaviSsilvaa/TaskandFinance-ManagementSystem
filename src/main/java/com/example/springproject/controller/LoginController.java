package com.example.springproject.controller;

import com.example.springproject.service.CustomUserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);  // Log para o controlador de Login

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error) {
        logger.info("Página de login acessada");

        if (error != null) {
            logger.warn("Falha no login: Credenciais inválidas");  // Log de erro se as credenciais forem inválidas
        }

        return "login";  // Renderiza a página de login
    }

}
