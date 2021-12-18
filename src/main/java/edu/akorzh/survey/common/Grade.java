package edu.akorzh.survey.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Grade {
    PH_D("Доктор наук"),
    PHD("Кандидат наук"),
    ASSISTANT_PROFESSOR("Доцент"),
    ENGINEER("Инженер"),
    ASSISTANT("Ассистент");

    private final String value;
}
