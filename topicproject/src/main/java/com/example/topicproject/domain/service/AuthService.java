package com.example.topicproject.domain.service;

import com.example.topicproject.domain.dto.auth.AuthDTO;
import com.example.topicproject.domain.dto.auth.TokenDTO;
import com.example.topicproject.domain.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenDTO auth(AuthDTO authDTO){
        String token = "";

        var tokenUsername = new UsernamePasswordAuthenticationToken(authDTO.email(),
                authDTO.password());

        var authentication = authenticationManager.authenticate(tokenUsername);

        if(authentication.isAuthenticated()){
            token = jwtService.generateToken((User) authentication.getPrincipal());
        }

        return new TokenDTO(token);
    }

}
