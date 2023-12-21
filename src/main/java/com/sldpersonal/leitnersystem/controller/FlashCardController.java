package com.sldpersonal.leitnersystem.controller;

import com.sldpersonal.leitnersystem.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/flashcard")
@RequiredArgsConstructor
public class FlashCardController {
    private final FlashcardService flashCardService;

    @GetMapping
    public String getFlashCard() {
        return "Hello World";
    }

    @PostMapping
    public String createFlashCard() {
        return "Hello World";
    }
}
