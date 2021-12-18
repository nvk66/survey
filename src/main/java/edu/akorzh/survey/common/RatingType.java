package edu.akorzh.survey.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RatingType {
    EXAMINATION("Экзамен"),
    CREDIT("Зачёт");

    private final String value;

}
