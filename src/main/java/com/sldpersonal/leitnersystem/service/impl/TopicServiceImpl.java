package com.sldpersonal.leitnersystem.service.impl;

import com.sldpersonal.leitnersystem.collection.TopicItem;
import com.sldpersonal.leitnersystem.mapper.ITopicMapper;
import com.sldpersonal.leitnersystem.model.PaginationResponse;
import com.sldpersonal.leitnersystem.model.TopicCreateRequest;
import com.sldpersonal.leitnersystem.model.TopicResponse;
import com.sldpersonal.leitnersystem.repository.TopicRepository;
import com.sldpersonal.leitnersystem.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final ITopicMapper mapper;

    @Override
    public int createTopic(TopicCreateRequest request) {
        var topic = TopicItem.builder().name(request.getName())
                .description(request.getDescription())
                .owner(request.getOwner())
                .tags(List.of(request.getTags()))
                .build();
        topicRepository.save(topic);
        return 1;
    }

    @Override
    public PaginationResponse<TopicResponse> getTopics(int page) {
        var topics = topicRepository.findAll(Pageable.ofSize(10).withPage(page - 1));
        var data = topics.getContent().stream().map(mapper::toTopicResponse).toList();

        return PaginationResponse.<TopicResponse>builder()
                .data(data)
                .totalElement((int) topics.getTotalElements())
                .totalPage(topics.getTotalPages())
                .build();
    }

    @Override
    public int updateTopic(String id, TopicCreateRequest request) {
        topicRepository.save(TopicItem.builder()
                .id(id)
                .name(request.getName())
                .description(request.getDescription())
                .owner(request.getOwner())
                .tags(List.of(request.getTags()))
                .build());
        return 1;
    }

    @Override
    public int deleteTopic(String id) {
        topicRepository.deleteById(id);
        return 1;
    }
}
