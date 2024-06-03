package com.example.topicproject.domain.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record AuthDTO(
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email should be valid")
        String email,
        @NotBlank(message = "Password is mandatory")
        @Length(min = 6, message = "Password must be at least 6 characters long")
        String password
) {
}
