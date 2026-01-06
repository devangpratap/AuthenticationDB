package com.devang.auth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        while (true) {

            SessionService.cleanupExpiredSessions();

            System.out.println("0) Exit");
            System.out.println("1) Register");
            System.out.println("2) Login");
            System.out.println("3) Who am I");
            System.out.println("4) Logout");
            System.out.print("> ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "0" -> {
                    break;
                }
                case "1" -> {
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    String passwordHash = AuthService.hashPassword(password);
                    UserService.createUser(email, passwordHash);

                    System.out.println("✅ User registered.");

                }
                case "2" -> {
                    System.out.print("Email: ");
                    String email = sc.nextLine().trim();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    UserAuthRow row = UserService.findAuthByEmail(email);

                    if (row == null) {
                        System.out.println("❌ No such user.");
                    } else if (!AuthService.checkPassword(password, row.passwordHash)) {
                        System.out.println("❌ Wrong password.");
                    } else {
                        String token = SessionService.createSession(row.id);
                        System.out.println("✅ Login successful.");
                        System.out.println("Session token:");
                        System.out.println(token);
                        System.out.println("Session valid for 7 days.");
                    }

                }
                case "3" -> {
                    System.out.print("Session token: ");
                    String token = sc.nextLine().trim();

                    String email = SessionService.getEmailFromToken(token);

                    if (email == null) {
                        System.out.println("❌ Invalid or expired session.");
                    } else {
                        System.out.println("✅ Logged in as: " + email);
                    }
                }
                case "4" -> {
                    System.out.print("Session token: ");
                    String token = sc.nextLine().trim();

                    SessionService.logout(token);
                    System.out.println("✅ Logged out (if token existed).");

                }
                default -> System.out.println("❌ Invalid choice.");
            }

            sc.close();
        }
    }
}