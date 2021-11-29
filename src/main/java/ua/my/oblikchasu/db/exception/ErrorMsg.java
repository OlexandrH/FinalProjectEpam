package ua.my.oblikchasu.db.exception;

public abstract class ErrorMsg {
    private ErrorMsg () {}
    public static final String ERROR = "Error ";
    public static final String DB_CONN_ERROR = "Unable to connect database";
    public static final String DB_CONN_POOL_ERROR = "Unable to create connection pool";
    public static final String DB_CONN_CLOSE_FAIL = "Unable to close connection";
    public static final String DB_STATEMENT_CLOSE_FAIL = "Unable to close statement";
    public static final String DB_RESULT_SET_CLOSE_FAIL = "Unable to close result set";
    public static final String UNSUPPORTED_OPERATION = "Operation not supported";
    public static final String DB_PASSWORD_ENCRYPTION_FAIL = "Error encrypting password";
}
