package com.bank.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Customer extends Person {
    static String CUSTOMER_DATAFILE_PATH = "src/main/java/com/bank/data/customers.csv";
    static ArrayList<Customer> allCustomers = new ArrayList<>(); // All created accounts

    // Note: Customer logs in with phone number
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
     * 
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
     * 
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
     * 
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
     * 
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
     * 
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

    /**
     * Read all customers from customers.csv file and save them to arraylist.
     * Format:
     * id, firstName, lastName, email, phoneNumber, passwordHash, gender,
     * nationalId, birthDate, joinDate, status
     */
    public static void loadData() {
        // TODO: Add all fields
        try (Scanner customerScanner = new Scanner(new File(CUSTOMER_DATAFILE_PATH))) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while (customerScanner.hasNextLine()) {
                String line = customerScanner.nextLine();
                String[] customerData = line.split(", ");
                Customer customer = new Customer();
                customer.setId(Long.parseLong(customerData[0]));
                customer.setFirstName(customerData[1]);
                customer.setLastName(customerData[2]);
                customer.setEmail(customerData[3]);
                customer.setPhoneNumber(customerData[4]);
                customer.setPasswordHash(customerData[5]);
                customer.setGender(Gender.valueOf(customerData[6]));
                customer.setNationalId(customerData[7]);
                customer.setBirthDate(dateFormat.parse(customerData[8]));
                customer.setJoinDate(dateFormat.parse(customerData[9]));
                customer.setStatus(CustomerStatus.valueOf(customerData[10]));
                customer.save();
            }
        } catch (NumberFormatException | FileNotFoundException | ParseException e) {
            System.err.println("[!] Error loading customers from file.");
            e.printStackTrace();
        }
    }

    /**
     * Save all customers to customers.csv file.
     * Write all customers data to customers.csv file in format:
     * id, firstName, lastName, email, phoneNumber, passwordHash, gender,
     * nationalId, birthDate, joinDate, status
     */
    public static void saveData() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            FileWriter dataFile = new FileWriter(CUSTOMER_DATAFILE_PATH);
            for (Customer customer : allCustomers) {
                dataFile.write(customer.getId() + ", " + customer.getFirstName() + ", " + customer.getLastName() + ", "
                        + customer.getEmail() + ", " + customer.getPhoneNumber() + ", " + customer.getPassword() + ", "
                        + customer.getGender() + ", " + customer.getNationalId() + ", "
                        + dateFormat.format(customer.getBirthDate()) + ", " + dateFormat.format(customer.getJoinDate())
                        + ", " + customer.getStatus() + "\n");
            }
            dataFile.close();
        } catch (Exception e) {
            System.err.println("[!] Error saving customers to file.");
            System.err.println(e);
        }
    }

    public String toString() {
        return "Customer: " + this.getFirstName() + " " + this.getLastName() + " " + this.getPhoneNumber() + " ("
                + this.getStatus() + ")";
    }

    // Getters and setters
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