package com.example.topicproject.domain.dto.course;

import com.example.topicproject.domain.entities.Course;
import jakarta.validation.constraints.NotBlank;

public record CreateCourseDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Category is required")
        String category
) {
    public Course toEntity() {
        return Course.builder()
                .name(name)
                .category(category)
                .build();
    }
}
