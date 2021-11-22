package ua.my.oblikchasu.db.exception;

public class DBException extends Exception {
    private Throwable source;
    private String message;

    public DBException (String message, Throwable cause) {
        this.source = cause;
        this.message = message;
    }
}
