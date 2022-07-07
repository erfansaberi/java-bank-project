package com.bank.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    static Employee employee;
    static String testEmail = "cartel@ut.ac.ir";
    static String testPassword = "HESOYAM";

    @BeforeAll
    static void setUp() {
        employee = new Employee();
        employee.setEmail(testEmail);
        employee.setPassword(testPassword);
        employee.save();
    }

    @Test
    void testEmployeeEmailExists() {
        assertTrue(Employee.emailExists(testEmail));
    }

    @Test
    void testGetEmployeeByEmail() {
        assertEquals(Employee.getByEmail(testEmail).getId(), employee.getId());
        assertEquals(Employee.getByEmail(testEmail).getEmail(), employee.getEmail());
    }

    @Test
    void testEmployeeAuthenticate() {
        assertTrue(Employee.authenticate(testEmail, testPassword));
        assertFalse(Employee.authenticate("anotherEmail@ut.ac.ur", testPassword));
        assertFalse(Employee.authenticate(testEmail, "anotherPassword"));
    }
}