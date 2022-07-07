package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Employee extends Person {
    static ArrayList<Employee> allEmployees = new ArrayList<>(); // All created accounts

    private long id;
    private String email;
    private String password; // Hashed
    private Date joinDate;
    private EmployeeStatus status;

    public Employee() {
        super();
        this.joinDate = new Date();
        this.status = EmployeeStatus.ACTIVE;
        this.id = allEmployees.size() + 1;
    }

    public void save() {
        allEmployees.add(this);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getId() {
        return this.id;
    }

    public void setPassword(String rawPassword) {
        this.password = Core.hashPassword(rawPassword);
    }

    public static boolean emailExists(String email) {
        for (Employee employee : allEmployees) {
            if (employee.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static Employee getByEmail(String email) {
        for (Employee employee : allEmployees) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return null;
    }

    public String getPassword() {
        return this.password;
    }

    public static boolean authenticate(String email, String rawPassword) {
        if (emailExists(email)) {
            Employee employee = Employee.getByEmail(email);
            if (employee.getPassword().equals(Core.hashPassword(rawPassword))) {
                return true;
            }
        }
        return false;
    }



}

enum EmployeeStatus {
    ACTIVE,
    DEACTIVATED,
    DELETED
}