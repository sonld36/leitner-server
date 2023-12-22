package com.sldpersonal.leitnersystem.service.impl;

import com.sldpersonal.leitnersystem.collection.FlashCard;
import com.sldpersonal.leitnersystem.common.Constant;
import com.sldpersonal.leitnersystem.mapper.IFlashcardMapper;
import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;
import com.sldpersonal.leitnersystem.model.FlashcardResponse;
import com.sldpersonal.leitnersystem.model.PaginationResponse;
import com.sldpersonal.leitnersystem.repository.FlashcardRepository;
import com.sldpersonal.leitnersystem.service.FlashcardService;
import com.sldpersonal.leitnersystem.utils.BoxLevelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardServiceImpl implements FlashcardService {
    private final FlashcardRepository flashcardRepository;
    private final IFlashcardMapper mapper;
    @Override
    public int createFlashCard(FlashcardCreateRequest request) {
        var flashCard = mapper.fromCreateRequest(request);
        flashcardRepository.save(flashCard);
        return 1;
    }

    @Override
    public List<FlashcardResponse> getByLevelBox(List<FlashCard> flashCards, Constant.BoxLevel level) {
        return flashCards.stream()
                .filter(flashCard -> flashCard.getBoxLevel() == level && BoxLevelUtil.isSufficientTimePassed(flashCard.getLastReview(), level.getValue()))
                .map(mapper::toFlashcardResponse)
                .toList();
    }

    @Override
    public PaginationResponse<FlashcardResponse> getFlashCard(int page) {
        var flashCards = flashcardRepository.findAll(Pageable.ofSize(10).withPage(page - 1));
        var data = flashCards.getContent().stream().map(mapper::toFlashcardResponse).toList();
        return PaginationResponse.<FlashcardResponse>builder()
                .data(data)
                .totalElement((int) flashCards.getTotalElements())
                .totalPage(flashCards.getTotalPages())
                .build();
    }

    @Override
    public List<FlashCard> getFlashCardByTopicId(String topicId) {
        return flashcardRepository.findByTopicId(topicId);
    }
}
