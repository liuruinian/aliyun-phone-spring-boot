package io.github.liuruinian.phone.exception;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class SubscriptionOperationException extends RuntimeException {

    public SubscriptionOperationException() {
    }

    public SubscriptionOperationException(String message) {
        super(message);
    }

    public SubscriptionOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubscriptionOperationException(Throwable cause) {
        super(cause);
    }
}
