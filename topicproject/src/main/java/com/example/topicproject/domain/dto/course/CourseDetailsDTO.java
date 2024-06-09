package com.example.topicproject.domain.dto.course;

import com.example.topicproject.domain.dto.user.UserMinimalDTO;
import com.example.topicproject.domain.entities.Course;

import java.util.List;

public record CourseDetailsDTO(
        Long id,
        String name,
        String category,
        List<UserMinimalDTO> users
) {
    public CourseDetailsDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategory(),
                course.getUsers().stream().map(UserMinimalDTO::new).toList());
    }
}
