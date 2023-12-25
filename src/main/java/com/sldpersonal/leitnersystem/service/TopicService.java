package com.sldpersonal.leitnersystem.service;

import com.sldpersonal.leitnersystem.model.*;

public interface TopicService {
    int createTopic(TopicCreateRequest request);
    PaginationResponse<TopicResponse> getTopics(int page);
    int updateTopic(String id, TopicCreateRequest request);
    int deleteTopic(String id);

    LearningTopicResponse getLearningTopic(String topicId);
    void learnDone(String topicId, ResultLearningSessionDTO result);

    TopicInformationResponse getTopicInformation(String id);
}
