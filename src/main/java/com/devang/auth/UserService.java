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

    public static UserAuthRow findAuthByEmail(String email) throws Exception {
        String sql = "SELECT Id, Password_Hash FROM users WHERE email = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return new UserAuthRow(rs.getInt("Id"), rs.getString("Password_Hash"));
            }
        }
    }
}
