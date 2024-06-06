package com.example.topicproject.domain.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
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

    @Column(nullable = false)
    private String message;

    @Column(name = "created_at",nullable = false)
    @Timestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String solution;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;


}
