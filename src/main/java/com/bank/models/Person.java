package com.bank.models;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private Gender gender;
    private String nationalId;

    public Person() {

    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
