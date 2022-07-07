package com.bank.models;

import java.util.ArrayList;

public class Customer {
    ArrayList<Customer> allCustomers = new ArrayList<>(); // All created accounts

    private long id;
}

enum CustomerStatus {
    Pending,
    Active,
    Deactivated,
    Banned,
    Deleted
}