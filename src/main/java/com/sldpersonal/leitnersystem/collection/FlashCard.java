package com.sldpersonal.leitnersystem.collection;

import com.sldpersonal.leitnersystem.common.Constant;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Document(collection = "flash_card_items")
public class FlashCard {
    private String id;
    private String answer;
    private String question;
    private Constant.TypeTextFlashCard type;
    private String topicId;
    private Constant.BoxLevel boxLevel;
    private Timestamp lastReview;
    private List<String> notes;
    private Date createdAt;
    private Date updatedAt;
}
