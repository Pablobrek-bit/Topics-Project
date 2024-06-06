package com.example.topicproject.application.controller;

import com.example.topicproject.domain.dto.response.CreateResponseDTO;
import com.example.topicproject.domain.dto.response.ResponseDetailsDTO;
import com.example.topicproject.domain.dto.response.ResponseIdDTO;
import com.example.topicproject.domain.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/responses")
@RequiredArgsConstructor
public class ResponseController {

    private final ResponseService responseService;

    @PostMapping("/{courseId}/{topicId}")
    public ResponseEntity<ResponseIdDTO> createResponse(@PathVariable Long courseId,
                                                        @PathVariable String topicId,
                                                        @RequestBody CreateResponseDTO createResponseDTO,
                                                        @RequestAttribute("id") String userId,
                                                        UriComponentsBuilder uriComponentsBuilder){
        var responseIdDTO = responseService.createResponse(courseId, topicId, createResponseDTO, userId);

        var uri =
                uriComponentsBuilder.path("/responses/{id}").buildAndExpand(responseIdDTO.id()).toUri();

        return ResponseEntity.created(uri).body(responseIdDTO);
    }

    @GetMapping("/{responseId}")
    public ResponseEntity<ResponseDetailsDTO> getResponse(@PathVariable String responseId){
        return ResponseEntity.ok(responseService.getResponse(responseId));
    }

    @GetMapping("/{topicId}/list")
    public ResponseEntity<List<ResponseDetailsDTO>> listResponses(@PathVariable String topicId){
        return ResponseEntity.ok(responseService.listResponses(topicId));
    }

    @DeleteMapping("/{responseId}")
    public ResponseEntity<Void> deleteResponse(@PathVariable String responseId,
                                           @RequestAttribute("id") String userId){
        responseService.deleteResponse(responseId, userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{responseId}")
    public ResponseEntity<ResponseDetailsDTO> updateResponse(@PathVariable String responseId,
                                                             @RequestBody CreateResponseDTO createResponseDTO,
                                                             @RequestAttribute("id") String userId){
        return ResponseEntity.ok(responseService.updateResponse(responseId, createResponseDTO, userId));
    }

}
