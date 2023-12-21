package com.sldpersonal.leitnersystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class PaginationResponse<T> {
    @JsonProperty("data")
    private List<T> data;
    @JsonProperty("total_element")
    private int totalElement;
    @JsonProperty("total_page")
    private int totalPage;
}
