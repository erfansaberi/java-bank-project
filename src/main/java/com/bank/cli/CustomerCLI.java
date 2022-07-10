package com.bank.cli;

import java.util.Scanner;

import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.views.CustomerViews;

public class CustomerCLI {
    static Scanner sc;
    static Customer customer;

    public static void launch(Customer customer, Scanner sc) {
        CustomerCLI.sc = sc;
        CustomerCLI.customer = customer;
        System.out.println("[+] Welcome " + customer.getFirstName() + "!");
        System.out.println("[+] Enter 'help' for help.");
        while (CustomerCLI.customer != null) {
            try {
                System.out.print("\n[" + customer.getFullName() + " (Customer)]> ");
                String input = sc.nextLine();
                System.out.println();
                String[] inputSplit = input.split(" ");
                String command = inputSplit[0];
                switch (command) {
                    case "exit":
                        System.out.println("[~] Bye!");
                        System.exit(0);
                        break;

                    case "account":
                        switch (inputSplit[1]) {
                            case "list":
                                listAccounts();
                                break;
                            case "show":
                                showAccount(Long.parseLong(inputSplit[2]));
                                break;
                            case "create":
                                createAccount();
                                break;
                            case "delete":
                                deleteAccount(Long.parseLong(inputSplit[2]));
                                break;
                        }
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
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("[!] Missing arguments. Type 'help' for help.");
            }
        }
    }

    /**
     * Print account id and balance
     * 
     * @param accountId
     */
    private static void showAccount(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
        } else {
            System.out.println("[~] Account ID:" + account.getId());
            System.out.println("[~] Type: " + account.getType());
            System.out.println("[~] Status: " + account.getStatus());
            System.out.println("[~] Balance: " + account.getBalance());
        }
    }

    /**
     * Delete an existing account
     * Ask customer to confirm deletion
     * Account balance will be lost
     * 
     * @param accountId
     */
    private static void deleteAccount(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
        } else {
            System.out.println("[~] Account ID:" + account.getId());
            System.out.println("[~] Type: " + account.getType());
            System.out.println("[~] Status: " + account.getStatus());
            System.out.println("[~] Balance: " + account.getBalance());
            System.out.println("[!] Your account will be deleted and its balance will be lost!");
            System.out.println("[~] Are you sure you want to delete this account? (y/n)");
            String input = sc.nextLine();
            if (input.equals("y")) {
                account.delete();
                System.out.println("[~] Account deleted.");
            } else {
                System.out.println("[~] Account not deleted.");
            }
        }
    }

    /**
     * Create a new account
     * Ask customer to enter account type and initial balance
     * Calls CustomerViews.createAccount() to create account
     * and prints creation status message.
     * Params: accountType, initialBalance
     */
    private static void createAccount() {
        // TODO: Implement createAccount form for customer CLI
        System.out.println("  [T] Account types: 1) Transactional, 2) Long-Term, 3) Short-Term");
        System.out.print("  [>] Account type: ");
        int accountType = Integer.parseInt(sc.nextLine());
        System.out.print("  [>] Initial money: ");
        Double initialMoney = Double.parseDouble(sc.nextLine());
        CustomerViews.CreateAccountStatus status = CustomerViews.customerCreateAccount(customer, accountType,
                initialMoney);
        switch (status) {
            case SUCCESS:
                System.out.println("[+] Account created successfully.");
                break;
            case FAILURE:
                System.out.println("[!] Account creation failed.");
                break;
            case INVALID_ACCOUNT_TYPE:
                System.out.println("[!] Invalid account type.");
                break;
            case MAX_PENDING_ACCOUNT_LIMIT_REACHED:
                System.out.println("[!] Maximum pending account limit reached.");
                break;
            case CUSTOMER_NOT_ACTIVE:
                System.out.println("[!] Your subscription is not active.");
                break;
            case NEGATIVE_BALANCE:
                System.out.println("[!] Negative balance.");
                break;
            default:
                System.out.println("[!] Unknown error.");
                break;
        }
    }

    /**
     * Prints the customer accounts (excluding the deleted accounts)
     */
    private static void listAccounts() {
        System.out.println("[+] Your accounts:");
        for (Account account : customer.getAllAccounts()) {
            if (!account.isDeleted()) {
                // Print account id, balance, account type and status
                System.out.println("  [" + account.getStatus() + "] Account ID: " + account.getId() + " - Balance: "
                        + account.getBalance() + " - Type: " + account.getType());
            }
        }
    }

    /**
     * Print help text.
     */
    public static void printHelp() { // TODO: Add customer commands to help text
        System.out.println("[?] Customer Commands:");
        System.out.println("[C] account list (Print your accounts)"); // TODO: Add account list command
        System.out.println("[C] account show $id (Print account details)"); // TODO: Add account show command
        System.out.println("[C] account create (Create a new account)"); // TODO: Add account create command
        System.out.println("[C] account delete $id (Delete an account)"); // TODO: Add account delete command
        System.out.println("[C] transaction list $accountId (Print an account transactions)"); // TODO: Add transaction
                                                                                               // list command
        System.out.println("[C] transaction show &transactionId (Print a transaction details)"); // TODO: Add
                                                                                                 // transaction show
                                                                                                 // command
        System.out.println("[C] transfer $fromAccountId $toAccountId $amount (Transfer money)"); // TODO: Add transfer
                                                                                                 // command
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
