package com.sldpersonal.leitnersystem.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class TopicInformationResponse {
    private String id;
    private int totalCard;
    private BoxInformation boxesInformation;
    private List<FlashcardResponse> flashcards;

    @Builder
    @Getter
    public static class BoxInformation {
        private int everyDay;
        private int everyThreeDays;
        private int everyWeek;
        private int everyTwoWeeks;
        private int everyMonth;

    }
}
