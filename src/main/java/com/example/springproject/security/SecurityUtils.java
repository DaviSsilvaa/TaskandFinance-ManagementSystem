package com.example.springproject.security;

import com.example.springproject.exception.ResourceNotFoundException;
import com.example.springproject.model.User;
import com.example.springproject.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retorna o objeto User completo do usuário atualmente autenticado.
     * @return O objeto User.
     * @throws ResourceNotFoundException Se o usuário não for encontrado no banco de dados.
     */
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || "anonymousUser".equals(authentication.getPrincipal())) {
            throw new IllegalStateException("Nenhum usuário autenticado no contexto de segurança.");
        }


        String userEmail = authentication.getName();

        User user = userRepository.findByEmail(userEmail);

        if (user == null) {
            throw new ResourceNotFoundException("Usuário", "E-mail", userEmail);
        }

        return user;
    }
}
