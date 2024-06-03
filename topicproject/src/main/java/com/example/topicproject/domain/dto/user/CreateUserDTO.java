package com.example.topicproject.domain.dto.user;

import com.example.topicproject.domain.entities.Profile;
import com.example.topicproject.domain.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

public record CreateUserDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email")
        String email,
        @NotBlank(message = "Password is required")
        @Length(min = 6, message = "Password must be at least 6 characters")
        String password,

        Profile profile
) {
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .profiles(profile == null ? List.of() : List.of(profile))
                .createdAt(LocalDateTime.now())
                .build();
    }
}
