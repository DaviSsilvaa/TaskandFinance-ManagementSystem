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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    // Configuração de CORS global, sem http.cors()
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of("http://localhost:3000"));  // Permite requisições de localhost:3000 (front-end React)
        cfg.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // Métodos permitidos
        cfg.setAllowedHeaders(List.of("Content-Type", "Authorization"));  // Cabeçalhos permitidos
        cfg.setAllowCredentials(true);  // Permite enviar cookies com as requisições

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);  // Aplica o CORS para todas as rotas
        return source;
    }

    // Configuração de segurança
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desabilita CSRF para APIs REST
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register", "/usuarios/**").permitAll()  // Permite acesso público a essas rotas
                        .anyRequest().authenticated()  // Requer autenticação para outras rotas
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Página de login personalizada
                        .permitAll()  // Permite acesso à página de login sem autenticação
                        .defaultSuccessUrl("/home", true)  // Página de sucesso após login
                )
                .logout(logout -> logout
                        .permitAll()  // Permite logout sem autenticação
                );
        return http.build();
    }

    // Bean para codificar as senhas usando BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usando o BCrypt para criptografar senhas
    }

    // Usuário em memória para testes
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password(passwordEncoder().encode("admin123"))  // Codifica a senha
                .roles("USER")  // Papel de usuário
                .build();

        return new InMemoryUserDetailsManager(user);  // Usuário criado em memória
    }
}
