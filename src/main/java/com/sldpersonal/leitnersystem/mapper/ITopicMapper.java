package com.sldpersonal.leitnersystem.mapper;

import com.sldpersonal.leitnersystem.collection.TopicItem;
import com.sldpersonal.leitnersystem.model.TopicCreateRequest;
import com.sldpersonal.leitnersystem.model.TopicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ITopicMapper {
    TopicResponse toTopicResponse(TopicItem topicItem);

    List<TopicResponse> toListTopicResponse(List<TopicItem> topicItems);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", expression = "java(new java.sql.Date(System.currentTimeMillis()))")
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "owner", expression = "java(request.getOwner() != null ? request.getOwner() : \"admin\")")
    TopicItem fromTopicCreateRequest(TopicCreateRequest request);
}
