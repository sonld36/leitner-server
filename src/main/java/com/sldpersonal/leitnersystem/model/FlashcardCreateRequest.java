package com.sldpersonal.leitnersystem.model;

import com.sldpersonal.leitnersystem.common.Constant;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FlashcardCreateRequest {
    @NotNull
    private String answer;
    @NotNull
    @Max(value = 255, message = "Question must be less than 255 characters")
    private String question;
    private byte[] image;
    @NotNull
    private String topicId;
    private List<String> notes;
}
