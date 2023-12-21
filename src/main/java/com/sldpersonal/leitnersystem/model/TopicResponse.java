package com.sldpersonal.leitnersystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class TopicResponse {
    private String id;
    private String name;
    private String description;
    private List<String> tags;
}
