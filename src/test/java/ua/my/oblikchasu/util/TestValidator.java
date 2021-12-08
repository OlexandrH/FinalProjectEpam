package ua.my.oblikchasu.util;

import org.junit.Test;
import ua.my.oblikchasu.util.Validator;

import static org.junit.Assert.*;

public class TestValidator {

    @Test
    public void shouldReturnTrueIfLoginValid () {
        String login = "_AaBb0123456789";
        assertTrue(Validator.validateLogin(login));
    }

    @Test
    public void shouldReturnFalseIfLoginContainsIllegalCharacters () {
        String login = "!$%^&*()";
        assertFalse(Validator.validateLogin(login));
    }

    @Test
    public void shouldReturnFalseIfLoginHasInvalidLength () {
        String login = "Ab";
        assertFalse(Validator.validateLogin(login));
        login = "abcdefghijklmnopqrstuvwxyz0123456789";
        assertFalse(Validator.validateLogin(login));
    }

    @Test
    public void shouldReturnTrueIfPasswordValid () {
        String password = "_AaBb0123456789";
        assertTrue(Validator.validatePassword(password));
    }

    @Test
    public void shouldReturnFalseIfPasswordContainsIllegalCharacters () {
        String password = "!$%^&*()";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void shouldReturnFalseIfPasswordHasInvalidLength () {
        String password = "Ab";
        assertFalse(Validator.validatePassword(password));
        password = "abcdefghijklmnopqrstuvwxyz0123456789";
        assertFalse(Validator.validatePassword(password));
    }

    @Test
    public void shouldReturnTrueIfUsernameValid () {
        String username = "_AaBbЄєҐґБб. 0123456789";
        assertTrue(Validator.validateUserName(username));
    }

    @Test
    public void shouldReturnFalseIfUsernameContainsIllegalCharacters () {
        String userName = "!$%^&*()";
        assertFalse(Validator.validateUserName(userName));
    }

    @Test
    public void shouldReturnFalseIfUsernameHasInvalidLength () {
        String username = "A";
        assertFalse(Validator.validateUserName(username));
        username = "abcdefghijklmnopqrstuvwxyz0123456789";
        assertFalse(Validator.validateUserName(username));
    }

    @Test
    public void shouldReturnTrueIfActivityNameValid () {
        String activityName = "AaBbЄєҐґБб., 0123456789";
        assertTrue(Validator.validateActivityName(activityName));
    }

    @Test
    public void shouldReturnFalseIfActivityNameContainsIllegalCharacters () {
        String activityName = "_!$%^&*";
        assertFalse(Validator.validateActivityName(activityName));
    }

    @Test
    public void shouldReturnFalseIfActivityNameHasInvalidLength () {
        String activityName = "A";
        activityName = "abcdefghijklmnopqrstuvwxyz0123456789";
        assertFalse(Validator.validateActivityName(activityName));
    }

    @Test
    public void shouldReturnTrueIfCategoryNameValid () {
        String categoryName = "AaBbЄєҐґБб., 0123456789";
        assertTrue(Validator.validateCategoryName(categoryName));
    }

    @Test
    public void shouldReturnFalseIfCategoryNameContainsIllegalCharacters () {
        String categoryName = "_!$%^&*";
        assertFalse(Validator.validateCategoryName(categoryName));
    }

    @Test
    public void shouldReturnFalseIfCategoryNameHasInvalidLength () {
        String categoryName = "Ab";
        assertFalse(Validator.validateCategoryName(categoryName));
        categoryName = "abcdefghijklmnopqrstuvwxyz0123456789";
        assertFalse(Validator.validateCategoryName(categoryName));
    }
}
