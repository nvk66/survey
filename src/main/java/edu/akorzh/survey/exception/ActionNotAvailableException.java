package edu.akorzh.survey.exception;

public class ActionNotAvailableException extends RuntimeException {
    public ActionNotAvailableException() {
    }

    public ActionNotAvailableException(String message) {
        super(message);
    }

    public ActionNotAvailableException(Throwable cause) {
        super(cause);
    }
}
