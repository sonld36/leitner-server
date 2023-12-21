package com.sldpersonal.leitnersystem.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TopicResponse {
    private String id;
    private String name;
    private String description;
}
