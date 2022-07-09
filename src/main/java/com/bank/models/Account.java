package com.bank.models;

import java.util.ArrayList;

public class Account {
    ArrayList<Account> allAccounts = new ArrayList<>(); // All created accounts

    private long id;
}

enum AccountStatus {
    Pending,
    Active,
    Deactivated,
    Deleted
}

enum AccountType {
    Transaction,
    LongTerm,
    ShortTerm
}