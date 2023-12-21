package com.sldpersonal.leitnersystem.repository;

import com.sldpersonal.leitnersystem.collection.TopicItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TopicRepository extends MongoRepository<TopicItem, String> {

}
