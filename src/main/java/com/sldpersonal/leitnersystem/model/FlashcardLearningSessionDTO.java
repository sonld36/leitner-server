package com.sldpersonal.leitnersystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardLearningSessionDTO {
    private String id;

    @JsonFormat(shape = JsonFormat.Shape.BOOLEAN)
    private boolean isCorrect;
    private Instant timeAnswer;
}
