package com.devang.auth;

import java.security.SecureRandom;

public class Tokens {
    private static final SecureRandom RNG = new SecureRandom();
    private static final char[] ALPHABET =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static String newToken(int length) {
        char[] out = new char[length];
        for (int i = 0; i < length; i++) {
            out[i] = ALPHABET[RNG.nextInt(ALPHABET.length)];
        }
        return new String(out);
    }
}