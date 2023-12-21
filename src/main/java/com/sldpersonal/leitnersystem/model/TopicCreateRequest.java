package com.sldpersonal.leitnersystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TopicCreateRequest {
    private String name;
    private String description;
    private String owner;
    private String[] tags;
}
