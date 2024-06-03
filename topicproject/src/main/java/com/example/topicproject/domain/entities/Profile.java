package com.example.topicproject.domain.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "profiles")
    @Column(nullable = false)
    private List<User> users = new ArrayList<>();
}
