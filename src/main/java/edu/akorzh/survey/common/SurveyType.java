package edu.akorzh.survey.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SurveyType {
    SURVEY("Опрос"),
    QUIZ("Викторина"),
    APPLICATION_FORM("Анкета");

    private final String value;
}
