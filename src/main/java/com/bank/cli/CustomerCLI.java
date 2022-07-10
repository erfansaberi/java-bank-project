package com.bank.cli;

import java.util.Scanner;

import com.bank.models.Customer;

public class CustomerCLI {
    static Scanner sc = new Scanner(System.in);
    static Customer customer;

    public static void launch(Customer customer) {
        CustomerCLI.customer = customer;
        System.out.println("[+] Welcome " + customer.getFirstName() +"!");
        System.out.println("[+] Enter 'help' for help.");
        while (CustomerCLI.customer != null) {
            System.out.print("\n["+ customer.getFullName() +" (Customer)]> ");
            String input = sc.nextLine();
            System.out.println();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "exit":
                    System.out.println("[~] Bye!");
                    System.exit(0);
                    break;

                case "balance":
                break;


                case "history":
                break;


                case "transfer":
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
        System.out.println("[?] Customer Commands:");
        System.out.println("[C] balance: Print balance.");
        System.out.println("[C] history: Print history.");
        System.out.println("[C] transfer: Transfer money.");
        System.out.println("[C] logout: Logout.");
        System.out.println("[C] exit: Exit program.");
        System.out.println("[C] help: Print help text.");
    }

    /**
     * Logout
     * Set customer to null.
     */
    public static void logout() {
        customer = null;
        System.out.println("[+] Logout successful.");
    }
}
