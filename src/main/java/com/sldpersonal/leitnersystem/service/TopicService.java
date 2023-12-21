package com.sldpersonal.leitnersystem.service;

import com.sldpersonal.leitnersystem.model.PaginationResponse;
import com.sldpersonal.leitnersystem.model.TopicCreateRequest;
import com.sldpersonal.leitnersystem.model.TopicResponse;

public interface TopicService {
    int createTopic(TopicCreateRequest request);
    PaginationResponse<TopicResponse> getTopics(int page);
    int updateTopic(String id, TopicCreateRequest request);
    int deleteTopic(String id);
}
