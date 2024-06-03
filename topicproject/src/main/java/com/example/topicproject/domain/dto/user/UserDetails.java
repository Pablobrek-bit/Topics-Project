package com.example.topicproject.domain.dto.user;

import com.example.topicproject.domain.dto.profile.ProfileDetailsDTO;

public record UserDetails(
        String id,
        String name,
        String email,
        ProfileDetailsDTO profile
) {
}
