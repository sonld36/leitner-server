package com.sldpersonal.leitnersystem.repository;

import com.sldpersonal.leitnersystem.collection.FlashCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FlashcardRepository extends MongoRepository<FlashCard, String> {
    List<FlashCard> findByTopicId(String topicId);

    List<FlashCard> saveAll(List<FlashCard> flashCards);
}
