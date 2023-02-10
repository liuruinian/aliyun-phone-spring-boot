package io.github.liuruinian.phone.exception;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class AxgGroupException extends RuntimeException {

    public AxgGroupException() {
    }

    public AxgGroupException(String message) {
        super(message);
    }

    public AxgGroupException(String message, Throwable cause) {
        super(message, cause);
    }

    public AxgGroupException(Throwable cause) {
        super(cause);
    }
}
