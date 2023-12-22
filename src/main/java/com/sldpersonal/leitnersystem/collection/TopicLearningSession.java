package com.sldpersonal.leitnersystem.collection;

import lombok.Builder;

import java.util.Date;

@Builder
public class TopicLearningSession {
    private int timesCorrect;
    private int timesIncorrect;
    private int totalCard;
    private Date timeStart;
    private Date timeEnd;
}
