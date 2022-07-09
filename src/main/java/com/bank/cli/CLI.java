package com.bank.cli;

import java.io.Console;
import java.io.File;
import java.util.Scanner;

import com.bank.models.Core;
import com.bank.models.Customer;
import com.bank.models.Admin;
import com.bank.models.Employee;

public class CLI {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Core.startup();
        printSplashText();

        while (true) {
            System.out.print("\n[Guest]> ");
            String input = sc.nextLine();
            System.out.println();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "login":
                    customerLogin();
                    break;

                case "adminlogin" : 
                    // TODO: Add admin login
                    break;

                case "employeelogin" :
                     employeeLogin() ;
                     break ;

                case "exit":
                    System.out.println("[~] Bye!");
                    System.exit(0);
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
     * Print splash ascii art. (Banking System)
     */
    public static void printSplashText() {
        try {
            File file = new File("src/main/resources/com/bank/cli/splashtext.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("*** Banking System ***");
            System.out.println(e);
        }
        System.out.println("[+] Welcome to Bank CLI!");
        System.out.println("[+] Enter 'help' for help.");
    }

    /**
     * Print help text.
     */
    public static void printHelp() {
        System.out.println("[?] Commands:");
        System.out.println("\t[#] Customer commands:");
        System.out.println("\t\t[C] register - Customer register");
        System.out.println("\t\t[C] login - Customer login");
        System.out.println("\t[#] Employee commands:");
        System.out.println("\t\t[C] employeelogin - Employee login");
        System.out.println("\t[#] Admin commands:");
        System.out.println("\t\t[C] adminlogin - Admin login");
        System.out.println("");
        System.out.println("\t[C] help - Print this help.");
        System.out.println("\t[C] exit - Exit CLI.");
    }

    /**
     * Console get password with mask.
     */
    public static String getPassword() {
        Console console = System.console();
        if (console == null) {
            System.out.println("[!] Error: No console.");
            System.exit(1);
        }
        char[] password = console.readPassword();
        return new String(password);
    }

    /**
     * Customer login.
     * If credentials are correct, call CustomerCLI.launch() and pass customer object,
     * otherwise, print error message.
     */
    public static void customerLogin() {
        System.out.println("[~] Customer login:");
        System.out.print("  [>] Phone number: ");
        String phoneNumber = sc.nextLine();
        System.out.print("  [>] Password: ");
        String password = getPassword();
        if (Customer.authenticate(phoneNumber, password)) {
            System.out.println("[+] Login successful!");
            CustomerCLI.launch(Customer.getByPhoneNumber(phoneNumber)); // Goto customer CLI.
        } else {
            System.out.println("[!] Login failed!");
        }
    }

    /**
     * Employee login.
     * If credentials are correct, call EmployeeCLI.launch() and pass employee object,
     * otherwise, print error message.
     */
    public static void employeeLogin() {
        System.out.println("[~] Employee login:");
        System.out.print("  [>] Email address: ");
        String email = sc.nextLine();
        System.out.print("  [>] Password: ");
        String password = getPassword();
        if (Employee.authenticate(email, password)) {
            System.out.println("[+] Login successful!");
            EmployeeCLI.launch(Employee.getByEmail( email)); // Goto employee CLI.
        } else {
            System.out.println("[!] Login failed!");
        }
    }
}
