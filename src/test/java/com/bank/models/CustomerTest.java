package com.bank.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    static Customer pendingCustomer;
    static Customer activeCustomer;
    static Customer inactiveCustomer;
    static Customer bannedCustomer;
    static Customer deletedCustomer;
    static String testEmailActive = "cartel@ut.ac.ir";
    static String testEmailPending = "pending@ut.ac.ir";
    static String testEmailInactive = "inactive@ut.ac.ir";
    static String testEmailBanned = "banned@ut.ac.ir";
    static String testEmailDeleted = "deleted@ut.ac.ir";
    static String testPhoneNumberActive = "09121234567";
    static String testPhoneNumberPending = "09121234568";
    static String testPhoneNumberInactive = "09121234569";
    static String testPhoneNumberBanned = "09121234570";
    static String testPhoneNumberDeleted = "09121234571";
    static String testPassword = "HESOYAM";

    @BeforeAll
    static void setUp() {
        Core.loadConfig();
        // Create a pending Customer
        pendingCustomer = new Customer();
        pendingCustomer.setPhoneNumber(testPhoneNumberPending);
        pendingCustomer.setEmail(testEmailPending);
        pendingCustomer.setPassword(testPassword);
        pendingCustomer.setStatus(CustomerStatus.PENDING);
        pendingCustomer.save();

        // Create an active Customer
        activeCustomer = new Customer();
        activeCustomer.setPhoneNumber(testPhoneNumberActive);
        activeCustomer.setEmail(testEmailActive);
        activeCustomer.setPassword(testPassword);
        activeCustomer.save();

        // Create an inactive Customer
        inactiveCustomer = new Customer();
        inactiveCustomer.setPhoneNumber(testPhoneNumberInactive);
        inactiveCustomer.setEmail(testEmailInactive);
        inactiveCustomer.setPassword(testPassword);
        inactiveCustomer.setStatus(CustomerStatus.INACTIVE);
        inactiveCustomer.save();

        // Create a banned Customer
        bannedCustomer = new Customer();
        bannedCustomer.setPhoneNumber(testPhoneNumberBanned);
        bannedCustomer.setEmail(testEmailBanned);
        bannedCustomer.setPassword(testPassword);
        bannedCustomer.setStatus(CustomerStatus.BANNED);
        bannedCustomer.save();

        // Create a deleted Customer
        deletedCustomer = new Customer();
        deletedCustomer.setPhoneNumber(testPhoneNumberDeleted);
        deletedCustomer.setEmail(testEmailDeleted);
        deletedCustomer.setPassword(testPassword);
        deletedCustomer.setStatus(CustomerStatus.DELETED);
        deletedCustomer.save();
    }

    @Test
    void testCustomerEmailExists() {
        assertTrue(Customer.emailExists(testEmailPending));
        assertTrue(Customer.emailExists(testEmailActive));
        assertTrue(Customer.emailExists(testEmailInactive));
        assertTrue(Customer.emailExists(testEmailBanned));
        assertFalse(Customer.emailExists(testEmailDeleted));
    }

    @Test
    void testGetCustomerByEmail() {
        assertEquals(Customer.getByEmail(testEmailActive).getId(), activeCustomer.getId());
        assertEquals(Customer.getByEmail(testEmailActive).getEmail(), activeCustomer.getEmail());
    }

    @Test
    void testCustomerAuthenticate() {
        assertTrue(Customer.authenticate(testPhoneNumberActive, testPassword));
        assertFalse(Customer.authenticate("anotherEmail@ut.ac.ur", testPassword));
        assertFalse(Customer.authenticate(testEmailActive, "anotherPassword"));
        assertFalse(Customer.authenticate(testPhoneNumberPending, testPassword));
        assertFalse(Customer.authenticate(testPhoneNumberInactive, testPassword));
        assertFalse(Customer.authenticate(testPhoneNumberBanned, testPassword));
        assertFalse(Customer.authenticate(testPhoneNumberDeleted, testPassword));
    }
}