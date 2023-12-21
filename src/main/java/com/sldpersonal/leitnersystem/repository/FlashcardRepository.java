package com.sldpersonal.leitnersystem.repository;

import com.sldpersonal.leitnersystem.collection.FlashCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FlashcardRepository extends MongoRepository<FlashCard, String> {
}
