package com.sldpersonal.leitnersystem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Builder
public class ResultLearningSessionDTO {
    List<FlashcardLearningSessionDTO> flashcardLearningSessionDTOList;
    private String topicId;
    private int timesCorrect;
    private int timesIncorrect;
    private Date timeStart;
    private Date timeEnd;
    private int totalCard;
}
