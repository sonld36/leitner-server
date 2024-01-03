package com.sldpersonal.leitnersystem.controller;

import com.sldpersonal.leitnersystem.model.*;
import com.sldpersonal.leitnersystem.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {
    private final TopicService topicService;

    @PostMapping
    public int createTopic(@RequestBody TopicCreateRequest request) {
        return topicService.createTopic(request);
    }

    @GetMapping
    public PaginationResponse<TopicResponse> getTopics(@RequestParam(defaultValue = "1") int page) {
        return topicService.getTopics(page);
    }

    @GetMapping("/{id}")
    public TopicInformationResponse getTopicInformation(@PathVariable String id) {
        var tmp = topicService.getTopicInformation(id);
        return tmp;
    }

    @GetMapping("/learn/{topicId}")
    public LearningTopicResponse getLearningTopic(@PathVariable String topicId) {
        return topicService.getLearningTopic(topicId);
    }



    @PostMapping("/learn-done/{topicId}")
    public void learnDone(@PathVariable String topicId, @RequestBody ResultLearningSessionDTO result) {
        topicService.learnDone(topicId, result);
    }

    @PutMapping("/{id}")
    public int updateTopic(@PathVariable String id, @RequestBody TopicCreateRequest request) {
        return topicService.updateTopic(id, request);
    }

    @DeleteMapping("/{id}")
    public int deleteTopic(@PathVariable String id) {
        return topicService.deleteTopic(id);
    }
}
