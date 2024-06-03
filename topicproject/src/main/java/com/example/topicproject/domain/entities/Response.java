package com.example.topicproject.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "responses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String message;
    private LocalDateTime createdAt;
    private String solution;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;


}
