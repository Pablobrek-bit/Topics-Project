package com.example.topicproject.domain.dto.topic;

import com.example.topicproject.domain.dto.course.CourseDetailsDTO;
import com.example.topicproject.domain.dto.user.UserDetailsDTO;
import com.example.topicproject.domain.entities.Topic;

public record TopicDetailsDTO(
        String id,
        String title,
        String description,
        UserDetailsDTO user,
        CourseDetailsDTO course
) {
    public TopicDetailsDTO(Topic topic) {
        this(topic.getId(),topic.getTitle(), topic.getDescription(),
                new UserDetailsDTO(topic.getUser()),
                new CourseDetailsDTO(topic.getCourse()));
    }
}
