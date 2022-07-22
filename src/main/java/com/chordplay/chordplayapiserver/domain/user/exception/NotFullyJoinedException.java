package com.chordplay.chordplayapiserver.domain.user.exception;

public class NotFullyJoinedException extends RuntimeException{
    public NotFullyJoinedException() {
        super();
    }

    public NotFullyJoinedException(String message) {
        super(message);
    }

    public NotFullyJoinedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFullyJoinedException(Throwable cause) {
        super(cause);
    }

    protected NotFullyJoinedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
