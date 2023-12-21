package com.sldpersonal.leitnersystem.controller;

import com.sldpersonal.leitnersystem.model.PaginationResponse;
import com.sldpersonal.leitnersystem.model.TopicCreateRequest;
import com.sldpersonal.leitnersystem.model.TopicResponse;
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

    @PutMapping("/{id}")
    public int updateTopic(@PathVariable String id, @RequestBody TopicCreateRequest request) {
        return topicService.updateTopic(id, request);
    }

    @DeleteMapping("/{id}")
    public int deleteTopic(@PathVariable String id) {
        return topicService.deleteTopic(id);
    }
}
