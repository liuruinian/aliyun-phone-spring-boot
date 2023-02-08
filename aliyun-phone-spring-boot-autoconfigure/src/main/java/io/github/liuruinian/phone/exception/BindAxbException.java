package io.github.liuruinian.phone.exception;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class BindAxbException extends RuntimeException {

    public BindAxbException() {
    }

    public BindAxbException(String message) {
        super(message);
    }

    public BindAxbException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindAxbException(Throwable cause) {
        super(cause);
    }
}
