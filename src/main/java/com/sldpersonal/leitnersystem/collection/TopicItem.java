package com.sldpersonal.leitnersystem.collection;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.util.List;

@Document(collection = "topic_item")
@Builder
@Getter
public class TopicItem {
    private String id;
    private String name;
    private String description;
    private String owner;
    private List<String> tags;
    private Date createdAt;
    private Date updatedAt;
}
