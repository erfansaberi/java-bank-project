package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Employee {
    ArrayList<Employee> allEmployees = new ArrayList<>(); // All created accounts

    private long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private Date joinDate;
    private String nationalId;
    private String password; // Hashed
    private String email;
    private Status status; // Active, Deactive, Deleted

}

enum Status {
    Active,
    Deactive,
    Deleted
}