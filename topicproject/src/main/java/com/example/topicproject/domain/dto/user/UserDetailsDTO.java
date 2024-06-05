package com.example.topicproject.domain.dto.user;

import com.example.topicproject.domain.dto.profile.ProfileDetailsDTO;
import com.example.topicproject.domain.entities.User;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
//public record UserDetailsDTO(
//        String id,
//        String name,
//        String email,
//        String password,
//        ProfileDetailsDTO profile
//) {
//    public UserDetailsDTO(User user) {
//        this(user.getId(), user.getName(), user.getEmail(),user.getPassword(),
//                new ProfileDetailsDTO(user.getProfiles().get(0).getName()));
//    }
//}

@Data
@ToString
public class UserDetailsDTO{
    private String id;
    private String name;
    private String email;
    private String password;
    private ProfileDetailsDTO profile;

    public UserDetailsDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.profile = new ProfileDetailsDTO(user.getProfiles().get(0).getId(),
        user.getProfiles().get(0).getName());
    }

    public User toEntity() {
        return User.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .profiles(List.of(this.profile.toEntity()))
                .createdAt(LocalDateTime.now())
                .build();
    }
}