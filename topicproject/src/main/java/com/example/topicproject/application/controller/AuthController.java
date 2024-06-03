package com.example.topicproject.application.controller;

import com.example.topicproject.domain.dto.auth.AuthDTO;
import com.example.topicproject.domain.dto.auth.TokenDTO;
import com.example.topicproject.domain.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid AuthDTO authDTO){
        TokenDTO token = authService.auth(authDTO);

        return ResponseEntity.ok(token);
    }

}
