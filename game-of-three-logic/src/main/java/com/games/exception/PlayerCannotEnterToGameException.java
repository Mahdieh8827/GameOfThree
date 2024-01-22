package com.games.exception;

import static java.lang.String.format;

import java.io.Serial;

public class PlayerCannotEnterToGameException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6945847183138381340L;

    private static final String REGISTRATION_ERROR_MESSAGE = "A new player cannot enter to game because %s.";

    public PlayerCannotEnterToGameException(String reason) {
        super(format(REGISTRATION_ERROR_MESSAGE, reason));
    }

}
