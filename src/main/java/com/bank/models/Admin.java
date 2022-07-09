package com.bank.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Admin {
    private static String username;
    private static String password;

    public static void setAdminCredentials() {
        try {
            FileInputStream configsFile = new FileInputStream("src/main/java/com/bank/configs/configs.properties");
            Properties configs = new Properties();
            configs.load(configsFile);
            username = configs.getProperty("ADMIN_USERNAME");
            password = configs.getProperty("ADMIN_PASSWORD");
        } catch (IOException e) {
            System.err.println("[!] Failed to load admin credentials");
            e.printStackTrace();
        }
    }

    public static boolean authenticate(String username, String rawPassword) {
        return Admin.username.equals(username) && Admin.password.equals(Core.hashPassword(rawPassword));
    }
}
