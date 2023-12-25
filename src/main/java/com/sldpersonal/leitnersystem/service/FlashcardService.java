package com.sldpersonal.leitnersystem.service;

import com.sldpersonal.leitnersystem.collection.FlashCard;
import com.sldpersonal.leitnersystem.common.Constant;
import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;
import com.sldpersonal.leitnersystem.model.FlashcardResponse;
import com.sldpersonal.leitnersystem.model.PaginationResponse;

import java.util.List;

public interface FlashcardService {
    int createFlashCard(FlashcardCreateRequest request);

    List<FlashcardResponse> getByLevelBox(List<FlashcardResponse> flashCards, Constant.BoxLevel level);

    PaginationResponse<FlashcardResponse> getFlashCard(int page);

    List<FlashcardResponse> getFlashCardByTopicId(String topicId);
}
