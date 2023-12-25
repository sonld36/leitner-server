package com.sldpersonal.leitnersystem.mapper;

import com.sldpersonal.leitnersystem.collection.FlashCard;
import com.sldpersonal.leitnersystem.model.FlashcardCreateRequest;
import com.sldpersonal.leitnersystem.model.FlashcardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IFlashcardMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "boxLevel", expression = "java(com.sldpersonal.leitnersystem.common.Constant.BoxLevel.EVERYDAY)")
    @Mapping(target = "lastReview", ignore = true)
    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "createdAt", expression = "java(new java.sql.Date(System.currentTimeMillis()))")
    @Mapping(target = "updatedAt", ignore = true)
    FlashCard fromCreateRequest(FlashcardCreateRequest request);

    FlashcardResponse toFlashcardResponse(FlashCard flashCard);

    List<FlashcardResponse> toListFlashcardResponse(List<FlashCard> flashCards);
}
