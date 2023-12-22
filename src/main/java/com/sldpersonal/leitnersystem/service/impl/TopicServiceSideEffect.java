package com.sldpersonal.leitnersystem.service.impl;

import com.sldpersonal.leitnersystem.collection.FlashCard;
import com.sldpersonal.leitnersystem.model.AggregateFlashcardSession;
import com.sldpersonal.leitnersystem.model.FlashcardLearningSessionDTO;
import com.sldpersonal.leitnersystem.model.event.UpdateBoxFlashcardEvent;
import com.sldpersonal.leitnersystem.repository.FlashcardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TopicServiceSideEffect {
    private final FlashcardRepository flashcardRepository;

    @EventListener(UpdateBoxFlashcardEvent.class)
    public void updateBoxFlashcard(UpdateBoxFlashcardEvent event) {
        var aggregated = aggregate(event.flashcardsSession());
        var flashcardsUpdate = aggregated.stream().map(aggregateFlashcardSession -> {
            var flashcard = flashcardRepository.findById(aggregateFlashcardSession.getFlashcardId()).orElseThrow();
            return getNextLevel(aggregateFlashcardSession, flashcard);
        }).toList();
        flashcardRepository.saveAll(flashcardsUpdate);
    }

    private List<AggregateFlashcardSession> aggregate(List<FlashcardLearningSessionDTO> flashcardsSession) {
        var sortedByTimeAnswer = flashcardsSession.stream()
                .sorted(Comparator.comparing(FlashcardLearningSessionDTO::getTimeAnswer))
                .toList();
        Map<String, AggregateFlashcardSession> aggregateFlashcardSessionMap = new HashMap<>();
        sortedByTimeAnswer.forEach(flashcardLearningSessionDTO -> {
            var aggregateFlashcardSession = aggregateFlashcardSessionMap.get(flashcardLearningSessionDTO.getId());
            if (aggregateFlashcardSession == null) {
                aggregateFlashcardSession = AggregateFlashcardSession.builder()
                        .flashcardId(flashcardLearningSessionDTO.getId())
                        .timesCorrect(0)
                        .totalAppear(0)
                        .build();
            }
            if (flashcardLearningSessionDTO.isCorrect()) {
                aggregateFlashcardSession.setTimesCorrect(aggregateFlashcardSession.getTimesCorrect() + 1);
                aggregateFlashcardSession.setStroke(aggregateFlashcardSession.getStroke() + 1);
            } else {
                aggregateFlashcardSession.setStroke(0);
            }
            aggregateFlashcardSession.setTotalAppear(aggregateFlashcardSession.getTotalAppear() + 1);

            aggregateFlashcardSessionMap.put(flashcardLearningSessionDTO.getId(), aggregateFlashcardSession);
        });
        return aggregateFlashcardSessionMap.values().stream().toList();
    }

    private FlashCard getNextLevel(AggregateFlashcardSession flashcardSession, FlashCard flashcard) {
        switch (flashcard.getBoxLevel()) {
            case EVERYDAY -> {
                var couldNext = (double) flashcardSession.getStroke() / flashcardSession.getTotalAppear() >= 0.75;
                return couldNext ? flashcard.onNextLevel() : flashcard;
            }
            case EVERY_THREE_DAYS, EVERY_TWO_WEEKS -> {
                var checkRotation = flashcardSession.getStroke() / flashcardSession.getTotalAppear();
                if (checkRotation >= 0.85) {
                    return flashcard.onNextLevel();
                } else if (checkRotation < 0.75) {
                    return flashcard.onPreviousLevel();
                }
                return flashcard;
            }
            case EVERY_WEEK, EVERY_MONTH -> {
                var checkRotation = (double) flashcardSession.getStroke() / flashcardSession.getTotalAppear();
                if (checkRotation >= 0.95) {
                    return flashcard.onNextLevel();
                } else if (checkRotation < 0.85) {
                    return flashcard.onPreviousLevel();
                }
                return flashcard;
            }

            default -> throw new IllegalStateException("Unexpected value: " + flashcard.getBoxLevel());
        }
    }
}