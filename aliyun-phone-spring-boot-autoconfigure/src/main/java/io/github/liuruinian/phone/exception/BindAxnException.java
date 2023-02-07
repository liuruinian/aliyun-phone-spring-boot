package io.github.liuruinian.phone.exception;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class BindAxnException extends RuntimeException {

    public BindAxnException() {
    }

    public BindAxnException(String message) {
        super(message);
    }

    public BindAxnException(String message, Throwable cause) {
        super(message, cause);
    }

    public BindAxnException(Throwable cause) {
        super(cause);
    }
}
