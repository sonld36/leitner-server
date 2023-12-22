package com.sldpersonal.leitnersystem.model;

import com.sldpersonal.leitnersystem.common.Constant;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FlashcardResponse {
    private String id;
    private String question;
    private String answer;
    private Constant.TypeTextFlashCard type;
    private String topicId;
    private Constant.BoxLevel boxLevel;
    private String lastReview;
    private String[] notes;
}
