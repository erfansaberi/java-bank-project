package com.bank.cli;

import java.util.Scanner;

import com.bank.models.Employee;

public class EmployeeCLI {
    static Scanner sc = new Scanner(System.in);
    static Employee employee;

    public static void launch(Employee employee) {
        EmployeeCLI.employee = employee;
        System.out.println("[+] Welcome " + employee.getFirstName() +"!");
        System.out.println("[+] Enter 'help' for help.");
        while (employee != null) {
            System.out.print("\n["+ employee.getFullName() +" (Employee)]> ");
            String input = sc.nextLine();
            System.out.println();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "exit":
                    System.out.println("[~] Bye!");
                    System.exit(0);
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
    public static void printHelp() { // TODO: Add Employee commands to help text
        System.out.println("[?] Employee Commands:");
        System.out.println("[+] ");
        System.out.println("[+] ");
        System.out.println("[+] ");
        System.out.println("[+] logout: Logout.");
        System.out.println("[+] exit: Exit program ");
        System.out.println("[+] help: Print help text.");
    }
}
