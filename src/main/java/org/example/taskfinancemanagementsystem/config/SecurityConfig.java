package org.example.taskfinancemanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register", "/usuarios/**").permitAll()  // Permitir o acesso sem autenticação
                        .anyRequest().authenticated()  // Exigir autenticação para outras páginas
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Página de login personalizada
                        .permitAll()  // Permitir o acesso à página de login sem autenticação
                        .defaultSuccessUrl("/home", true)  // Página de sucesso após o login
                )
                .logout(logout -> logout
                        .permitAll()  // Permitir logout sem autenticação
                );
        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usando o BCrypt para criptografar senhas
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))  // Codificando a senha
                .roles("USER")  // Papel de usuário
                .build();

        return new InMemoryUserDetailsManager(user);  // Usuário criado em memória
    }
}
