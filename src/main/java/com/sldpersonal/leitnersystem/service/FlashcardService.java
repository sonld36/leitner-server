package com.sldpersonal.leitnersystem.service;

import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;

public interface FlashcardService {
    int createFlashCard(FlashcardCreateRequest request);
}
