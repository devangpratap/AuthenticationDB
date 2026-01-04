package com.devang.auth;

public class UserAuthRow {
    public final int id;
    public final String passwordHash;

    public UserAuthRow(int id, String passwordHash) {
        this.id = id;
        this.passwordHash = passwordHash;
    }
}