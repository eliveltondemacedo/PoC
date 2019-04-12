package com.portal.auto.exception;

import static java.lang.String.format;

@SuppressWarnings("serial")
public class FailedToLoginException extends RuntimeException {
    public FailedToLoginException(String username) {
        super(format("Falha no login com o username %s", username));
    }
}
