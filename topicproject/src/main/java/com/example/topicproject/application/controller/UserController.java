package com.example.topicproject.application.controller;

import com.example.topicproject.domain.dto.user.CreateUserDTO;
import com.example.topicproject.domain.dto.user.UpdateUserDTO;
import com.example.topicproject.domain.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserDTO createUserDTO
            , UriComponentsBuilder uriComponentsBuilder) {
        var userId = userService.createUser(createUserDTO);

        var uri =
                uriComponentsBuilder.path("/users/{id}").buildAndExpand(userId.id()).toUri();

        return ResponseEntity.created(uri).body(userId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        var user = userService.getUser(id);

        return ResponseEntity.ok(user);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe(@RequestAttribute("id") String userId){
        var user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestAttribute("id") String userId,
                                        @RequestBody @Valid UpdateUserDTO updateUserDTO){
        System.out.println("chegou aqui");
        var user = userService.updateUser(userId, updateUserDTO);

        return ResponseEntity.ok(user);
    }

}
