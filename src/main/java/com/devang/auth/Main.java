package com.devang.auth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.println("1) Register");
        System.out.println("2) Login");
        System.out.println("3) Who am I");
        System.out.print("> ");
        String choice = sc.nextLine().trim();

        if (choice.equals("1")) {
            System.out.print("Email: ");
            String email = sc.nextLine().trim();

            System.out.print("Password: ");
            String password = sc.nextLine();

            String passwordHash = AuthService.hashPassword(password);
            UserService.createUser(email, passwordHash);

            System.out.println("✅ User registered.");

        } else if (choice.equals("2")) {
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

        } else if (choice.equals("3")) {
            System.out.print("Session token: ");
            String token = sc.nextLine().trim();

            String email = SessionService.getEmailFromToken(token);

            if (email == null) {
                System.out.println("❌ Invalid or expired session.");
            } else {
                System.out.println("✅ Logged in as: " + email);
            }

        } else {
            System.out.println("❌ Invalid choice.");
        }

        sc.close();
    }
}