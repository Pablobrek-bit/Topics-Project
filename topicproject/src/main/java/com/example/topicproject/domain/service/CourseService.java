package com.example.topicproject.domain.service;

import com.example.topicproject.domain.dto.course.CourseDetailsDTO;
import com.example.topicproject.domain.dto.course.CourseIdDTO;
import com.example.topicproject.domain.dto.course.CreateCourseDTO;
import com.example.topicproject.domain.entities.Course;
import com.example.topicproject.domain.repository.CourseRepository;
import com.example.topicproject.exceptions.CustomExceptions.CourseNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional
    public CourseIdDTO createCourse(CreateCourseDTO createCourseDTO){
        var course = courseRepository.save(createCourseDTO.toEntity());

        return new CourseIdDTO(course.getId());
    }

    public CourseDetailsDTO getCourse(Long id) {
        Course course = findById(id);

        return new CourseDetailsDTO(course.getId(), course.getName(),
                course.getCategory());

    }

    public Course findById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with " +
                        "id: " + id));
    }
}
