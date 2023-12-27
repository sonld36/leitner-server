package com.sldpersonal.leitnersystem.service.impl;

import com.sldpersonal.leitnersystem.collection.TopicItem;
import com.sldpersonal.leitnersystem.collection.TopicLearningSession;
import com.sldpersonal.leitnersystem.common.Constant;
import com.sldpersonal.leitnersystem.mapper.ITopicMapper;
import com.sldpersonal.leitnersystem.model.*;
import com.sldpersonal.leitnersystem.model.event.UpdateBoxFlashcardEvent;
import com.sldpersonal.leitnersystem.repository.TopicRepository;
import com.sldpersonal.leitnersystem.service.FlashcardService;
import com.sldpersonal.leitnersystem.service.TopicService;
import com.sldpersonal.leitnersystem.utils.BoxLevelUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;
    private final ITopicMapper mapper;
    private final FlashcardService flashcardService;
    private final ApplicationEventPublisher publisher;

    @Override
    public int createTopic(TopicCreateRequest request) {
        var topic = mapper.fromTopicCreateRequest(request);
        topicRepository.save(topic);
        return 1;
    }

    @Override
    public PaginationResponse<TopicResponse> getTopics(int page) {
        var topics = topicRepository.findAll(Pageable.ofSize(10).withPage(page - 1));
        var data = topics.getContent().stream().map(mapper::toTopicResponse).toList();

        return PaginationResponse.<TopicResponse>builder()
                .data(data)
                .totalElement((int) topics.getTotalElements())
                .totalPage(topics.getTotalPages())
                .build();
    }

    @Override
    public int updateTopic(String id, TopicCreateRequest request) {
        topicRepository.save(TopicItem.builder()
                .id(id)
                .name(request.getName())
                .description(request.getDescription())
                .owner(request.getOwner())
                .tags(List.of(request.getTags()))
                .build());
        return 1;
    }

    @Override
    public int deleteTopic(String id) {
        topicRepository.deleteById(id);
        return 1;
    }

    @Override
    public LearningTopicResponse getLearningTopic(String topicId) {
        var flashCards = flashcardService.getFlashCardByTopicId(topicId);
        var everyDayCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERYDAY)
                .stream().filter(flashcardResponse ->
                        BoxLevelUtil.isSufficientTimePassed(flashcardResponse.getLastReview(), Constant.BoxLevel.EVERYDAY.getValue()))
                .toList();
        var everyThreeDaysCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_THREE_DAYS)
                .stream().filter(flashcardResponse ->
                        BoxLevelUtil.isSufficientTimePassed(flashcardResponse.getLastReview(), Constant.BoxLevel.EVERY_THREE_DAYS.getValue()))
                .toList();
        var everyTwoWeeksCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_TWO_WEEKS)
                .stream().filter(flashcardResponse ->
                        BoxLevelUtil.isSufficientTimePassed(flashcardResponse.getLastReview(), Constant.BoxLevel.EVERY_TWO_WEEKS.getValue()))
                .toList();
        var everyWeekCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_WEEK)
                .stream().filter(flashcardResponse ->
                        BoxLevelUtil.isSufficientTimePassed(flashcardResponse.getLastReview(), Constant.BoxLevel.EVERY_WEEK.getValue()))
                .toList();
        var everyMonthCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_MONTH)
                .stream().filter(flashcardResponse ->
                        BoxLevelUtil.isSufficientTimePassed(flashcardResponse.getLastReview(), Constant.BoxLevel.EVERY_MONTH.getValue()))
                .toList();
        List<FlashcardResponse> merge = new ArrayList<>();
        merge.addAll(everyDayCards);
        merge.addAll(everyThreeDaysCards);
        merge.addAll(everyTwoWeeksCards);
        merge.addAll(everyWeekCards);
        merge.addAll(everyMonthCards);
        Collections.shuffle(merge);

        return LearningTopicResponse.builder()
                .everyday(everyDayCards)
                .everyThreeDays(everyThreeDaysCards)
                .everyTwoWeeks(everyTwoWeeksCards)
                .everyWeek(everyWeekCards)
                .everyMonth(everyMonthCards)
                .shuffle(merge)
                .build();
    }

    @Override
    public void learnDone(String topicId, ResultLearningSessionDTO result) {
        var topic = topicRepository.findById(topicId).orElseThrow();
        var topicLearningSession = mapToTopicLearningSession(result);
        topic.addSession(topicLearningSession);
        topicRepository.save(topic);
        publisher.publishEvent(new UpdateBoxFlashcardEvent(result.getFlashcardLearningSessionDTOList()));
    }

    @Override
    public TopicInformationResponse getTopicInformation(String id) {
        var flashCards = flashcardService.getFlashCardByTopicId(id);
        var everyDayCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERYDAY);
        var everyThreeDaysCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_THREE_DAYS);
        var everyTwoWeeksCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_TWO_WEEKS);
        var everyWeekCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_WEEK);
        var everyMonthCards = flashcardService.getByLevelBox(flashCards, Constant.BoxLevel.EVERY_MONTH);
        return TopicInformationResponse.builder()
                .id(id)
                .boxesInformation(
                        TopicInformationResponse.BoxInformation.builder()
                                .everyDay(everyDayCards.size())
                                .everyThreeDays(everyThreeDaysCards.size())
                                .everyTwoWeeks(everyTwoWeeksCards.size())
                                .everyWeek(everyWeekCards.size())
                                .everyMonth(everyMonthCards.size())
                                .build()
                )
                .flashcards(flashCards)
                .totalCard(flashCards.size())
                .build();
    }

    private TopicLearningSession mapToTopicLearningSession(ResultLearningSessionDTO result) {
        return TopicLearningSession.builder()
                .timeEnd(Date.from(result.getTimeEnd()))
                .timeStart(Date.from(result.getTimeStart()))
                .timesCorrect(result.getTimesCorrect())
                .timesIncorrect(result.getTimesIncorrect())
                .totalCard(result.getTotalCard())
                .build();
    }
}
