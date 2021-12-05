package ua.my.oblikchasu.service.exception;

public class ServiceException extends Exception {
    private final Exception cause;
    private final String message;

    public ServiceException(String message, Exception cause) {
        this.cause = cause;
        this.message = message;
    }

    public ServiceException(String message, Exception cause, String message1) {
        super(message);
        this.cause = cause;
        this.message = message1;
    }

    public ServiceException(String message, Throwable cause, Exception cause1, String message1) {
        super(message, cause);
        this.cause = cause1;
        this.message = message1;
    }

    public ServiceException(Throwable cause, Exception cause1, String message) {
        super(cause);
        this.cause = cause1;
        this.message = message;
    }
}
