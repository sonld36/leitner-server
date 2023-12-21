package com.sldpersonal.leitnersystem.service.impl;

import com.sldpersonal.leitnersystem.repository.FlashcardRepository;
import com.sldpersonal.leitnersystem.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService {
    private final FlashcardRepository flashcardRepository;

}
