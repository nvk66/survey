package edu.akorzh.survey.api.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Error {
    private int errorCode;
    private String message;
    private String detail;
}
