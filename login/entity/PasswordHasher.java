package login.entity;

import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(String password){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashByte = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashByte);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
