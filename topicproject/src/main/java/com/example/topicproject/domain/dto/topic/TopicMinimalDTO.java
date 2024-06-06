package com.example.topicproject.domain.dto.topic;

import com.example.topicproject.domain.entities.Topic;

public record TopicMinimalDTO(
        String id,
        String title,
        String description
) {
    public TopicMinimalDTO(Topic topic) {
        this(topic.getId(), topic.getTitle(), topic.getDescription());
    }

    public Topic toEntity(){
        return Topic.builder()
                .id(id)
                .title(title)
                .description(description)
                .build();
    }
}
