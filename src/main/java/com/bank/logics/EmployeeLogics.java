package com.bank.logics;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.bank.models.Employee;
import com.bank.models.Gender;
import com.bank.validator.Validator;

public class EmployeeLogics {
    /**
     * Employee register logic
     * 
     * @param firstName
     * @param lastName
     * @param email
     * @param phoneNumber
     * @param password
     * @param confirmPassword
     * @param birthDateStr
     * @param genderStr
     * @param nationalId
     * @param salary
     * @return Status message
     */
    public static RegisterStatus employeeRegister(String firstName, String lastName, String email, String phoneNumber,
            String password, String confirmPassword, String birthDateStr, String genderStr, String nationalId,
            double salary) {

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

        Period age = Period.between(LocalDate.from(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                LocalDate.now());
        if (age.getYears() < 18)
            return RegisterStatus.TOO_YOUNG_TO_REGISTER;

        if (Employee.getByEmail(email) != null)
            return RegisterStatus.EMAIL_IN_USE;
        if (Employee.getByPhoneNumber(phoneNumber) != null)
            return RegisterStatus.PHONE_NUMBER_IN_USE;
        if (Employee.getByNationalId(nationalId) != null)
            return RegisterStatus.NATIONAL_ID_IN_USE;

        Gender gender;
        try {
            gender = Gender.valueOf(genderStr);
        } catch (Exception e) {
            return RegisterStatus.INVALID_GENDER;
        }

        Employee newEmployee = new Employee();
        newEmployee.setFirstName(firstName);
        newEmployee.setLastName(lastName);
        newEmployee.setEmail(email);
        newEmployee.setPhoneNumber(phoneNumber);
        newEmployee.setPassword(password);
        newEmployee.setGender(gender);
        newEmployee.setNationalId(nationalId);
        newEmployee.setBirthDate(birthDate);
        newEmployee.setJoinDate(new Date());
        newEmployee.setStatus(Employee.EmployeeStatus.ACTIVE);
        newEmployee.save();
        return RegisterStatus.SUCCESS;
    }

    public enum RegisterStatus {
        SUCCESS,
        EMAIL_IN_USE, PHONE_NUMBER_IN_USE, NATIONAL_ID_IN_USE, PASSWORD_MISMATCH,
        INVALID_NATIONAL_ID, INVALID_PHONE_NUMBER, INVALID_EMAIL,
        PASSWORD_TOO_SHORT, INVALID_BIRTH_DATE, TOO_YOUNG_TO_REGISTER, INVALID_GENDER
    }
}
