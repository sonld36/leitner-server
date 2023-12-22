package com.sldpersonal.leitnersystem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class FlashcardLearningSessionDTO {
    private String id;
    private boolean isCorrect;
    private Date timeAnswer;
}
