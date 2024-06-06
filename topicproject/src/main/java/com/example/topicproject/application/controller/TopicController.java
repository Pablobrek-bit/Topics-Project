package com.example.topicproject.application.controller;

import com.example.topicproject.domain.dto.topic.CreateTopicDTO;
import com.example.topicproject.domain.dto.topic.TopicDetailsDTO;
import com.example.topicproject.domain.dto.topic.TopicIdDTO;
import com.example.topicproject.domain.dto.topic.UpdateTopicDTO;
import com.example.topicproject.domain.service.TopicService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping("/{courseId}")
    @Transactional
    public ResponseEntity<TopicIdDTO> createTopic(@PathVariable Long courseId,
                                                  @RequestBody @Valid CreateTopicDTO createTopicDTO,
                                                  @RequestAttribute("id") String userId,
                                                  UriComponentsBuilder uriComponentsBuilder){


        var topicId = topicService.createTopic(courseId, createTopicDTO, userId);

        var uri =
                uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicId.id()).toUri();

        return ResponseEntity.created(uri).body(topicId);
    }

    @GetMapping("/{topicId}")
    public ResponseEntity<TopicDetailsDTO> getTopic(@PathVariable String topicId){
        var topic = topicService.getTopic(topicId);

        return ResponseEntity.ok(topic);
    }

    @GetMapping("/{courseId}/list")
    public ResponseEntity<?> listTopics(@PathVariable Long courseId){
        var topics = topicService.listTopicsByCourse(courseId);

        return ResponseEntity.ok(topics);
    }

    @DeleteMapping("/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable String topicId,
                                         @RequestAttribute("id") String userId){
        topicService.deleteTopic(topicId, userId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{topicId}")
    public ResponseEntity<?> updateTopic(@PathVariable String topicId,
                                         @RequestAttribute("id") String userId,
                                         @RequestBody @Valid UpdateTopicDTO updateTopicDTO){

        var topic = topicService.updateTopic(topicId, userId, updateTopicDTO);

        return ResponseEntity.ok(topic);
    }



}
