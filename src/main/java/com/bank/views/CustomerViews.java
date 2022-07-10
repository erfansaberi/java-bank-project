package com.bank.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.bank.models.Customer;
import com.bank.models.Gender;
import com.bank.validator.Validator;

import javafx.css.CssParser.ParseError;

public class CustomerViews {
    /**
     * Customer register view
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param password
     * @param confirmPassword
     * @param birthDate
     * @param gender
     * @param nationalId
     * @return Status message
     */
    public static RegisterStatus customerRegister(String firstName, String lastName, String email, String phoneNumber,
            String password, String confirmPassword, String birthDateStr, String genderStr, String nationalId) {

        if (!password.equals(confirmPassword))
            return RegisterStatus.PASSWORD_MISMATCH;
        if (!Validator.isValidEmail(email))
            return RegisterStatus.INVALID_EMAIL;
        if (!Validator.isValidPhoneNumber(phoneNumber))
            return RegisterStatus.INVALID_PHONE_NUMBER;
        if (!Validator.isValidNationalId(nationalId))
            return RegisterStatus.INVALID_NATIONAL_ID;
        if (password.length() < 8)
            return RegisterStatus.PASSWORD_TOO_SHORT;

        Date birthDate;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            return RegisterStatus.INVALID_BIRTH_DATE;
        }

        Period age = Period.between(LocalDate.from(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()), LocalDate.now());
        if (age.getYears() < 18)
            return RegisterStatus.TOO_YOUNG_TO_REGISTER;

        if (Customer.getByEmail(email) != null)
            return RegisterStatus.EMAIL_IN_USE;
        if (Customer.getByPhoneNumber(phoneNumber) != null)
            return RegisterStatus.PHONE_NUMBER_IN_USE;
        if (Customer.getByNationalId(nationalId) != null)
            return RegisterStatus.NATIONAL_ID_IN_USE;

        Gender gender;
        try {
            gender = Gender.valueOf(genderStr);
        } catch (Exception e) {
            return RegisterStatus.INVALID_GENDER;
        }

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setPassword(password);
        newCustomer.setGender(gender);
        newCustomer.setNationalId(nationalId);
        newCustomer.setBirthDate(birthDate);
        newCustomer.setJoinDate(new Date());
        newCustomer.setStatus(Customer.CustomerStatus.PENDING);
        newCustomer.save();
        return RegisterStatus.SUCCESS;
    }

    public enum RegisterStatus {
        SUCCESS,
        PASSWORD_MISMATCH,
        PASSWORD_TOO_SHORT,
        EMAIL_IN_USE,
        PHONE_NUMBER_IN_USE,
        NATIONAL_ID_IN_USE,
        INVALID_EMAIL,
        INVALID_NATIONAL_ID,
        INVALID_PHONE_NUMBER,
        INVALID_BIRTH_DATE,
        INVALID_GENDER,
        TOO_YOUNG_TO_REGISTER;
    }
}
