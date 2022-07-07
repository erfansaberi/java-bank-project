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
    
    public Employee(String firstName, String lastName, String email, String rawPassword) {
        super(firstName, lastName);
        this.email = email;
        this.password = hashPassword(rawPassword);
        this.joinDate = new Date();
        this.status = EmployeeStatus.ACTIVE;
        this.id = allEmployees.size() + 1;
    }

    public void save() {
        allEmployees.add(this);
    }



}

enum EmployeeStatus {
    Active,
    Deactive,
    Deleted
}