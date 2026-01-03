package com.devang.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserService {

    public static void createUser(String email, String passwordHash) throws Exception {
        String sql = "INSERT INTO users (email, Password_Hash) VALUES (?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, passwordHash);
            ps.executeUpdate();
        }
    }
}
