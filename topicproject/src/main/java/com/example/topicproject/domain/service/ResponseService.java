package com.example.topicproject.domain.service;

import com.example.topicproject.domain.dto.response.CreateResponseDTO;
import com.example.topicproject.domain.dto.response.ResponseDetailsDTO;
import com.example.topicproject.domain.dto.response.ResponseIdDTO;
import com.example.topicproject.domain.repository.ResponseRepository;
import com.example.topicproject.exceptions.CustomExceptions.ResponseNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseRepository responseRepository;
    private final TopicService topicService;
    private final UserService userService;
    private final CourseService courseService;

    public ResponseIdDTO createResponse(Long courseId, String topicId,
                               CreateResponseDTO createResponseDTO, String userId){

        courseService.findById(courseId);
        var topic = topicService.findById(topicId);
        var user = userService.findById(userId);

        var response = createResponseDTO.toEntity(topic, user);

        return new ResponseIdDTO(responseRepository.save(response).getId());
    }

    public ResponseDetailsDTO getResponse(String responseId){
        return findById(responseId);
    }

    private ResponseDetailsDTO findById(String responseId){
        var response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResponseNotFoundException("Response not found " +
                        "with id: "+ responseId));

        return new ResponseDetailsDTO(response);
    }


    public List<ResponseDetailsDTO> listResponses(String topicId) {
        var responses = responseRepository.findAllByTopicId(topicId);

        return responses.stream()
                .map(ResponseDetailsDTO::new)
                .toList();
    }

    public void deleteResponse(String responseId, String userId) {
        var response = findById(responseId);

        var isTheSameUser = response.user().id().equals(userId);

        if(!isTheSameUser){
            throw new ResponseNotFoundException("You don't have permission to delete this response");
        }

        responseRepository.deleteById(responseId);
    }


    public ResponseDetailsDTO updateResponse(String responseId, CreateResponseDTO createResponseDTO, String userId) {
        var response = responseRepository.findById(responseId)
                .orElseThrow(() -> new ResponseNotFoundException("Response not found " +
                        "with id: "+ responseId));

        var isTheSameUser = response.getUser().getId().equals(userId);

        if(!isTheSameUser){
            throw new ResponseNotFoundException("You don't have permission to update this response");
        }

        if (createResponseDTO.getMessage() != null) {
            response.setMessage(createResponseDTO.getMessage());
        }

        if (createResponseDTO.getSolution() != null) {
            response.setSolution(createResponseDTO.getSolution());
        }

        var newResponse = responseRepository.save(response);

        return new ResponseDetailsDTO(newResponse);

    }
}
