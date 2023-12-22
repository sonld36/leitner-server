package com.sldpersonal.leitnersystem.model;

import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Builder
@Data
public class AggregateFlashcardSession {
    private String flashcardId;
    private int timesCorrect;
    private int totalAppear;
    private int stroke;
}
