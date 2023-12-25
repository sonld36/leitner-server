package com.sldpersonal.leitnersystem.collection;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "topic_items")
@Builder
@Getter
public class TopicItem {
    private String id;
    private String name;
    private String description;
    private String owner;
    private List<String> tags;
    private List<TopicLearningSession> sessions;
    private boolean active;
    private Date createdAt;
    private Date updatedAt;
}
