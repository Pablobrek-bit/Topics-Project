package com.example.topicproject.domain.dto.course;

import com.example.topicproject.domain.entities.Course;

public record CourseDetailsDTO(
        Long id,
        String name,
        String category
) {
    public CourseDetailsDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory());
    }
}
