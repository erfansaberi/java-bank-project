package com.bank.views;

import com.bank.models.Admin;
import com.bank.models.Employee;
import com.bank.validator.Validator;

public class ManagementViews {

    // Access level of manager
    public enum accessLevel {
        ADMIN,
        EMPLOYEE,
        NONE
    };

    /**
     * Managers login view.
     * Admin logs in with username and password, saved in configs.properties.
     * Employees login with email and password.
     * 
     * @param usernameOrEmail the username or email of the manager.
     * @param rawPassword     the password of the manager.
     * @return accessLevel of the manager. (ADMIN, EMPLOYEE, NONE)
     */
    public static accessLevel authenticate(String usernameOrEmail, String rawPassword) {
        if (Validator.isValidEmail(usernameOrEmail)) {
            if (Employee.authenticate(usernameOrEmail, rawPassword))
                return accessLevel.EMPLOYEE;
        } else if (Admin.authenticate(usernameOrEmail, rawPassword))
            return accessLevel.ADMIN;
        return accessLevel.NONE;
    }
}
