package ua.my.oblikchasu.util;

import java.io.PrintWriter;

public class Validator {

    public static final String LOGIN_REGEX = "[_0-9A-Za-z]{3,30}";
    public static final String USERNAME_REGEX = "[_.0-9A-Za-zА-Яа-яҐґІіЇїЄє\\s]{2,30}";
    public static final String PASSWORD_REGEX = "[_0-9A-Za-z]{3,30}";
    public static final String ACTIVITY_NAME_REGEX = "[.,()0-9A-Za-zА-Яа-яҐґІіЇїЄє\\s]{2,30}";
    public static final String CATEGORY_NAME_REGEX = "[.,()0-9A-Za-zА-Яа-яҐґІіЇїЄє\\s]{3,30}";

//    public static final String LOGIN_REGEX = "[A-Za-z]{3,30}";
//    public static final String USERNAME_REGEX = "[A-Za-zА-Яа-яҐґІіЇіЄє\\s]{2,30}";
//    public static final String PASSWORD_REGEX = "[A-Za-z]{3,30}";
//    public static final String ACTIVITY_NAME_REGEX = "[.0-9A-Za-zА-Яа-яҐґІіЇіЄє\\s]{3,30}";
//    public static final String CATEGORY_NAME_REGEX = "[.0-9A-Za-zА-Яа-яҐґІіЇіЄє\\s]{3,30}";

    private Validator () {}

    public static void alert (PrintWriter out, String message, String redirect) {
        out.println("<script type=\"text/javascript\">");
        out.println("alert('" + message + "');");
        out.println("location='" + redirect + "';");
        out.println("</script>");
    }

    public static boolean validateLogin (String login) {
        if(login == null) return false;
        return login.matches(LOGIN_REGEX);
    }

    public static boolean validatePassword (String password) {
        if(password == null) return false;
        return password.matches(PASSWORD_REGEX);
    }

    public static boolean validateUserName (String name) {
        if(name == null) return false;
        return name.matches(USERNAME_REGEX);
    }

    public static boolean validateActivityName (String categoryName) {
        if(categoryName == null) return false;
        return categoryName.matches(ACTIVITY_NAME_REGEX);
    }

    public static boolean validateCategoryName (String activityName) {
        if(activityName == null) return false;
        return activityName.matches(CATEGORY_NAME_REGEX);
    }

}
