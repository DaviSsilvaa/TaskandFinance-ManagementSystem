package com.example.springproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Método GET para mostrar o formulário de login
    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("Acessando página de login (GET /login)");
        return "login";  // Retorna a view de login (login.html)
    }
}
