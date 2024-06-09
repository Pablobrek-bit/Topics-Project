package com.example.topicproject.domain.service;

import com.example.topicproject.domain.dto.user.CreateUserDTO;
import com.example.topicproject.domain.dto.user.UpdateUserDTO;
import com.example.topicproject.domain.dto.user.UserDetailsDTO;
import com.example.topicproject.domain.dto.user.UserIdDTO;
import com.example.topicproject.domain.entities.User;
import com.example.topicproject.domain.repository.UserRepository;
import com.example.topicproject.exceptions.CustomExceptions.EmailAlreadyRegistered;
import com.example.topicproject.exceptions.CustomExceptions.UnauthorizedActivityException;
import com.example.topicproject.exceptions.CustomExceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CourseService courseService;

    @Transactional
    public UserIdDTO createUser(CreateUserDTO createUserDTO){
        verifyEmail(createUserDTO.email());

        var passwordHash = new BCryptPasswordEncoder().encode(createUserDTO.password());

        var newCreateUserDTO = new CreateUserDTO(createUserDTO.name(),
                createUserDTO.email(), passwordHash, createUserDTO.profile());

        User user = userRepository.save(newCreateUserDTO.toEntity());
        return new UserIdDTO(user.getId());
    }

    public UserDetailsDTO getUser(String id){
        return findById(id);
    }

    public UserDetailsDTO findById(String id){
        return userRepository.findById(id)
                .map(UserDetailsDTO::new)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));


    }

    private void verifyEmail(String email){
        if(userRepository.existsByEmail(email)){
            throw new EmailAlreadyRegistered("Email already exists");
        }
    }

    @Transactional
    public UserDetailsDTO updateUser(String userId, UpdateUserDTO updateUserDTO) {
        var user = findById(userId);

        var passwordMatches =
                new BCryptPasswordEncoder().matches(updateUserDTO.password(),
                        user.getPassword());

        if(!passwordMatches){
            throw new UnauthorizedActivityException("Invalid password");
        }

        if(updateUserDTO.name() != null){
            user.setName(updateUserDTO.name());
        }

        if(updateUserDTO.email() != null){
            verifyEmail(updateUserDTO.email());
            user.setEmail(updateUserDTO.email());
        }

        if(updateUserDTO.newPassword() != null){
            user.setPassword(new BCryptPasswordEncoder().encode(updateUserDTO.newPassword()));
        }

        var newUser = user.toEntity();

        var updatedUser = userRepository.save(newUser);

        return new UserDetailsDTO(updatedUser);
    }

    @Transactional
    public void registerUserInCourse(String userId, Long courseId) {
        System.out.println("chegou aqui");
        var course = courseService.findById(courseId);

        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        course.getUsers().add(user);

        user.getCourses().add(course);

        userRepository.save(user);
    }
}
