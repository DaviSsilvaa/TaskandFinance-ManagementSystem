package com.example.springproject.service;

import com.example.springproject.model.User;
import com.example.springproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Tentando carregar usuário com o e-mail: {}", email);

        User appUser = userRepository.findByEmail(email);

        if (appUser == null) {
            logger.error("Usuário não encontrado com o e-mail: {}", email);
            throw new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email);
        }

        logger.info("Usuário encontrado: {}", appUser.getEmail());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(appUser.getEmail())
                .password(appUser.getPassword())
                .roles("USER")
                .build();
    }


}
