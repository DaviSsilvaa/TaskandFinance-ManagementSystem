package com.example.springproject.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.springproject.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY = "mySecretKey";  // Use uma chave secreta forte para produção
    private static final long EXPIRATION_TIME = 86400000; // 1 dia em milissegundos

    // Método para gerar o token
    public String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

        return JWT.create()
                .withSubject(user.getEmail())  // O e-mail será o sujeito do token
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Define a expiração do token
                .sign(algorithm);  // Gera o token JWT
    }

    // Método para validar o token
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();  // Retorna o e-mail do usuário autenticado
        } catch (Exception exception) {
            return null;  // Retorna null em caso de token inválido
        }
    }
}
