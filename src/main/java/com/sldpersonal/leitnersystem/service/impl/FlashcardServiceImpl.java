package com.sldpersonal.leitnersystem.service.impl;

import com.sldpersonal.leitnersystem.mapper.IFlashcardMapper;
import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;
import com.sldpersonal.leitnersystem.repository.FlashcardRepository;
import com.sldpersonal.leitnersystem.service.FlashcardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final IFlashcardMapper mapper;
    @Override
    public int createFlashCard(FlashcardCreateRequest request) {
        var flashCard = mapper.fromCreateToEntity(request);
        flashcardRepository.save(flashCard);
        return 1;
    }
}
