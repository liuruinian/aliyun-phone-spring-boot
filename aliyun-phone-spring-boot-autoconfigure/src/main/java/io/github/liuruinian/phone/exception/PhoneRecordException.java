package io.github.liuruinian.phone.exception;

/**
 * @author liuruinian
 * @since 2023-02-07
 */
public class PhoneRecordException extends RuntimeException {

    public PhoneRecordException() {
    }

    public PhoneRecordException(String message) {
        super(message);
    }

    public PhoneRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhoneRecordException(Throwable cause) {
        super(cause);
    }
}
