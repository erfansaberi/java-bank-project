package com.bank.models;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    static Employee activeEmployee;
    static Employee inactiveEmployee;
    static Employee deletedEmployee;
    static String testEmailActive = "cartel@ut.ac.ir";
    static String testEmailInactive = "inactive@ut.ac.ir";
    static String testEmailDeleted = "deleted@ut.ac.ir";
    static String testPassword = "HESOYAM";

    @BeforeAll
    static void setUp() {
        Core.loadConfig();
        // Create an active employee
        activeEmployee = new Employee();
        activeEmployee.setEmail(testEmailActive);
        activeEmployee.setPassword(testPassword);
        activeEmployee.save();

        // Create an inactive employee
        inactiveEmployee = new Employee();
        inactiveEmployee.setEmail(testEmailInactive);
        inactiveEmployee.setPassword(testPassword);
        inactiveEmployee.setStatus(EmployeeStatus.INACTIVE);
        inactiveEmployee.save();

        // Create a deleted employee
        deletedEmployee = new Employee();
        deletedEmployee.setEmail(testEmailDeleted);
        deletedEmployee.setPassword(testPassword);
        deletedEmployee.setStatus(EmployeeStatus.DELETED);
        deletedEmployee.save();
    }

    @Test
    void testEmployeeEmailExists() {
        assertTrue(Employee.emailExists(testEmailActive));
        assertTrue(Employee.emailExists(testEmailInactive));
        assertFalse(Employee.emailExists(testEmailDeleted));
    }

    @Test
    void testGetEmployeeByEmail() {
        assertEquals(Employee.getByEmail(testEmailActive).getId(), activeEmployee.getId());
        assertEquals(Employee.getByEmail(testEmailActive).getEmail(), activeEmployee.getEmail());
    }

    @Test
    void testEmployeeAuthenticate() {
        assertTrue(Employee.authenticate(testEmailActive, testPassword));
        assertFalse(Employee.authenticate("anotherEmail@ut.ac.ur", testPassword));
        assertFalse(Employee.authenticate(testEmailActive, "anotherPassword"));
    }
}