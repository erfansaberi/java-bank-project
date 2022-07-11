package com.bank.cli;

import java.util.ArrayList;
import java.util.Scanner;

import com.bank.models.Account;
import com.bank.models.Admin;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Loan;
import com.bank.validator.Validator;
import com.bank.views.EmployeeViews;

public class AdminCLI {
    static Scanner sc;
    static Admin admin;
    public static void launch(Admin admin, Scanner sc) {
        AdminCLI.sc = sc;
        AdminCLI.admin = admin;
        System.out.println("[!] Welcome to the Admin CLI.");
        printNotifications();
        System.out.println("[!] Type 'help' for a list of commands.");
        System.out.println("[!] Type 'exit' to exit.");
        while (AdminCLI.admin != null) {
            System.out.print("\n[Admin]> ");
            String input = sc.nextLine();
            System.out.println();
            String[] inputSplit = input.split(" ");
            String command = inputSplit[0];
            switch (command) {
                case "employee":
                    switch (inputSplit[1]) {
                        case "list":
                            listEmployees();
                            break;
                        case "show":
                            showEmployee(Long.parseLong(inputSplit[2]));
                            break;
                        case "add":
                            createEmployee();
                            break;
                        case "delete":
                            // deleteEmployee(Long.parseLong(inputSplit[2]));
                            break;
                    }
                    break;
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
        
    public static void createEmployee() {
        System.out.println("[~] Employee register:");
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
            password = CLI.getPassword();
            System.out.print("  [>] Confirm password: ");
            confirmPassword = CLI.getPassword();
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

        double salary;
        while (true) {
            try {
                System.out.print("  [>] Salary: ");
                salary = Double.parseDouble(sc.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid salary!");
            }
        }

        EmployeeViews.RegisterStatus registerStatus = EmployeeViews.employeeRegister(firstName, lastName, email,
                phoneNumber, password, confirmPassword, birthDateStr, genderStr, nationalId, salary);
        if (registerStatus == EmployeeViews.RegisterStatus.SUCCESS) {
            System.out.println("[+] Registration successful!");
        } else if (registerStatus == EmployeeViews.RegisterStatus.EMAIL_IN_USE) {
            System.out.println("[!] Email address already exists!");
        } else if (registerStatus == EmployeeViews.RegisterStatus.PHONE_NUMBER_IN_USE) {
            System.out.println("[!] Phone number already exists!");
        } else if (registerStatus == EmployeeViews.RegisterStatus.NATIONAL_ID_IN_USE) {
            System.out.println("[!] National ID already exists!");
        } else {
            System.out.println("[!] Registration failed!");
        }
    }


    private static void showEmployee(long employeeId) {
        Employee employee = Employee.getById(employeeId);
        if (employee == null) {
            System.out.println("[!] Employee not found.");
        } else {
            System.out.println("[!] Employee: " + employee.getFullName());
            System.out.println("[!] Employee ID: " + employee.getId());
            System.out.println("[!] Employee Email: " + employee.getEmail());
            System.out.println("[!] Employee Phone: " + employee.getPhoneNumber());
            System.out.println("[!] Employee Salary: " + employee.getSalary());
            System.out.println("[!] Employee Gender: " + employee.getGender());
            System.out.println("[!] Employee National ID: " + employee.getNationalId());
            System.out.println("[!] Employee Birthday: " + employee.getBirthDate());
            System.out.println("[!] Employee Hired Date: " + employee.getJoinDate());
            System.out.println("[!] Employee Status: " + employee.getStatus());
            System.out.println("[!] Employee Safebox balance: " + employee.getSafeBoxBalance());
        }
    }

    private static void listEmployees() {
        ArrayList<Employee> employees = Employee.getAllEmployees();
        if (employees.size() == 0) {
            System.out.println("[!] No employees found.");
        } else {
            System.out.println("[+] Employees:");
            for (Employee employee : employees) {
                System.out.println("[~] ID: "+ employee.getId() + " - Full Name: " + employee.getFullName());
            }
        }
    }

    private static void printNotifications() {
        // Pending customers
        ArrayList<Customer> pendingCustomers = Customer.getAllPendingCustomers();
        int pendingCustomersCount = pendingCustomers.size();
        if (pendingCustomersCount > 0) {
            System.out.println(" *  There are " + pendingCustomersCount + " pending customers.");
            System.out.println(" *  Type 'customer pendinglist' to see them.");
        }
        // Pending accounts
        ArrayList<Account> pendingAccounts = Account.getAllPendingAccounts();
        int pendingAccountsCount = pendingAccounts.size();
        if (pendingAccountsCount > 0) {
            System.out.println(" *  There are " + pendingAccountsCount + " pending accounts.");
            System.out.println(" *  Type 'account pendinglist' to see them.");
        }
        // Pending loans //TODO: pending loans
        // ArrayList<Loan> pendingLoans = Loan.getAllPendingLoans();
        // int pendingLoansCount = pendingLoans.size();
        // if (pendingLoansCount > 0) {
        //     System.out.println("[!] There are " + pendingLoansCount + " pending loans.");
        //     System.out.println("[!] Type 'loan pendinglist' to see them.");
        // }
    }

    private static void printStats() {
        System.out.println("[*] There are " + Employee.getAllEmployees().size() + " employees.");
        System.out.println("[*] There are " + Customer.getAllCustomers().size() + " customers.");
        System.out.println("[*] There are " + Account.getAllAccounts().size() + " accounts.");
    }

    /**
     * Print help text.
     */
    public static void printHelp() {
        System.out.println("[?] Admin Commands:");
        System.out.println("  [?] Employee Commands:");
        System.out.println("    [C] employee list : List all employees.");
        System.out.println("    [C] employee add : Add an employee.");
        System.out.println("    [C] employee show $id : Show an employee.");
        System.out.println("    [C] employee edit $id : Edit an employee.");
        System.out.println("    [C] employee delete $id : Delete an employee.");
        System.out.println("    [C] employee search : Find an employee.");
        System.out.println("  [?] Customer Commands:");
        System.out.println("    [C] customer pendinglist : List all pending customers.");
        System.out.println("    [C] customer approve &id : Approve a customer registration.");
        System.out.println("    [C] customer refuse &id : Refuse a customer registration.");
        System.out.println("    [C] customer list : List all customers.");
        System.out.println("    [C] customer add : Add a customer.");
        System.out.println("    [C] customer show $id : Show a customer.");
        System.out.println("    [C] customer edit $id : Edit a customer.");
        System.out.println("    [C] customer delete $id : Delete a customer.");
        System.out.println("    [C] customer search : Find a customer.");
        System.out.println("  [?] Account Commands:");
        System.out.println("    [C] account pendinglist : List all pending accounts.");
        System.out.println("    [C] account approve &id : Approve an account registration.");
        System.out.println("    [C] account refuse &id : Approve an account registration.");
        System.out.println("    [C] account list : List all accounts.");
        System.out.println("    [C] account add : Add an account.");
        System.out.println("    [C] account show $id : Show an account.");
        System.out.println("    [C] account edit $id : Edit an account.");
        System.out.println("    [C] account delete $id : Delete an account.");
        System.out.println("  [?] Loan Commands:");
        System.out.println("    [C] loan pendinglist : List all pending loans.");
        System.out.println("    [C] loan approve &id : Approve a loan request.");
        System.out.println("    [C] loan refuse &id : Refuse a loan request.");
        System.out.println("    [C] loan list : List all loans.");
        System.out.println("    [C] loan show $id : Show a loan.");
        System.out.println("  [?] Transaction Commands:");
        System.out.println("    [C] transfer $fromAccountId $toAccountId $amount : Transfer money between accounts.");
        System.out.println("    [C] transaction list : Show transaction history.");
        System.out.println("    [C] transaction show $id : Show transaction history.");
        System.out.println("    [C] transaction search : Show transaction history.");
        System.out.println("  [?] Misc Commands:");
        System.out.println("    [C] stats : Print system stats.");
        System.out.println("    [C] help : Show this help text.");
        System.out.println("    [C] logout : Logout.");
        System.out.println("    [C] exit : Exit program.");
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
