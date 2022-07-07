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

    /**
     * Create new employee. Employee is not saved to arraylist.
     * Save method must be called to save employee to arraylist.
     * Id is generated automatically.
     * Status is set to EmployeeStatus.ACTIVE.
     * Join date is set to current date.
     */
    public Employee() {
        super();
        this.joinDate = new Date();
        this.status = EmployeeStatus.ACTIVE;
        this.id = allEmployees.size() + 1;
    }

    /**
     * Save employee to arraylist.
     */
    public void save() {
        allEmployees.add(this);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        // TODO: Validate email with Validator.validateEmail()
        this.email = email;
    }

    public long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String rawPassword) {
        this.password = Core.hashPassword(rawPassword);
    }

    /**
     * Check if email is used in arraylist.
     * @param email Email to check.
     * @return True if email is used, false otherwise.
     */
    public static boolean emailExists(String email) {
        for (Employee employee : allEmployees) {
            if (employee.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get employee by email.
     * @param email Email to get employee by.
     * @return Employee with given email, null if not found.
     */
    public static Employee getByEmail(String email) {
        for (Employee employee : allEmployees) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Authenticate employee.
     * @param email Email entered by user.
     * @param rawPassword Password entered by user.
     * @return true if email and password are correct, false otherwise
     */
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

/**
 * Employee status.
 * ACTIVE: Employee is active.
 * INACTIVE: Employee is inactive.
 * DELETED: Employee is deleted.
 */
enum EmployeeStatus {
    ACTIVE,
    INACTIVE,
    DELETED
}