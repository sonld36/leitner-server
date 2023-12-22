package com.sldpersonal.leitnersystem.collection;

import com.sldpersonal.leitnersystem.common.Constant;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;
import java.util.List;

@Builder
@Getter
@Document(collection = "flash_card_items")
public class FlashCard {
    private String id;
    private String answer;
    private String question;
    private Constant.TypeTextFlashCard type;
    private String topicId;
    private Constant.BoxLevel boxLevel;
    private boolean active;
    private Date lastReview;
    private List<String> notes;
    private Date createdAt;
    private Date updatedAt;

    public FlashCard onNextLevel() {
        if (this.boxLevel.getValue() == Constant.BoxLevel.EVERY_MONTH.getValue()) {
            return this;
        }
        this.boxLevel = Constant.BoxLevel.values()[this.boxLevel.getValue() + 1];
        return this;
    }

    public FlashCard onPreviousLevel() {
        if (this.boxLevel.getValue() == Constant.BoxLevel.EVERYDAY.getValue()) {
            return this;
        }
        this.boxLevel = Constant.BoxLevel.values()[this.boxLevel.getValue() - 1];
        return this;
    }
}
