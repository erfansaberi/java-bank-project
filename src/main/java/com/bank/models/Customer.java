package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {
    ArrayList<Customer> allCustomers = new ArrayList<>(); // All created accounts

    private long id;
    private String phoneNumber;
    private String password; // Hashed
    private Date joinDate;
    private CustomerStatus status;
}

enum CustomerStatus {
    PENDING,
    ACTIVE,
    INACTIVE,
    BANNED,
    DELETED
}