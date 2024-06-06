package com.example.topicproject.domain.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserDTO(
        String name,
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Password is required")
        String password,
        String newPassword
) {
}
