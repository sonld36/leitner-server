package com.sldpersonal.leitnersystem.model;

import com.sldpersonal.leitnersystem.common.Constant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FlashcardCreateRequest {
    private String answer;
    private String question;
    private Constant.TypeTextFlashCard type;
    private String topicId;
    private List<String> notes;
}
