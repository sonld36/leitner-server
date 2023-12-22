package com.sldpersonal.leitnersystem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class LearningTopicResponse {
    List<FlashcardResponse> everyday;
    List<FlashcardResponse> everyThreeDays;
    List<FlashcardResponse> everyWeek;
    List<FlashcardResponse> everyTwoWeeks;
    List<FlashcardResponse> everyMonth;
}
