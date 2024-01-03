package com.sldpersonal.leitnersystem.model;

import com.sldpersonal.leitnersystem.common.Constant;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Builder
@Getter
public class FlashcardResponse {
    private String id;
    private String question;
    private String answer;
    private byte[] image;
    private String topicId;
    private Constant.BoxLevel boxLevel;
    private Date lastReview;
    private String[] notes;
}
