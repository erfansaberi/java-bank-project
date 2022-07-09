package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends Person {
    static ArrayList<Customer> allCustomers = new ArrayList<>(); // All created accounts

    private long id;
    private String email;
    private String phoneNumber; // Customer logs in with phone number
    private String password; // Hashed
    private Date joinDate;
    private CustomerStatus status;

    /**
     * Create new customer. Customer is not saved to arraylist.
     * Save method must be called to save customer to arraylist.
     * Id is generated automatically.
     * Status is set to CustomerStatus.ACTIVE.
     * Join date is set to current date.
     */
    public Customer() {
        super();
        this.joinDate = new Date();
        this.status = CustomerStatus.ACTIVE;
        this.id = allCustomers.size() + 1;
    }

    /**
     * Save customer to arraylist.
     */
    public void save() {
        allCustomers.add(this);
    }

    /**
     * Check if phone number is used in arraylist.
     * @param phoneNumber Phone number to check.
     * @return True if phone number is used, false otherwise.
     */
    public static boolean phoneNumberExists(String phoneNumber) {
        for (Customer customer : allCustomers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                if (customer.getStatus() != CustomerStatus.DELETED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check if email is used in arraylist.
     * @param email Email to check.
     * @return True if email is used, false otherwise.
     */
    public static boolean emailExists(String email) {
        for (Customer customer : allCustomers) {
            if (customer.getEmail().equals(email)) {
                if (customer.getStatus() != CustomerStatus.DELETED) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get customer by phone number.
     * @param phoneNumber Phone number to get customer by.
     * @return Customer with given phone number, null if not found.
     */
    public static Customer getByPhoneNumber(String phoneNumber) {
        for (Customer customer : allCustomers) {
            if (customer.getPhoneNumber().equals(phoneNumber)) {
                if (customer.getStatus() != CustomerStatus.DELETED) {
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * Get customer by email.
     * @param email Email to get customer by.
     * @return Customer with given email, null if not found.
     */
    public static Customer getByEmail(String email) {
        for (Customer customer : allCustomers) {
            if (customer.getEmail().equals(email)) {
                if (customer.getStatus() != CustomerStatus.DELETED) {
                    return customer;
                }
            }
        }
        return null;
    }

    /**
     * Authenticate customer.
     * @param phoneNumber Phone number entered by user.
     * @param rawPassword Password entered by user.
     * @return true if phone number and password are correct, false otherwise
     */
    public static boolean authenticate(String phoneNumber, String rawPassword) {
        if (phoneNumberExists(phoneNumber)) {
            Customer customer = Customer.getByPhoneNumber(phoneNumber);
            if (customer.getPassword().equals(Core.hashPassword(rawPassword))) {
                if (customer.getStatus() == CustomerStatus.ACTIVE) {
                    return true;
                }
            }
        }
        return false;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordHash(String passwordHash) {
        this.password = passwordHash;
    }

    public void setPassword(String rawPassword) {
        this.password = Core.hashPassword(rawPassword);
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public static ArrayList<Customer> getAllCustomers() {
        return allCustomers;
    }

}

enum CustomerStatus {
    PENDING,
    ACTIVE,
    INACTIVE,
    BANNED,
    DELETED
}