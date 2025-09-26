package com.example.springproject.config;

import com.example.springproject.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configura o PasswordEncoder (BCryptPasswordEncoder)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Configura o PasswordEncoder
    }

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    // Configura o UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(); // Usando o CustomUserDetailsService como Bean
    }

    // Configura o AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Configura as permissões e comportamento do Spring Security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Desabilita CSRF se necessário
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/login", "/register").permitAll()  // Permite acesso sem autenticação às páginas de login e registro
                        .anyRequest().authenticated()  // Exige autenticação para outras páginas
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Página de login personalizada
                        .loginProcessingUrl("/login")  // URL para o processamento do login
                        .defaultSuccessUrl("/home", true)  // Página de destino após login bem-sucedido
                        .permitAll()  // Permite acesso para todos
                        .failureUrl("/login?error=true")  // URL para o caso de erro no login
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL para logout
                        .permitAll()  // Permite acesso para todos
                );

        return http.build();
    }


}

