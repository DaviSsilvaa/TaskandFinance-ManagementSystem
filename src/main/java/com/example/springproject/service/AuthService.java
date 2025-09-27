package com.example.springproject.service;

import com.example.springproject.dto.UserDTO;
import com.example.springproject.model.User;
import com.example.springproject.repository.UserRepository;
import com.example.springproject.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;  // Para acessar o repositório de usuários

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // Para gerar e validar tokens JWT

    @Autowired
    private PasswordEncoder passwordEncoder;  // Para criptografar a senha

    private static final int MAX_FAILED_ATTEMPTS = 3; // Definindo o limite de tentativas falhas

    // Método de login com validação de credenciais
    public String login(UserDTO userDTO) {
        // Validar o e-mail e a senha
        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user == null) {
            return "Usuário não encontrado";
        }

        // Verificar se a conta está bloqueada
        if (user.isLocked()) {
            return "Conta bloqueada devido a muitas tentativas falhas de login";
        }

        // Verificar se a senha está correta
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            // Aumentar o contador de falhas
            user.setFailedAttempts(user.getFailedAttempts() + 1);

            // Se atingiu o limite de falhas, bloquear a conta
            if (user.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) {
                user.setLocked(true);
                userRepository.save(user); // Salva a conta como bloqueada
                return "Conta bloqueada devido a muitas tentativas falhas de login";
            }

            userRepository.save(user); // Salva as tentativas falhas atualizadas
            return "Credenciais inválidas";
        }

        // Se o login for bem-sucedido, resetar o contador de falhas
        user.setFailedAttempts(0);
        userRepository.save(user); // Resetando as tentativas falhas

        // Gerar o token JWT
        String token = jwtTokenProvider.generateToken(user);

        return "Token gerado com sucesso: " + token;
    }

    // Método de registro de novo usuário
    public String register(UserDTO userDTO) {
        // Verificar se o e-mail já existe
        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return "E-mail já está em uso";
        }

        // Criptografar a senha
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Criar o usuário
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(encodedPassword);  // Senha criptografada
        user.setFailedAttempts(0);  // Iniciar com 0 tentativas falhas
        user.setLocked(false);  // Iniciar com a conta desbloqueada

        // Salvar o usuário no banco de dados
        userRepository.save(user);

        return "Usuário registrado com sucesso";
    }

    // Método de logout
    public String logout() {
        // No caso do JWT, o logout pode ser feito simplesmente descartando o token no cliente
        return "Usuário deslogado";
    }
}
