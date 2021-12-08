package ua.my.oblikchasu.util;

import org.junit.Test;
import ua.my.oblikchasu.util.Encryptor;
import static org.junit.Assert.*;
import java.security.NoSuchAlgorithmException;

public class TestEncryptor {

    @Test
    public void shouldEncryptPassword () throws NoSuchAlgorithmException {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        String encryptedPassword = Encryptor.encryptPassword(testLogin, testPassword);
        assertNotEquals(testPassword, encryptedPassword);
    }

    @Test
    public void shouldReturnTrueIfPasswordAndLoginCorrect () throws NoSuchAlgorithmException {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        String encryptedPassword = Encryptor.encryptPassword(testLogin, testPassword);
        assertTrue(Encryptor.validatePassword(testLogin, testPassword, encryptedPassword));
    }

    @Test
    public void shouldReturnFalseIfPasswordIncorrect () throws NoSuchAlgorithmException {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        String encryptedPassword = Encryptor.encryptPassword(testLogin, testPassword);
        assertFalse(Encryptor.validatePassword(testLogin, "wrongPassword", encryptedPassword));
    }

    @Test
    public void shouldReturnFalseIfLoginIncorrect () throws NoSuchAlgorithmException {
        String testLogin = "testLogin";
        String testPassword = "testPassword";
        String encryptedPassword = Encryptor.encryptPassword(testLogin, testPassword);
        assertFalse(Encryptor.validatePassword("wrongLogin", testLogin, encryptedPassword));
    }

}
