package com.example.topicproject.domain.dto.profile;

import com.example.topicproject.domain.entities.Profile;

public record ProfileDetailsDTO(
        String id,
        String name
) {
    public Profile toEntity() {
        return Profile.builder()
                .name(this.name)
                .id(this.id)
                .build();
    }
}
