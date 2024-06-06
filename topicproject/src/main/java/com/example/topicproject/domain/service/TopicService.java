package com.example.topicproject.domain.service;

import com.example.topicproject.domain.dto.topic.CreateTopicDTO;
import com.example.topicproject.domain.dto.topic.TopicDetailsDTO;
import com.example.topicproject.domain.dto.topic.TopicIdDTO;
import com.example.topicproject.domain.dto.topic.UpdateTopicDTO;
import com.example.topicproject.domain.entities.Topic;
import com.example.topicproject.domain.repository.TopicRepository;
import com.example.topicproject.exceptions.CustomExceptions.TopicNotFoundException;
import com.example.topicproject.exceptions.CustomExceptions.UnauthorizedActivityException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicService {
    private final CourseService courseService;
    private final UserService userService;
    private final TopicRepository topicRepository;

    @Transactional
    public TopicIdDTO createTopic(Long courseId, CreateTopicDTO createTopicDTO, String userId) {
        var course = courseService.findById(courseId);
        var user = userService.findById(userId);

        var topic = createTopicDTO.toEntity(course, user);

        var newTopic = topicRepository.save(topic);

        return new TopicIdDTO(newTopic.getId());
    }


    public TopicDetailsDTO getTopic(String topicId) {
        var topic = findById(topicId);

        return new TopicDetailsDTO(topic);
    }

    public List<TopicDetailsDTO> listTopicsByCourse(Long courseId){
        courseService.findById(courseId);

        var listTopics = topicRepository.findByCourseId(courseId);

        return listTopics.stream()
                .map(TopicDetailsDTO::new)
                .toList();
    }

    public Topic findById(String topicId){
        return topicRepository.findById(topicId)
                .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    }

    @Transactional
    public void deleteTopic(String topicId, String userId) {
        var user = userService.findById(userId);

        var topic = findById(topicId);

        if(!topic.getUser().getId().equals(user.getId())){
            throw new UnauthorizedActivityException("You are not allowed to delete this topic");
        }

        topicRepository.delete(topic);
    }

    public TopicDetailsDTO updateTopic(String topicId, String userId, UpdateTopicDTO updateTopicDTO) {
        var user = userService.findById(userId);

        var topic = findById(topicId);

        var isUserAllowed = topic.getUser().getId().equals(user.getId());

        if(!isUserAllowed){
            throw new UnauthorizedActivityException("You are not allowed to update this topic");
        }

        if(updateTopicDTO.title() != null){
            topic.setTitle(updateTopicDTO.title());
        }

        if(updateTopicDTO.description() != null){
            topic.setDescription(updateTopicDTO.description());
        }

        var newTopic = topicRepository.save(topic);

        return new TopicDetailsDTO(newTopic);
    }
}
