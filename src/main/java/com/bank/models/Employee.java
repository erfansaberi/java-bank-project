package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Employee extends Person {
    ArrayList<Employee> allEmployees = new ArrayList<>(); // All created accounts

    private long id;
    private String email;
    private String password; // Hashed
    private Date joinDate;
    private EmployeeStatus status;

}

enum EmployeeStatus {
    Active,
    Deactive,
    Deleted
}