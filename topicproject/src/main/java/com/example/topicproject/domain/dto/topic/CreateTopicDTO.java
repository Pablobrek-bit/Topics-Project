package com.example.topicproject.domain.dto.topic;

import com.example.topicproject.domain.dto.user.UserDetailsDTO;
import com.example.topicproject.domain.entities.Course;
import com.example.topicproject.domain.entities.Topic;
import jakarta.validation.constraints.NotBlank;

public record CreateTopicDTO(
        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Description is required")
        String description
) {
        public Topic toEntity(Course course, UserDetailsDTO user) {
                return Topic.builder()
                        .title(title)
                        .description(description)
                        .course(course)
                        .user(user.toEntity())
                        .build();
        }
}
