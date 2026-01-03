package com.devang.auth;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        System.out.print("Email: ");
        String email = sc.nextLine().trim();

        System.out.print("Password: ");
        String password = sc.nextLine();

        String passwordHash = AuthService.hashPassword(password);
        UserService.createUser(email, passwordHash);

        System.out.println("✅ User registered.");
        sc.close();
    }
}