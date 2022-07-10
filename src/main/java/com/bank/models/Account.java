package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    static ArrayList<Account> allAccounts = new ArrayList<>(); // All created accounts

    private long id;
    private Customer owner;
    private double balance;
    private Date creationDate;
    private AccountStatus status;

    public Account() {
        this.id = allAccounts.size() + 1;
        this.balance = 0;
        this.creationDate = new Date();
        this.status = AccountStatus.ACTIVE;
    }

    public Account(Customer owner, double balance) {
        this.id = allAccounts.size() + 1;
        this.owner = owner;
        this.balance = balance;
        this.creationDate = new Date();
        this.status = AccountStatus.ACTIVE;
    }

    public void save() {
        allAccounts.add(this);
    }

    public static boolean idExists(long id) {
        for (Account account : allAccounts) {
            if (account.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public static Account getAccountById(long id) {
        for (Account account : allAccounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public static ArrayList<Account> getAllAccounts() {
        return allAccounts;
    }

    @Override
    public String toString() {
        return "Account {" +
                "id=" + id +
                ", owner=" + owner.getFullNameWithId() +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", status=" + status +
                '}';
    }

}

enum AccountStatus {
    PENDING,
    ACTIVE,
    DEACTIVATED,
    DELETED
}

enum AccountType {
    TRANSACTION,
    LONGTERM,
    SHORTTERM
}