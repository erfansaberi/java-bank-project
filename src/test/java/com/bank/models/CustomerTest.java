package com.bank.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.logics.CustomerLogics;

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
        pendingCustomer.setStatus(Customer.CustomerStatus.PENDING);
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
        inactiveCustomer.setStatus(Customer.CustomerStatus.INACTIVE);
        inactiveCustomer.save();

        // Create a banned Customer
        bannedCustomer = new Customer();
        bannedCustomer.setPhoneNumber(testPhoneNumberBanned);
        bannedCustomer.setEmail(testEmailBanned);
        bannedCustomer.setPassword(testPassword);
        bannedCustomer.setStatus(Customer.CustomerStatus.BANNED);
        bannedCustomer.save();

        // Create a deleted Customer
        deletedCustomer = new Customer();
        deletedCustomer.setPhoneNumber(testPhoneNumberDeleted);
        deletedCustomer.setEmail(testEmailDeleted);
        deletedCustomer.setPassword(testPassword);
        deletedCustomer.setStatus(Customer.CustomerStatus.DELETED);
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

    /**
     * Test Customer create account using the following steps:
     * CustomeLogics.customerCreateAccount(Customer customer, int accountTypeNumber, double balance)
     * Create 3 accounts for active customer and check if they are created correctly (1:Transaction, 2:LONGTERM, 3:SHORTTERM) (should return CustomerLogics.CreateAccountStatus.SUCCESS)
     * Create another account for active customer (should fail) (maximum pending accounts reached) (should return CustomerLogics.CreateAccountStatus.MAX_PENDING_ACCOUNT_LIMIT_REACHED)
     * Create 1 account for deleted customer (should fail) (should return CustomerLogics.CreateAccountStatus.CUSTOMER_NOT_ACTIVE)
     * Create 1 account for banned customer (should fail) (should return CustomerLogics.CreateAccountStatus.CUSTOMER_NOT_ACTIVE)
     * Create 1 account for pending customer (should fail) (should return CustomerLogics.CreateAccountStatus.CUSTOMER_NOT_ACTIVE)
     */
    @Test
    void testCustomerCreateAccount() {
        assertEquals(CustomerLogics.CreateAccountStatus.SUCCESS, CustomerLogics.customerCreateAccount(activeCustomer, 1, 0));
        assertEquals(CustomerLogics.CreateAccountStatus.SUCCESS, CustomerLogics.customerCreateAccount(activeCustomer, 2, 10000));
        assertEquals(CustomerLogics.CreateAccountStatus.SUCCESS, CustomerLogics.customerCreateAccount(activeCustomer, 3, 10000));
        assertEquals(CustomerLogics.CreateAccountStatus.MAX_PENDING_ACCOUNT_LIMIT_REACHED, CustomerLogics.customerCreateAccount(activeCustomer, 1, 10000));
        assertEquals(CustomerLogics.CreateAccountStatus.CUSTOMER_NOT_ACTIVE, CustomerLogics.customerCreateAccount(deletedCustomer, 1, 0));
        assertEquals(CustomerLogics.CreateAccountStatus.CUSTOMER_NOT_ACTIVE, CustomerLogics.customerCreateAccount(bannedCustomer, 1, 0));
        assertEquals(CustomerLogics.CreateAccountStatus.CUSTOMER_NOT_ACTIVE, CustomerLogics.customerCreateAccount(pendingCustomer, 1, 0));
    }
}