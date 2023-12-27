package com.sldpersonal.leitnersystem.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultLearningSessionDTO {
    List<FlashcardLearningSessionDTO> flashcardLearningSessionDTOList;
    private String topicId;
    private int timesCorrect;
    private int timesIncorrect;
    private Instant timeStart;
    private Instant timeEnd;
    private int totalCard;
}
