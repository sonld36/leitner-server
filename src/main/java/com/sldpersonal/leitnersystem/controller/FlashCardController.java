package com.sldpersonal.leitnersystem.controller;

import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;
import com.sldpersonal.leitnersystem.model.FlashcardResponse;
import com.sldpersonal.leitnersystem.model.PaginationResponse;
import com.sldpersonal.leitnersystem.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flashcard")
@RequiredArgsConstructor
public class FlashCardController {
    private final FlashcardService flashCardService;

    @GetMapping
    public PaginationResponse<FlashcardResponse> getFlashCard(@RequestParam int page) {
        return flashCardService.getFlashCard(page);
    }

    @PostMapping
    public int createFlashCard(@RequestBody FlashcardCreateRequest request) {
        return flashCardService.createFlashCard(request);
    }
}
