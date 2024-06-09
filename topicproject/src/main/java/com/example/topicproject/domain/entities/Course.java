package com.example.topicproject.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = true)
    private String name;
    @Column(nullable = true)
    private String category;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics = new ArrayList<>();

    @ManyToMany(mappedBy = "courses")
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }
}
