package io.github.liuruinian.phone.exception;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class StateQueryException extends RuntimeException {

    public StateQueryException() {
    }

    public StateQueryException(String message) {
        super(message);
    }

    public StateQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public StateQueryException(Throwable cause) {
        super(cause);
    }
}
