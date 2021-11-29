package ua.my.oblikchasu.service;

public abstract class ErrorMsg {
    private ErrorMsg() {}
    public static final String ERROR = "Error!";
    public static final String ACTIVITY_CANNOT_FIND = "Cannot find activity";
    public static final String ACTIVITY_CANNOT_ADD = "Cannot add activity";
    public static final String ACTIVITY_CANNOT_UPDATE = "Cannot update activity";
    public static final String ACTIVITY_CANNOT_DELETE = "Cannot delete activity";
    public static final String ACTIVITY_CATEGORY_CANNOT_FIND = "Cannot find activity category";
    public static final String ACTIVITY_CATEGORY_CANNOT_ADD = "Cannot add activity category";
    public static final String ACTIVITY_CATEGORY_CANNOT_UPDATE = "Cannot update activity category";
    public static final String ACTIVITY_CATEGORY_CANNOT_DELETE = "Cannot delete activity category";
    public static final String USERS_ACTIVITY_CANNOT_FIND = "Cannot find user's activity";
    public static final String USERS_ACTIVITY_CANNOT_ADD = "Cannot add user's activity";
    public static final String USERS_ACTIVITY_CANNOT_UPDATE = "Cannot update user's activity";
    public static final String USERS_ACTIVITY_CANNOT_DELETE = "Cannot delete user's activity";
    public static final String USER_CANNOT_FIND = "Cannot find user";
    public static final String USER_CANNOT_ADD = "Cannot add user";
    public static final String USER_CANNOT_UPDATE = "Cannot update user";
    public static final String USER_CANNOT_DELETE = "Cannot delete user";
}
