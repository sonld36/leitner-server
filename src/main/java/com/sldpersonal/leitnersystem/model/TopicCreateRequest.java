package com.sldpersonal.leitnersystem.model;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicCreateRequest {
    private String name;
    private String description;
    private String owner;
    private String[] tags;
}
