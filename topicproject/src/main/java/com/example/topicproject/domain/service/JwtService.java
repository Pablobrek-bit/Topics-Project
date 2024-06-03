package com.example.topicproject.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.topicproject.domain.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;
    private final Long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;

    public String generateToken(User user){
        var algorithm = Algorithm.HMAC256(secretKey);

        return JWT.create()
                .withIssuer("auth0")
                .withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .withSubject(user.getId())
                .sign(algorithm);
    }

    public String validToken(String token){
        var algorithm = Algorithm.HMAC256(secretKey);
        var verifier = JWT.require(algorithm)
                .withIssuer("auth0")
                .build();

        return verifier.verify(token).getSubject();
    }

}
