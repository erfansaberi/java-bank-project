package com.bank.cli;

import java.util.Scanner;

import com.bank.models.Admin;

public class AdminCLI {
    static Scanner sc;
    static Admin admin;
    public static void launch(Admin admin, Scanner sc) {
        AdminCLI.sc = sc;
        AdminCLI.admin = admin;
        System.out.println("[!] Welcome to the Admin CLI.");
        System.out.println("[!] Type 'help' for a list of commands.");
        System.out.println("[!] Type 'exit' to exit.");
        while (AdminCLI.admin != null) {
            System.out.print("\n[Admin]> ");
            String input = sc.nextLine();
            System.out.println();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "exit":
                    CLI.exit();
                    break;

                case "logout":
                    logout();
                    break;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("[!] Unknown command.");
                    break;
            }
        }
    }
        
    /**
     * Print help text.
     */
    public static void printHelp() { // TODO: Add customer commands to help text
        System.out.println("[?] Admin Commands:");
        System.out.println("[C] logout: Logout.");
        System.out.println("[C] exit: Exit program.");
        System.out.println("[C] help: Print help text.");
    }

    /**
     * Logout
     * Set admin to null.
     */
    public static void logout() {
        admin = null;
        System.out.println("[+] Logout successful.");
    }
}
