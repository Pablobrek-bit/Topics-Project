package com.example.topicproject.domain.repository;

import com.example.topicproject.domain.entities.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, String> {
    List<Response> findAllByTopicId(String topicId);
}
