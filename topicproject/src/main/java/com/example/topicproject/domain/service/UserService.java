package com.example.topicproject.domain.service;

import com.example.topicproject.domain.dto.profile.ProfileDetailsDTO;
import com.example.topicproject.domain.dto.user.CreateUserDTO;
import com.example.topicproject.domain.dto.user.UserDetails;
import com.example.topicproject.domain.dto.user.UserIdDTO;
import com.example.topicproject.domain.entities.User;
import com.example.topicproject.domain.repository.UserRepository;
import com.example.topicproject.exceptions.CustomExceptions.EmailAlreadyRegistered;
import com.example.topicproject.exceptions.CustomExceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserIdDTO createUser(CreateUserDTO createUserDTO){
        verifyEmail(createUserDTO.email());

        var passwordHash = new BCryptPasswordEncoder().encode(createUserDTO.password());

        var newCreateUserDTO = new CreateUserDTO(createUserDTO.name(),
                createUserDTO.email(), passwordHash, createUserDTO.profile());

        User user = userRepository.save(newCreateUserDTO.toEntity());
        return new UserIdDTO(user.getId());
    }

    public UserDetails getUser(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        return new UserDetails(user.getId(), user.getName(), user.getEmail(),
                user.getProfiles().stream()
                        .findFirst()
                        .map(profile -> new ProfileDetailsDTO(profile.getName()))
                        .orElse(null));
    }

    private void verifyEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyRegistered("Email already exists");
        }
    }

}
