package com.bank.cli;

import java.util.Scanner;

import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Transaction;
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
                        CLI.exit();
                        break;
                    
                    case "safebox":
                        printSafeBoxBalance();
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

                    case "transaction":
                        switch (inputSplit[1]) {
                            case "list":
                                listAccountTransactions(Long.parseLong(inputSplit[2]));
                                break;
                            case "show":
                                showTransaction(Long.parseLong(inputSplit[2]));
                                break;
                        }
                        break;

                    case "transfer":
                        transferMoney(Long.parseLong(inputSplit[1]), Long.parseLong(inputSplit[2]),
                            Double.parseDouble(inputSplit[3]));
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
     * Transfer money from one account to another account and save the transaction.
     * Checks if the accounts exist and if the amount is greater than 0.
     * @param fromAccountId Account id to transfer money from.
     * @param toAccountId Account id to transfer money to.
     * @param amount Amount to transfer.
     */
    private static void transferMoney(long fromAccountId, long toAccountId, double amount) {
        if (amount <= 0) {
            System.out.println("[!] Amount must be greater than 0.");
            return;
        }
        Account fromAccount = Account.getAccountById(fromAccountId);
        Account toAccount = Account.getAccountById(toAccountId);
        if (fromAccount == null) {
            System.out.println("[!] Account " + fromAccountId + " does not exist.");
            return;
        }
        if (toAccount == null) {
            System.out.println("[!] Account " + toAccountId + " does not exist.");
            return;
        }
        if (fromAccount.getOwner().getId() != customer.getId()) {
            System.out.println("[!] You are not the owner of account " + fromAccountId + ".");
            return;
        }
        if (fromAccount.getBalance() < amount) {
            System.out.println("[!] You do not have enough money in account " + fromAccountId + ".");
            return;
        }
        Transaction transaction = Transaction.transferMoney(fromAccount, toAccount, amount);
        System.out.println("[+] Money transferred, Transaction ID: " + transaction.getId());
    }

    /**
     * Print account transactions.
     * Checks if the account exists and if the customer is the owner of the account.
     * @param accountId Account id to print transactions for.
     */
    private static void listAccountTransactions(long accountId) {
        Account account = Account.getAccountById(accountId);
        if (account == null) {
            System.out.println("[!] Account not found.");
            return;
        }
        if (account.getOwner() != customer) {
            System.out.println("[!] You do not own this account.");
            return;
        }
        System.out.println("[~] Transactions for account " + account.getId() + ":");
        for (Transaction transaction : Transaction.getAccountTransactions(account)) {
            System.out.println("\t" + transaction.getId() + ": " + transaction.getAmount() + " Account "
                + transaction.getFromAccount().getId() + " -> " + transaction.getToAccount().getId() + " Date: " + transaction.getDate());
        }

    }

    /**
     * Print transaction details.
     * @param transactionId Transaction id to print details for.
     */
    private static void showTransaction(long transactionId) {
        Transaction transaction = Transaction.getTransactionById(transactionId);
        if (transaction == null) {
            System.out.println("[!] Transaction not found.");
            return;
        }
        if (transaction.getFromAccount().getOwner() != customer || transaction.getToAccount().getOwner() != customer) {
            System.out.println("[!] You do not own this transaction.");
            return;
        }
        System.out.println("[~] Transaction " + transaction.getId() + ":");
        System.out.println("\t" + transaction.getAmount() + " Account " + transaction.getFromAccount().getId() + " -> "
            + transaction.getToAccount().getId() + " Date: " + transaction.getDate());
    }


    /**
     * Prints the customer's safebox balance
     */
    private static void printSafeBoxBalance() {
        System.out.println("[+] SafeBox balance: " + customer.getSafeBoxBalance());
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
        } else if (account.getOwner().getId() != customer.getId()) {
            System.out.println("[!] You are not the owner of this account.");
        } else if (account.getStatus() == Account.AccountStatus.BANNED) {
            System.out.println("[!] Can't delete a banned account.");
        } else {
            System.out.println("[~] Account ID:" + account.getId());
            System.out.println("[~] Type: " + account.getType());
            System.out.println("[~] Status: " + account.getStatus());
            System.out.println("[~] Balance: " + account.getBalance());
            System.out.println("[~] Your account balance will be added to your safebox.");
            System.out.print("[?] Are you sure you want to delete this account? (y/n)> ");
            String input = sc.nextLine();
            if (input.equals("y")) {
                CustomerViews.AccountDeleteStatus deleteStatus = CustomerViews.customerDeleteAccount(customer, account);
                if (deleteStatus == CustomerViews.AccountDeleteStatus.SUCCESS) {
                    System.out.println("[~] Account deleted.");
                } else {
                    System.out.println("[!] Account deletion failed.");
                }
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
            case NOT_ENOUGH_MONEY_IN_SAFE_BOX:
                System.out.println("[!] Not enough money in safe box.");
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
        System.out.println("[C] safebox (Print your safebox balance)");
        System.out.println("[C] account list (Print your accounts)");
        System.out.println("[C] account show $id (Print account details)");
        System.out.println("[C] account create (Create a new account)");
        System.out.println("[C] account delete $id (Delete an account)");
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
