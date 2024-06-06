package com.example.topicproject.domain.dto.response;

import com.example.topicproject.domain.dto.topic.TopicMinimalDTO;
import com.example.topicproject.domain.dto.user.UserMinimalDTO;
import com.example.topicproject.domain.entities.Response;

import java.time.LocalDateTime;

public record ResponseDetailsDTO(
        String id,
        String message,
        String solution,
        LocalDateTime createdAt,
        TopicMinimalDTO topic,
        UserMinimalDTO user

) {
    public ResponseDetailsDTO(Response response) {
        this(response.getId(), response.getMessage(), response.getSolution(),
                response.getCreatedAt(), new TopicMinimalDTO(response.getTopic()),
                new UserMinimalDTO(response.getUser()));
    }


}
