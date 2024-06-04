package com.example.topicproject.application.controller;

import com.example.topicproject.domain.dto.course.CourseIdDTO;
import com.example.topicproject.domain.dto.course.CreateCourseDTO;
import com.example.topicproject.domain.service.CourseService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    @Transactional
    public ResponseEntity<CourseIdDTO> createCourse(@RequestBody @Valid CreateCourseDTO createCourseDTO, UriComponentsBuilder uriComponentsBuilder){
        var courseIdDTO = courseService.createCourse(createCourseDTO);

        var uri = uriComponentsBuilder.path("/courses/{id}")
                .buildAndExpand(courseIdDTO.id())
                .toUri();

        return ResponseEntity.created(uri).body(courseIdDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourse(@PathVariable Long id){
        var course = courseService.getCourse(id);

        return ResponseEntity.ok(course);
    }

}
