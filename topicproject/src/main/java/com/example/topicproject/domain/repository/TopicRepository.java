package com.example.topicproject.domain.repository;

import com.example.topicproject.domain.entities.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, String> {
    List<Topic> findByCourseId(Long courseId);
}
