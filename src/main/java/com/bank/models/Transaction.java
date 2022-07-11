package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Transaction {
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
}
