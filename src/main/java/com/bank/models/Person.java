package com.bank.models;

import java.util.Date;

public class Person {
    long id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    String password; // Hashed
    Date birthDate;
    Gender gender;
    String nationalId;
    Date joinDate;

    public Person() {

    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getter and setters
    
    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        // TODO: Validate email with Validator.validateEmail()
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String rawPassword) {
        this.password = Core.hashPassword(rawPassword);
    }

    public void setPasswordHash(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }
    
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public String getFullNameWithId() {
        return this.firstName + " " + this.lastName + " (" + this.id + ")";
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }
    
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
}
