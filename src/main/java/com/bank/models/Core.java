package com.bank.models;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Core {
    static String passwordSalt; // salt used to hash passwords

    /**
     * Call this method when the application starts to load the data.
     */
    public static void startup() {
        Core.loadConfig(); // Load configs like salt from configs.properties
        Admin.setAdminCredentials(); // Set admin username and password from configs.properties file
        Employee.loadData();
        Customer.loadData();
        Account.loadData();
        Transaction.loadData();
        // TODO: Loan.loadData()
    }

    /**
     * Call this method when the application closes to save the data.
     */
    public static void shutdown() {
        Employee.saveData();
        Customer.saveData();
        Account.saveData();
        Transaction.saveData();
        // TODO: Loan.saveData()
    }

    /**
     * Loads the configs from configs.properties file.
     */
    public static void loadConfig() {
        try {
            FileInputStream configsFile = new FileInputStream("src/main/java/com/bank/configs/configs.properties");
            Properties configs = new Properties();
            configs.load(configsFile);
            passwordSalt = configs.getProperty("PASSWORD_SALT");
        } catch (IOException e) {
            System.err.println("[!] Failed to load configs");
            e.printStackTrace();
        }
    }

    /**
     * Get password hash.
     * Password is hashed with SHA-256.
     * Password salt is stored in configs.properties file.
     * 
     * @param password Password to be hashed.
     * @return Password hash.
     */
    public static String hashPassword(String password) {
        String hash = ""; // password hash
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("SHA-256");
            md.update(passwordSalt.getBytes());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hash = sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * DEBUG: Prints the password hash.
     */
    public static void debugPrintPasswordHash(String password) {
        System.out.println("[!] Password hash: " + Core.hashPassword(password));
    }

    /**
     * DEBUG: Prints all employees.
     */
    public static void debugPrintEmployees() {
        System.out.println("[!] Employees:");
        for (Employee employee : Employee.getAllEmployees()) {
            System.out.println("\t[!] " + employee.toString());
            System.out.println(employee.getPassword());
        }
    }

    /**
     * DEBUG: Prints all customers.
     */
    public static void debugPrintCustomers() {
        System.out.println("[!] Customers:");
        for (Customer customer : Customer.getAllCustomers()) {
            System.out.println("\t[!] " + customer.toString());
            System.out.println(customer.getPassword());
        }
    }

}
