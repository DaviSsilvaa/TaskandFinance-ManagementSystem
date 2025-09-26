package com.example.springproject.service;

import com.example.springproject.model.User;
import com.example.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;  // Importando PasswordEncoder
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Carregar o usuário com base no email
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }

        // Aqui, a senha deve ser criptografada no banco de dados (verifique se a senha é criptografada antes de comparar)
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())  // Usando o email como nome de usuário
                .password(user.getPassword())  // A senha deve estar criptografada no banco
                .roles("USER")  // Você pode adicionar mais roles conforme necessário
                .build();
    }
}


