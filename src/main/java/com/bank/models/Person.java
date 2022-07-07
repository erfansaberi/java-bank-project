package com.bank.models;

import java.util.Date;

public class Person {
    String firstName;
    String lastName;
    Date birthDate;
    Gender gender;
    String nationalId;

    public Person() {

    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
