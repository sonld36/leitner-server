package com.sldpersonal.leitnersystem.model.event;

import com.sldpersonal.leitnersystem.model.FlashcardLearningSessionDTO;

import java.util.List;

public record UpdateBoxFlashcardEvent(
    List<FlashcardLearningSessionDTO> flashcardsSession
) {
}
