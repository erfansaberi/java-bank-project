package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    static String ACCOUNT_DATAFILE_PATH = "src/main/java/com/bank/data/accounts.csv";
    static ArrayList<Account> allAccounts = new ArrayList<>(); // All created accounts

    private long id;
    private Customer owner;
    private double balance;
    private Date creationDate;
    private AccountType type;
    private AccountStatus status;
    private ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Constructor for Account
     */
    public Account() {
        this.id = allAccounts.size() + 1;
        this.balance = 0;
        this.creationDate = new Date();
        this.status = AccountStatus.ACTIVE;
    }

    /**
     * Constructor for Account
     *
     * @param owner Customer
     * @param balance double
     */
    public Account(Customer owner, double balance) {
        this.id = allAccounts.size() + 1;
        this.owner = owner;
        this.balance = balance;
        this.creationDate = new Date();
        this.status = AccountStatus.ACTIVE;
    }

    /**
     * Save account to arraylist
     */
    public void save() {
        allAccounts.add(this);
    }

    /**
     * Set account status = DELETED
     */
    public void delete() {
        this.status = AccountStatus.DELETED;
    }

    /**
     * Set account status = ACTIVE
     */
    public void approve() {
        this.status = AccountStatus.ACTIVE;
    }

    /**
     * Set account status = BANNED
     */
    public void ban() {
        this.status = AccountStatus.BANNED;
    }

    /**
     * Check if account id exists
     * @param id account id to check
     * @return true if account id exists, false if not
     */
    public static boolean idExists(long id) {
        for (Account account : allAccounts) {
            if (account.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get account by id
     * @param id account id to get
     * @return account if found, null if not
     */
    public static Account getAccountById(long id) {
        for (Account account : allAccounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }

    public static void loadData() {
        // TODO: Load all accounts from csv file

    }

    public static void saveData() {
        // TODO: Save all accounts to csv file
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

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return status == AccountStatus.DELETED;
    }

    @Override
    public String toString() {
        return "Account :{ " +
                "id=" + id +
                ", owner=" + owner.getFullNameWithId() +
                ", balance=" + balance +
                ", creationDate=" + creationDate +
                ", status=" + status + "}" ;
    }

    public static ArrayList<Account> getAllAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        for (Account account : allAccounts) {
            if (account.getStatus() != AccountStatus.DELETED) {
                accounts.add(account);
            }
        }
        return accounts;
    }

    public static ArrayList<Account> getAllPendingAccounts() {
        ArrayList<Account> pendingAccounts = new ArrayList<>();
        for (Account account : allAccounts) {
            if (account.getStatus() == AccountStatus.PENDING) {
                pendingAccounts.add(account);
            }
        }
        return pendingAccounts;
    }

    public enum AccountStatus {
        PENDING,
        ACTIVE,
        INACTIVE,
        BANNED,
        DELETED
    }
    
    public enum AccountType {
        TRANSACTION,
        LONGTERM,
        SHORTTERM
    }
}

