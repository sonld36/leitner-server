package com.sldpersonal.leitnersystem.model;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class TopicResponse {
    private String id;
    private String name;
    private String description;
    private List<String> tags;
}
