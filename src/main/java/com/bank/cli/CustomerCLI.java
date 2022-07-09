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
        while (customer != null) {
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
        System.out.println("[+] balance: Print balance.");
        System.out.println("[+] history: Print history.");
        System.out.println("[+] transfer: Transfer money.");
        System.out.println("[+] logout: Logout.");
        System.out.println("[+] exit: Exit program.");
        System.out.println("[+] help: Print help text.");
    }
}
