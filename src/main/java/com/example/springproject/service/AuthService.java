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
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final int MAX_FAILED_ATTEMPTS = 3;

    public String login(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());

        if (user == null) {
            return "Usuário não encontrado";
        }


        if (user.isLocked()) {
            return "Conta bloqueada devido a muitas tentativas falhas de login";
        }


        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {

            user.setFailedAttempts(user.getFailedAttempts() + 1);


            if (user.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) {
                user.setLocked(true);
                userRepository.save(user);
                return "Conta bloqueada devido a muitas tentativas falhas de login";
            }

            userRepository.save(user);
            return "Credenciais inválidas";
        }


        user.setFailedAttempts(0);
        userRepository.save(user);

        String token = jwtTokenProvider.generateToken(user);

        return "Token gerado com sucesso: " + token;
    }


    public String register(UserDTO userDTO) {

        if (userRepository.findByEmail(userDTO.getEmail()) != null) {
            return "E-mail já está em uso";
        }


        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(encodedPassword);
        user.setFailedAttempts(0);
        user.setLocked(false);


        userRepository.save(user);

        return "Usuário registrado com sucesso";
    }

    public String logout() {
        return "Usuário deslogado";
    }
}
