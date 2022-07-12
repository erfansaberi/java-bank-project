package com.bank.cli;

import java.util.ArrayList;
import java.util.Scanner;

import com.bank.models.Account;
import com.bank.models.Admin;
import com.bank.models.Customer;
import com.bank.models.Employee;
import com.bank.models.Loan;
import com.bank.models.Transaction;
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
            try {
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
                                deleteEmployee(Long.parseLong(inputSplit[2]));
                                break;
                            case "edit":
                                editEmployee(Long.parseLong(inputSplit[2]));
                                break;
                            case "search":
                                searchEmployees();
                                break;
                        }
                        break;

                    case "customer":
                        switch (inputSplit[1]) {
                            case "list":
                                listCustomers();
                                break;
                            case "show":
                                showCustomer(Long.parseLong(inputSplit[2]));
                                break;
                            case "addtosafebox":
                                addMoneyToCustomerSafeBox(Long.parseLong(inputSplit[2]),
                                        Double.parseDouble(inputSplit[3]));
                                break;
                            case "add":
                                createCustomer();
                                break;
                            case "delete":
                                deleteCustomer(Long.parseLong(inputSplit[2]));
                                break;
                            case "edit":
                                editCustomer(Long.parseLong(inputSplit[2]));
                                break;
                            case "search":
                                searchCustomers();
                                break;
                            case "pendinglist":
                                listPendingCustomers();
                                break;
                            case "approve":
                                approveCustomer(Long.parseLong(inputSplit[2]));
                                break;
                            case "reject":
                                rejectCustomer(Long.parseLong(inputSplit[2]));
                                break;
                        }
                        break;
                    case "account":
                        switch (inputSplit[1]) {
                            case "list":
                                listAccounts();
                                break;
                            case "show":
                                showAccount(Long.parseLong(inputSplit[2]));
                                break;
                            case "add":
                                createAccount();
                                break;
                            case "delete":
                                deleteAccount(Long.parseLong(inputSplit[2]));
                                break;
                            case "edit":
                                editAccount(Long.parseLong(inputSplit[2]));
                                break;
                            case "pendinglist":
                                listPendingAccounts();
                                break;
                            case "approve":
                                approveAccount(Long.parseLong(inputSplit[2]));
                                break;
                            case "reject":
                                rejectAccount(Long.parseLong(inputSplit[2]));
                                break;
                        }
                        break;
                    case "loan":
                        switch (inputSplit[1]) {
                            case "list":
                                listLoans();
                                break;
                            case "show":
                                showLoan(Long.parseLong(inputSplit[2]));
                                break;
                            case "pendinglist":
                                listPendingLoans();
                                break;
                            case "approve":
                                approveLoan(Long.parseLong(inputSplit[2]));
                                break;
                            case "reject":
                                rejectLoan(Long.parseLong(inputSplit[2]));
                                break;
                        }
                        break;
                    case "transaction":
                        switch (inputSplit[1]) {
                            case "list":
                                listTransactions();
                                break;
                            case "show":
                                showTransaction(Long.parseLong(inputSplit[2]));
                                break;
                            case "search":
                                searchTransactions();
                                break;
                        }
                        break;
                    case "transfer":
                        transferMoney(Long.parseLong(inputSplit[1]), Long.parseLong(inputSplit[2]),
                                Double.parseDouble(inputSplit[3]));
                        break;
                    case "stats":
                        printStats();
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
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("[!] Missing arguments.");
            } catch (Exception e) {
                System.out.println("[!] Error");
                System.out.println(e);
            }
        }
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
        System.out.println("    [C] customer reject &id : Reject a customer registration.");
        System.out.println("    [C] customer list : List all customers.");
        System.out.println("    [C] customer add : Add a customer.");
        System.out.println("    [C] customer show $id : Show a customer.");
        System.out.println(
                "    [C] customer addtosafebox $id $amount : Add money to a customer safebox (Can be negative).");
        System.out.println("    [C] customer edit $id : Edit a customer.");
        System.out.println("    [C] customer delete $id : Delete a customer.");
        System.out.println("    [C] customer search : Find a customer.");
        System.out.println("  [?] Account Commands:");
        System.out.println("    [C] account pendinglist : List all pending accounts.");
        System.out.println("    [C] account approve &id : Approve an account registration.");
        System.out.println("    [C] account reject &id : Reject an account registration.");
        System.out.println("    [C] account list : List all accounts.");
        System.out.println("    [C] account add : Add an account.");
        System.out.println("    [C] account show $id : Show an account.");
        System.out.println("    [C] account edit $id : Edit an account.");
        System.out.println("    [C] account delete $id : Delete an account.");
        System.out.println("  [?] Loan Commands:");
        System.out.println("    [C] loan pendinglist : List all pending loans.");
        System.out.println("    [C] loan approve &id : Approve a loan request.");
        System.out.println("    [C] loan reject &id : Reject a loan request.");
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

    // ----------------------------------------------------------------------------
    // ----------------------------- Employee Commands ----------------------------
    // ----------------------------------------------------------------------------

    private static void listEmployees() {
        ArrayList<Employee> employees = Employee.getAllEmployees();
        if (employees.size() == 0) {
            System.out.println("[!] No employees found.");
        } else {
            System.out.println("[+] Employees:");
            for (Employee employee : employees) {
                System.out.println("[~] ID: " + employee.getId() + " - Full Name: " + employee.getFullName()
                        + " - Email: " + employee.getEmail() + " - Salary: " + employee.getSalary());
            }
        }
    }

    private static void searchEmployees() {
        // TODO: Implement searchEmployees
        System.out.println("[!] Not implemented yet.");
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

    private static void editEmployee(long parseLong) {
        // TODO: Implement editEmployee
        System.out.println("[!] Not implemented yet.");
    }

    /**
     * Show employee detailes and ask admin again
     * to make sure he wants to delete the employee.
     * 
     * @param employeeId
     */
    private static void deleteEmployee(long employeeId) {
        Employee employee = Employee.getById(employeeId);
        if (employee == null) {
            System.out.println("[!] Employee not found.");
            return;
        }
        showEmployee(employeeId);
        System.out.print("[?] Are you sure you want to delete this employee? (y/n)> ");
        String input = sc.nextLine();
        if (input.equals("y")) {
            employee.delete();
            System.out.println("[!] Employee deleted.");
        } else {
            System.out.println("[!] Employee not deleted.");
        }
    }

    // ----------------------------------------------------------------------------
    // ---------------------------- Customer Commands -----------------------------
    // ----------------------------------------------------------------------------

    private static void listPendingCustomers() {
        ArrayList<Customer> customers = Customer.getAllPendingCustomers();
        if (customers.isEmpty()) {
            System.out.println("[!] No pending customers.");
        } else {
            System.out.println("[!] Pending customers:");
            for (Customer customer : customers) {
                System.out.println("  [~] ID: " + customer.getId() + " - Full Name: " + customer.getFullName());
            }
        }
    }

    private static void listCustomers() {
        ArrayList<Customer> customers = Customer.getAllCustomers();
        if (customers.size() == 0) {
            System.out.println("[!] No customers found.");
        } else {
            System.out.println("[+] Customers:");
            for (Customer customer : customers) {
                System.out.println("[~] ID: " + customer.getId() + " - Full Name: " + customer.getFullName());
            }
        }
    }

    private static void searchCustomers() {
        // TODO: Implement searchCustomers
        System.out.println("[!] Not implemented yet.");
    }

    private static void approveCustomer(long customerId) {
        Customer customer = Customer.getById(customerId);
        if (customer == null) {
            System.out.println("[!] Customer not found.");
            return;
        }
        if (customer.getStatus() != Customer.CustomerStatus.PENDING) {
            System.out.println("[!] Customer is not pending.");
            return;
        }
        showCustomer(customerId);
        System.out.print("[?] Are you sure you want to approve this customer? (y/n)> ");
        String input = sc.nextLine();
        if (input.equals("y")) {
            customer.approve();
            System.out.println("[!] Customer approved.");
        } else {
            System.out.println("[!] Customer not approved.");
        }
    }

    private static void rejectCustomer(long customerId) {
        Customer customer = Customer.getById(customerId);
        if (customer == null) {
            System.out.println("[!] Customer not found.");
            return;
        }
        showCustomer(customerId);
        System.out.print("[?] Are you sure you want to reject this customer? (y/n)> ");
        String input = sc.nextLine();
        if (input.equals("y")) {
            System.out.print("[?] Do you want to ban this customer or permanently delete it? (ban/delete)> ");
            String input2 = sc.nextLine();
            if (input2.equals("ban")) {
                customer.ban();
                System.out.println("[!] Customer rejected.");
            } else if (input2.equals("delete")) {
                customer.delete();
                System.out.println("[!] Customer deleted.");
            } else {
                System.out.println("[!] Invalid input.");
            }
        } else {
            System.out.println("[!] Customer not approved.");
        }
    }

    private static void createCustomer() {
        // TODO: Implement createCustomer
        System.out.println("[!] Not implemented yet.");
    }

    private static void showCustomer(long customerId) {
        Customer customer = Customer.getById(customerId);
        if (customer == null) {
            System.out.println("[!] Customer not found.");
        } else {
            System.out.println("[~] Customer: " + customer.getFullName());
            System.out.println("[~] Customer ID: " + customer.getId());
            System.out.println("[~] Customer Email: " + customer.getEmail());
            System.out.println("[~] Customer Phone: " + customer.getPhoneNumber());
            System.out.println("[~] Customer Gender: " + customer.getGender());
            System.out.println("[~] Customer National ID: " + customer.getNationalId());
            System.out.println("[~] Customer Birthday: " + customer.getBirthDate());
            System.out.println("[~] Customer Status: " + customer.getStatus());
            System.out.println("[~] Customer Safebox balance: " + customer.getSafeBoxBalance());
            System.out.println("[~] Customer Accounts count: " + customer.getAllAccounts().size());
            System.out.println("[~] Customer All Accounts: ");
            for (Account account : customer.getAllAccounts()) {
                System.out.println("  [~] Account ID: " + account.getId() + " - Account Type: " + account.getType()
                        + " - Account Balance: " + account.getBalance());
            }
            // customer loans
            System.out.println("[~] Customer Loans count: " + customer.getAllLoans().size());
            System.out.println("[~] Customer All Loans: ");
            for (Loan loan : customer.getAllLoans()) {
                System.out.println("  [~] Loan ID: " + loan.getId() + " - Loan Amount: " + loan.getLoanAmount()
                        + " - Loan Status: " + loan.getStatus());
                System.out.println("      Payed: " + loan.getTotalPaid() + " - MustPay: " + loan.getMustPay()
                        + " - Account Balance: " + loan.getAccount().getBalance());
                System.out.println("      Request Date: " + loan.getRequestDate() + " - Accept Date: "
                        + loan.getAcceptDate() + " - Pay length (month): " + loan.getPayingLengthMonths());
            }
        }
    }

    // Add to safebox
    private static void addMoneyToCustomerSafeBox(long customerId, double amount) {
        Customer customer = Customer.getById(customerId);
        if (customer == null) {
            System.out.println("[!] Customer not found.");
            return;
        }
        customer.addToSafeBoxBalance(amount);
        System.out.println("[~] Added " + amount + " to customer's safebox.");
    }

    private static void editCustomer(long parseLong) {
        // TODO: Implement editCustomer
        System.out.println("[!] Not implemented yet.");
    }

    private static void deleteCustomer(long customerId) {
        Customer customer = Customer.getById(customerId);
        if (customer == null) {
            System.out.println("[!] Customer not found.");
            return;
        } else {
            showCustomer(customerId);
            System.out.print("[?] Are you sure you want to delete this customer? (y/n)> ");
            String input = sc.nextLine();
            if (input.equals("y")) {
                customer.delete();
                System.out.println("[!] Customer deleted.");
            } else {
                System.out.println("[!] Customer not deleted.");
            }
        }
    }

    // ----------------------------------------------------------------------------
    // ----------------------------- Account Commands -----------------------------
    // ----------------------------------------------------------------------------

    private static void listAccounts() {
        ArrayList<Account> accounts = Account.getAllAccounts();
        if (accounts.size() == 0) {
            System.out.println("[!] No accounts found.");
        } else {
            System.out.println("[+] Accounts:");
            for (Account account : accounts) {
                System.out.println("[~] ID: " + account.getId() + " Owner: " + account.getOwner() + " - Account Type: "
                        + account.getType()
                        + " - Account Balance: " + account.getBalance() + " - Account Status: " + account.getStatus());
            }
        }
    }

    private static void listPendingAccounts() {
        ArrayList<Account> accounts = Account.getAllPendingAccounts();
        if (accounts.size() == 0) {
            System.out.println("[!] No accounts found.");
        } else {
            System.out.println("[+] Pending Accounts:");
            for (Account account : accounts) {
                System.out.println("[~] ID: " + account.getId() + " Owner: " + account.getOwner() + " - Account Type: "
                        + account.getType()
                        + " - Account Balance: " + account.getBalance());
            }
        }
    }

    private static void approveAccount(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
            return;
        } else if (account.getStatus() != Account.AccountStatus.PENDING) {
            System.out.println("[!] Account not pending!");
        } else {
            showAccount(accountId);
            System.out.print("[?] Are you sure you want to approve this account? (y/n)> ");
            String input = sc.nextLine();
            if (input.equals("y")) {
                account.approve();
                System.out.println("[+] Account approved.");
            } else {
                System.out.println("[~] Account not approved.");
            }
        }
    }

    private static void rejectAccount(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
            return;
        } else if (account.getStatus() != Account.AccountStatus.PENDING) {
            System.out.println("[!] Account not pending!");
        } else {
            showAccount(accountId);
            System.out.print("[?] Are you sure you want to reject this account? (y/n)> ");
            String input = sc.nextLine();
            if (input.equals("y")) {
                System.out.println("[?] Do you want to ban account or delete it? (ban/delete)> ");
                String input2 = sc.nextLine();
                if (input2.equals("ban")) {
                    account.ban();
                    System.out.println("[+] Account rejected and banned.");
                } else if (input2.equals("delete")) {
                    account.delete();
                    System.out.println("[+] Account rejected and deleted.");
                } else {
                    System.out.println("[!] Invalid input.");
                }
            } else {
                System.out.println("[~] Account not rejected.");
            }
        }
    }

    private static void createAccount() {
        // TODO: Implement createAccount
        System.out.println("[!] Not implemented yet.");
    }

    private static void showAccount(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
            return;
        } else {
            System.out.println("[+] Account:");
            System.out.println("[~] ID: " + account.getId() + " Owner: " + account.getOwner() + " - Account Type: "
                    + account.getType()
                    + " - Account Balance: " + account.getBalance() + " - Account Status: " + account.getStatus());
        }
    }

    private static void editAccount(long accountId) {
        // TODO: Implement editAccount
        System.out.println("[!] Not implemented yet.");
    }

    private static void deleteAccount(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
            return;
        } else {
            showAccount(accountId);
            System.out.print("[?] Are you sure you want to delete this account? (y/n)> ");
            String input = sc.nextLine();
            if (input.equals("y")) {
                account.delete();
                System.out.println("[+] Account deleted.");
            } else {
                System.out.println("[~] Account not deleted.");
            }
        }
    }

    // -----------------------------------------------------------------------------
    // ------------------------------- Loan Commands -------------------------------
    // -----------------------------------------------------------------------------

    private static void listPendingLoans() {
        // TODO: implement listPendingLoans
    }

    private static void listLoans() {
        // TODO: implement listLoans
    }

    private static void showLoan(long parseLong) {
        // TODO: implement showLoan
    }

    private static void approveLoan(long parseLong) {
        // TODO: implement approveLoan
    }

    private static void rejectLoan(long parseLong) {
        // TODO: implement rejectLoan
    }

    // -----------------------------------------------------------------------------
    // --------------------------- Transaction Commands ----------------------------
    // -----------------------------------------------------------------------------

    private static void transferMoney(long fromAccountId, long toAccountId, double amount) {
        // TODO: Implement the logic in views instead of here
        Account fromAccount = Account.getAccountById(fromAccountId);
        Account toAccount = Account.getAccountById(toAccountId);
        if (fromAccount == null || toAccount == null) {
            System.out.println("[!] Account not found.");
            return;
        }
        if (fromAccount.getBalance() < amount) {
            System.out.println("[!] Insufficient balance.");
            return;
        }
        Transaction transaction = Transaction.transferMoney(fromAccount, toAccount, amount);
        System.out.println("[+] Money transferred, Transaction ID: " + transaction.getId());
    }

    private static void listTransactions() {
        ArrayList<Transaction> transactions = Transaction.getAllTransactions();
        if (transactions.size() == 0) {
            System.out.println("[!] No transactions found.");
            return;
        }
        System.out.println("[~] Transactions: ");
        for (Transaction transaction : transactions) {
            System.out.println("  [~] Transaction ID: " + transaction.getId() + " - From Account ID: "
                    + transaction.getFromAccount().getId() + " - To Account ID: " + transaction.getToAccount().getId()
                    + " - Amount: " + transaction.getAmount() + " - Date: " + transaction.getDate());
        }
    }

    private static void searchTransactions() {
        // TODO: Implement searchTransactions
    }

    private static void showTransaction(long transactionId) {
        Transaction transaction = Transaction.getTransactionById(transactionId);
        if (transaction == null) {
            System.out.println("[!] Transaction not found.");
            return;
        } else {
            System.out.println("[~] Transaction ID: " + transaction.getId() + " - From Account ID: "
                    + transaction.getFromAccount().getId() + " - To Account ID: " + transaction.getToAccount().getId()
                    + " - Amount: " + transaction.getAmount() + " - Date: " + transaction.getDate());
        }
    }

    // -----------------------------------------------------------------------------
    // ------------------------------- Misc Commands -------------------------------
    // -----------------------------------------------------------------------------

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
        // Pending loans
        ArrayList<Loan> pendingLoans = Loan.getPendingLoans();
        int pendingLoansCount = pendingLoans.size();
        if (pendingLoansCount > 0) {
            System.out.println("[!] There are " + pendingLoansCount + " pending loans.");
            System.out.println("[!] Type 'loan pendinglist' to see them.");
        }
    }

    private static void printStats() {
        System.out.println("[*] There are " + Employee.getAllEmployees().size() + " employees.");
        System.out.println("[*] There are " + Customer.getAllCustomers().size() + " customers.");
        System.out.println("[*] There are " + Account.getAllAccounts().size() + " accounts.");
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
