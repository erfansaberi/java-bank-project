package com.bank.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Transaction {
    static String TRANSACTION_DATAFILE_PATH = "src/main/java/com/bank/data/transactions.csv";
    private static ArrayList<Transaction> allTransactions = new ArrayList<>();

    private long id;
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private Date date;

    public Transaction(Account fromAccount, Account toAccount, double amount) {
        this.id = allTransactions.size() + 1;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.date = new Date();
    }

    public void save() {
        allTransactions.add(this);
    }

    /**
     * Transfer money from one account to another account and save the transaction.
     * 
     * @param fromAccount Account id to transfer money from.
     * @param toAccount Account id to transfer money to.
     * @param amount Amount to transfer.
     * @return Transaction object.
     */
    public static Transaction transferMoney(Account fromAccount, Account toAccount, double amount) {
        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);
        Transaction transaction = new Transaction(fromAccount, toAccount, amount);
        transaction.save();
        return transaction;
    }

     /**
     * Read all transactions from transactions.csv file and save them to arraylist.
     * Format:
     * id, fromAccountId, toAccountId, amount, date
     */
    public static void loadData() {
        try (Scanner transactionScanner = new Scanner(new File(TRANSACTION_DATAFILE_PATH))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (transactionScanner.hasNextLine()) {
                String line = transactionScanner.nextLine();
                String[] transactionData = line.split(", ");
                Account fromAccount = Account.getByIdEvenIfDeleted(Long.parseLong(transactionData[1]));
                Account toAccount = Account.getByIdEvenIfDeleted(Long.parseLong(transactionData[2]));
                Transaction transaction = new Transaction(fromAccount, toAccount, Double.parseDouble(transactionData[3]));
                transaction.setId(Long.parseLong(transactionData[0]));
                transaction.setDate(dateFormat.parse(transactionData[4]));
                transaction.save();
            }
        } catch (NumberFormatException | FileNotFoundException | ParseException e) {
            System.err.println("[!] Error loading accounts from file.");
            e.printStackTrace();
        }
    }

    /**
     * Save all transactions to transactions.csv file.
     * Write all transactions data to transactions.csv file in format:
     * id, fromAccountId, toAccountId, amount, date
     */
    public static void saveData() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            FileWriter dataFile = new FileWriter(TRANSACTION_DATAFILE_PATH);
            for (Transaction transaction : allTransactions) {
                dataFile.write(transaction.getId() + ", " + transaction.getFromAccount().getId() + ", " + transaction.getToAccount().getId() + ", "
                        + transaction.getAmount() + ", " + dateFormat.format(transaction.getDate()) + "\n");
            }
            dataFile.close();
        } catch (Exception e) {
            System.err.println("[!] Error saving transactions to file.");
            System.err.println(e);
        }
    }

    // Getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static Transaction getTransactionById(long transactionId) {
        for (Transaction transaction : allTransactions) {
            if (transaction.getId() == transactionId) {
                return transaction;
            }
        }
        return null;
    }

    public static ArrayList<Transaction> getAllTransactions() {
        return allTransactions;
    }

    public static ArrayList<Transaction> getAccountTransactions(Account account) {
        ArrayList<Transaction> accountTransactions = new ArrayList<>();
        for (Transaction transaction : allTransactions) {
            if (transaction.getFromAccount().getId() == account.getId() || transaction.getToAccount().getId() == account.getId()) {
                accountTransactions.add(transaction);
            }
        }
        return accountTransactions;
    }


}
