package edu.akorzh.survey.common;

public enum AnswerType {
    RATE_5("Оценка от 1 до 5"),
    RATE_10("Оценка от 1 до 10"),
    FREE_FORM("Ответ в свободной форме");

    private final String value;

    AnswerType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
