package com.bank.views;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import com.bank.models.Account;
import com.bank.models.Customer;
import com.bank.models.Gender;
import com.bank.models.Account.AccountType;
import com.bank.validator.Validator;

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

        Period age = Period.between(LocalDate.from(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()),
                LocalDate.now());
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

    /**
     * Customer create account view
     * Takes customer, account type and balance as input
     * Creates new account with status = PENDING
     * Needs to be approved by admin or employees
     * 
     * @param customer Customer object
     * @param int      accountTypeNumber // 1: TRANSACTION, 2: LONGTERM, 3:
     *                 SHORTTERM
     * @param double   balance
     * @return CreateAccountStatus Creation status
     */
    public static CreateAccountStatus customerCreateAccount(Customer customer, int accountTypeNumber, double balance) {
        try {
            if (customer.getStatus() != Customer.CustomerStatus.ACTIVE)
                return CreateAccountStatus.CUSTOMER_NOT_ACTIVE;
            if (customer.getPendingAccounts().size() >= 3)
                return CreateAccountStatus.MAX_PENDING_ACCOUNT_LIMIT_REACHED;
            if (balance < 0)
                return CreateAccountStatus.NEGATIVE_BALANCE;
            if (!customer.haveMoneyInSafeBox(balance))
                return CreateAccountStatus.NOT_ENOUGH_MONEY_IN_SAFE_BOX;

            AccountType accountType;
            // 1: TRANSACTION, 2: LONGTERM, 3: SHORTTERM
            switch (accountTypeNumber) {
                case 1:
                    accountType = AccountType.TRANSACTION;
                    break;
                case 2:
                    accountType = AccountType.LONGTERM;
                    break;
                case 3:
                    accountType = AccountType.SHORTTERM;
                    break;
                default:
                    return CreateAccountStatus.INVALID_ACCOUNT_TYPE;
            }

            Account newAccount = new Account();
            newAccount.setOwner(customer);
            newAccount.setBalance(balance);
            newAccount.setCreationDate(new Date());
            newAccount.setStatus(Account.AccountStatus.PENDING);
            newAccount.setType(accountType);
            newAccount.save();
            customer.withdrawFromSafeBoxBalance(balance);
            return CreateAccountStatus.SUCCESS;
        } catch (Exception e) {
            return CreateAccountStatus.FAILURE;
        }
    }

    /**
     * Customer delete account view
     * Takes customer and account as input, checks if account belongs to customer,
     * adds balance to safe box and deletes account
     * @param account Account object
     */
    public static AccountDeleteStatus customerDeleteAccount(Customer customer, Account account) {
        Customer accountOwner = account.getOwner();
        if (accountOwner.getId() != customer.getId())
            return AccountDeleteStatus.ACCOUNT_NOT_OWNED_BY_CUSTOMER;
        if (account.getStatus() == Account.AccountStatus.BANNED)
            return AccountDeleteStatus.ACCOUNT_BANNED;
        double balance = account.getBalance();
        customer.addToSafeBoxBalance(balance);
        account.delete();
        return AccountDeleteStatus.SUCCESS;
    }

    public enum CreateAccountStatus {
        SUCCESS,
        CUSTOMER_NOT_ACTIVE,
        MAX_PENDING_ACCOUNT_LIMIT_REACHED,
        NEGATIVE_BALANCE,
        INVALID_ACCOUNT_TYPE,
        NOT_ENOUGH_MONEY_IN_SAFE_BOX,
        FAILURE
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

    public enum AccountDeleteStatus {
        SUCCESS,
        ACCOUNT_NOT_OWNED_BY_CUSTOMER,
        ACCOUNT_BANNED
    }
}
