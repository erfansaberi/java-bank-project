package com.bank.cli;

import java.io.Console;
import java.io.File;
import java.util.Scanner;

import com.bank.models.Core;
import com.bank.models.Customer;
import com.bank.models.Admin;
import com.bank.models.Employee;
import com.bank.validator.Validator;
import com.bank.views.CustomerViews;

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

                case "register":
                    customerRegister();
                    break;

                case "adminlogin":
                    adminLogin();
                    break;

                case "employeelogin":
                    employeeLogin();
                    break;

                case "exit":
                    exit();
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
     * Save data and exit program
     */
    public static void exit() {
        System.out.println("[~] Exiting...");
        Core.shutdown();
        System.out.println("[~] Bye!");
        System.exit(0);
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
     * If credentials are correct, call CustomerCLI.launch() and pass customer
     * object,
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
            CustomerCLI.launch(Customer.getByPhoneNumber(phoneNumber), sc); // Goto customer CLI.
        } else {
            System.out.println("[!] Login failed!");
        }
    }

    /**
     * Employee login.
     * If credentials are correct, call EmployeeCLI.launch() and pass employee
     * object,
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
            EmployeeCLI.launch(Employee.getByEmail(email), sc); // Goto employee CLI.
        } else {
            System.out.println("[!] Login failed!");
        }
    }

    /**
     * Admin login.
     * If credentials are correct, call AdminCLI.launch() and pass admin object,
     * otherwise, print error message.
     */
    public static void adminLogin() {
        System.out.println("[~] Admin login:");
        System.out.print("  [>] Username: ");
        String username = sc.nextLine();
        System.out.print("  [>] Password: ");
        String password = getPassword();
        if (Admin.authenticate(username, password)) {
            System.out.println("[+] Login successful!");
            AdminCLI.launch(new Admin(), sc); // Goto admin CLI.
        } else {
            System.out.println("[!] Login failed!");
        }
    }

    /**
     * Customer register.
     * Takes arguments from user and validates them, then creates a new customer
     * by calling CustomerViews.customerRegister() and passing the arguments.
     * New customer status will be set to 'PENDING' by default and needs to be
     * approved by an admin.
     * 
     * If registration is successful, print success message, otherwise, print
     * error message based on register status.
     *
     * All arguments will be validated by Validator class methods and if any
     * of them is invalid, prints the error message and asks user to enter again.
     * 
     * Arguments:
     * String firstName, String lastName, String email, String phoneNumber, String
     * password, String confirmPassword, String birthDateStr, String genderStr,
     * String nationalId
     */
    public static void customerRegister() {
        System.out.println("[~] Customer register:");
        System.out.print("  [>] First name: ");
        String firstName = sc.nextLine();
        System.out.print("  [>] Last name: ");
        String lastName = sc.nextLine();

        String email;
        while (true) {
            System.out.print("  [>] Email address: ");
            email = sc.nextLine();
            if (Validator.isValidEmail(email)) {
                break;
            } else {
                System.out.println("[!] Invalid email address!");
            }
        }

        String phoneNumber;
        while (true) {
            System.out.print("  [>] Phone number (11 digits): ");
            phoneNumber = sc.nextLine();
            if (Validator.isValidPhoneNumber(phoneNumber)) {
                break;
            } else {
                System.out.println("[!] Invalid phone number!");
            }
        }

        String password;
        String confirmPassword;
        while (true) {
            System.out.print("  [>] Password: ");
            password = getPassword();
            System.out.print("  [>] Confirm password: ");
            confirmPassword = getPassword();
            if (password.equals(confirmPassword)) {
                break;
            } else {
                System.out.println("[!] Passwords does'nt match!");
            }
            if (password.length() < 8) {
                System.out.println("[!] Password must be at least 8 characters!");
            }
        }

        String birthDateStr;
        while (true) {
            System.out.print("  [>] Birth date (YYYY-MM-DD): ");
            birthDateStr = sc.nextLine();
            if (Validator.isValidBirthDate(birthDateStr)) {
                break;
            } else {
                System.out.println("[!] Invalid birth date!");
            }
        }

        String genderStr;
        while (true) {
            System.out.print("  [>] Gender (male/female/none): ");
            genderStr = sc.nextLine().toUpperCase();
            if (genderStr.equals("MALE") || genderStr.equals("FEMALE") || genderStr.equals("NONE")) {
                break;
            } else {
                System.out.println("[!] Invalid gender!");
            }
        }

        String nationalId;
        while (true) {
            System.out.print("  [>] National ID (10 digits): ");
            nationalId = sc.nextLine();
            if (Validator.isValidNationalId(nationalId)) {
                break;
            } else {
                System.out.println("[!] Invalid National ID!");
            }
        }

        CustomerViews.RegisterStatus registerStatus = CustomerViews.customerRegister(firstName, lastName, email,
                phoneNumber, password, confirmPassword, birthDateStr, genderStr, nationalId);
        if (registerStatus == CustomerViews.RegisterStatus.SUCCESS) {
            System.out.println("[+] Registration successful!");
        } else if (registerStatus == CustomerViews.RegisterStatus.EMAIL_IN_USE) {
            System.out.println("[!] Email address already exists!");
        } else if (registerStatus == CustomerViews.RegisterStatus.PHONE_NUMBER_IN_USE) {
            System.out.println("[!] Phone number already exists!");
        } else if (registerStatus == CustomerViews.RegisterStatus.NATIONAL_ID_IN_USE) {
            System.out.println("[!] National ID already exists!");
        } else if (registerStatus == CustomerViews.RegisterStatus.TOO_YOUNG_TO_REGISTER) {
            System.out.println("[!] You are too young to register! Must be at least 18 years old.");
        } else {
            System.out.println("[!] Registration failed!");
        }
    }

}
