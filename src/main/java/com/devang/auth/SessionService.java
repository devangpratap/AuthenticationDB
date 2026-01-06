package com.devang.auth;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class SessionService {

    public static String createSession(int userId) throws Exception {
        String token = Tokens.newToken(48);
        Timestamp expiresAt = Timestamp.from(Instant.now().plus(7, ChronoUnit.DAYS));

        String sql = "INSERT INTO sessions (user_id, token, expires_at) VALUES (?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setTimestamp(3, expiresAt);
            ps.executeUpdate();
        }

        return token;
    }

    public static String getEmailFromToken(String token) throws Exception {
        String sql = """
        SELECT u.email
        FROM sessions s
        JOIN users u ON s.user_id = u.Id
        WHERE s.token = ? AND s.expires_at > NOW()
        """;

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);

            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) return null;
                return rs.getString("email");
            }
        }
    }
    public static void logout(String token) throws Exception {
        String sql = "DELETE FROM sessions WHERE token = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, token);
            ps.executeUpdate();
        }
    }
    public static void cleanupExpiredSessions() throws Exception {
        String sql = "DELETE FROM sessions WHERE expires_at < NOW()";

        try (var conn = Database.getConnection();
             var ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        }
    }
}