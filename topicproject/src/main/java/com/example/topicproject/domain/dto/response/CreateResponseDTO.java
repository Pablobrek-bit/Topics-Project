package com.example.topicproject.domain.dto.response;

import com.example.topicproject.domain.dto.user.UserDetailsDTO;
import com.example.topicproject.domain.entities.Response;
import com.example.topicproject.domain.entities.Topic;
import lombok.Data;

import java.time.LocalDateTime;

//public record CreateResponseDTO(
//        String message,
//        String solution
//) {
//    public Response toEntity(Topic topic, UserDetailsDTO user) {
//        return Response.builder()
//                .message(message)
//                .solution(solution)
//                .topic(topic)
//                .user(user.toEntity())
//                .createdAt(LocalDateTime.now())
//                .build();
//    }
//}

@Data
public class CreateResponseDTO{
    private String message;
    private String solution;

    public CreateResponseDTO(String message, String solution) {
        this.message = message;
        this.solution = solution;
    }

    public Response toEntity(Topic topic, UserDetailsDTO user) {
        return Response.builder()
                .message(message)
                .solution(solution)
                .topic(topic)
                .user(user.toEntity())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
