package com.sldpersonal.leitnersystem.utils;

public class BoxLevelUtil {
    public static int getProbabilityBoxLevel(int boxLevel) {
        return switch (boxLevel) {
            case 1 -> 4;
            case 2 -> 3;
            case 3 -> 2;
            case 4, 5 -> 1;
            default -> 0;
        };
    }

}
