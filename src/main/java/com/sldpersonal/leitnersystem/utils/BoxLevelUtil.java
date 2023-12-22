package com.sldpersonal.leitnersystem.utils;


import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class BoxLevelUtil {
    public static boolean isSufficientTimePassed(Date lastReview, int boxLevel) {
        if (lastReview == null) {
            return true;
        }
        Date currentDate = new Date(System.currentTimeMillis());
        Period period = Period.between(toLocalDate(lastReview), toLocalDate(currentDate));
        return switch (boxLevel) {
            case 2 -> isSufficientTimePassedForBoxLevel2(period);
            case 3 -> isSufficientTimePassedForBoxLevel3(period);
            case 4 -> isSufficientTimePassedForBoxLevel4(period);
            case 5 -> isSufficientTimePassedForBoxLevel5(period);
            default -> false;
        };
    }

    private static boolean isSufficientTimePassedForBoxLevel5(Period period) {
        return Math.abs(period.getDays()) >= 30;
    }

    private static boolean isSufficientTimePassedForBoxLevel4(Period period) {
        return Math.abs(period.getDays()) >= 14;
    }

    private static boolean isSufficientTimePassedForBoxLevel3(Period period) {
        return Math.abs(period.getDays()) >= 7;
    }

    private static boolean isSufficientTimePassedForBoxLevel2(Period period) {
        return Math.abs(period.getDays()) >= 3;
    }

    private static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
