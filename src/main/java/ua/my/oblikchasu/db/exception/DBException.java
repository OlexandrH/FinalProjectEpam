package ua.my.oblikchasu.db.exception;

public class DBException extends Exception {
    private final Throwable cause;
    private final String message;

    public DBException (String message, Throwable cause) {
        this.cause = cause;
        this.message = message;
    }
    public DBException(String message, Exception cause, String message1) {
        super(message);
        this.cause = cause;
        this.message = message1;
    }

    public DBException(String message, Throwable cause, Exception cause1, String message1) {
        super(message, cause);
        this.cause = cause1;
        this.message = message1;
    }

    public DBException(Throwable cause, Exception cause1, String message) {
        super(cause);
        this.cause = cause1;
        this.message = message;
    }
}
