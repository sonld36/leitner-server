package com.sldpersonal.leitnersystem.common;

import lombok.Getter;

public class Constant {
    public enum TypeTextFlashCard {
        CODE, TEXT
    }

    @Getter
    public enum BoxLevel {
        EVERYDAY(0),
        EVERY_THREE_DAYS(1),
        EVERY_WEEK(2),
        EVERY_TWO_WEEKS(3),
        EVERY_MONTH(4);

        private final int value;
        BoxLevel(int value) {
            this.value = value;
        }
    }
}
