package com.example.topicproject.domain.dto.user;

import com.example.topicproject.domain.entities.User;

public record UserMinimalDTO(
        String id,
        String email,
        String name
) {
    public UserMinimalDTO(User user) {
        this(user.getId(), user.getEmail(), user.getName());
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .email(email)
                .name(name)
                .build();
    }
}
