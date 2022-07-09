package com.bank.views;

import org.junit.jupiter.api.Test;

import com.bank.models.Core;
import com.bank.models.Employee;
import com.bank.validator.Validator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;

class ManagementViewsTest {
    static Employee employee;
    static String testEmail = "erfan.saberi@ut.ac.ir";
    static String testPassword = "HESOYAM";

    @BeforeAll
    static void setUp() {
        Core.loadConfig();
        employee = new Employee();
        employee.setEmail(testEmail);
        employee.setPassword(testPassword);
        employee.save();
    }

    @Test
    void emailValidatorTest() {
        assertTrue(Validator.isValidEmail(testEmail));
        assertFalse(Validator.isValidEmail("invalidEmail"));
    }

    @Test
    void managerAuthenticateTest() {
        assertEquals(ManagementViews.accessLevel.EMPLOYEE, ManagementViews.authenticate(testEmail, testPassword));
        assertEquals(ManagementViews.accessLevel.NONE, ManagementViews.authenticate("wrong@email.com", testPassword));
    }
}