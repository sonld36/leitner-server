package com.sldpersonal.leitnersystem.controller;

import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;
import com.sldpersonal.leitnersystem.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public int createFlashCard(@RequestBody FlashcardCreateRequest request) {
        return flashCardService.createFlashCard(request);
    }
}
