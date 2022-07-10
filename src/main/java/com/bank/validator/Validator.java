package com.bank.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validator {
    /**
     * Validates the given email address by regular expression.
     * 
     * @param email the email address to validate.
     * @return true if the email is valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        return email.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
    }

    /**
     * Validates the given national id number by regular expression.
     * National id number must be exactly 10 digits.
     * 
     * @param nationalId the national id number to validate.
     * @return true if the national id number is valid, false otherwise.
     */
    public static boolean isValidNationalId(String nationalId) {
        return nationalId.matches("^[0-9]{10}$");
    }

    /**
     * Validates the given phone number by regular expression.
     * Phone number must be exactly 10 digits.
     * 
     * @param phoneNumber the phone number to validate.
     * @return true if the phone number is valid, false otherwise.
     */
    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^[0-9]{11}$");
    }

    /**
     * Validates the given date to be in the format YYYY-MM-DD
     * by parsing the given date string with the given format
     * in a try-catch block.
     * @param birthDateStr
     * @return true if the date is valid, false otherwise.
     */
    public static boolean isValidBirthDate(String birthDateStr) {
        try {
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(birthDateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
