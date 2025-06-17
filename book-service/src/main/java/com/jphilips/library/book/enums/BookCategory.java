package com.jphilips.library.book.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BookCategory {
    GENERAL_WORKS("000"),
    PHILOSOPHY("100"),
    RELIGION("200"),
    SOCIAL_SCIENCES("300"),
    LANGUAGE("400"),
    SCIENCE("500"),
    MATHEMATICS("510"),
    TECHNOLOGY("600"),
    ARTS("700"),
    LITERATURE("800"),
    HISTORY_GEOGRAPHY("900");

    private final String code;

    public static BookCategory getOrDefault(String input) {
        if (input == null) return GENERAL_WORKS;

        try {
            return BookCategory.valueOf(input.trim().toUpperCase());
        } catch (IllegalArgumentException ex) {
            return GENERAL_WORKS;
        }
    }
}
