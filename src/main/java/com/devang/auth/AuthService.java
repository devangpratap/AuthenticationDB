package com.devang.auth;

import org.mindrot.jbcrypt.BCrypt;

public class AuthService {

    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}