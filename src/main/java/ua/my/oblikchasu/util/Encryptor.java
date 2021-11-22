package ua.my.oblikchasu.util;

import java.nio.charset.StandardCharsets;
import java.security.*;

public class Encryptor {

    public static String encryptPassword (String login, String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((login+password).getBytes(StandardCharsets.UTF_8));
        byte[] hash = md.digest();
        StringBuilder sb = new StringBuilder();
        for(byte b: hash) {
            sb.append(Integer.toHexString(b&0xFF));
        }
        return sb.toString();
    }

    public static boolean validatePassword (String login, String password, String encryptedPassword) throws NoSuchAlgorithmException {
        String input = encryptPassword(login, password);
        return encryptedPassword.equals(input);
    }

}
