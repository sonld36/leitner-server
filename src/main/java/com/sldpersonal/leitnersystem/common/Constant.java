package com.sldpersonal.leitnersystem.common;

public class Constant {
    public enum TypeTextFlashCard {
        CODE, TEXT
    }

    public enum BoxLevel {
        EVERYDAY(1),
        EVERY_THREE_DAYS(2),
        EVERY_WEEK(3),
        EVERY_TWO_WEEKS(4),
        EVERY_MONTH(5);

        private final int value;
        BoxLevel(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }
}
