package com.bank.views;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bank.models.Core;

public class CustomerViewsTest {
    @BeforeAll
    public static void setUp() {
        Core.loadConfig();
    }

    @Test
    public void testCustomerRegister() {
        assertEquals(CustomerViews.RegisterStatus.SUCCESS, CustomerViews.customerRegister("firstName", "lastName",
                "testemail@man.ir", "09333333333", "password", "password", "1993-02-10", "MALE", "0000000000"));
        assertEquals(CustomerViews.RegisterStatus.EMAIL_IN_USE, CustomerViews.customerRegister("firstName", "lastName",
                "testemail@man.ir", "09321333333", "password", "password", "1993-02-10", "MALE", "0000000001"));
        assertEquals(CustomerViews.RegisterStatus.PHONE_NUMBER_IN_USE,
                CustomerViews.customerRegister("firstName", "lastName",
                        "email2@man.ir", "09333333333", "password", "password", "1993-02-10", "MALE", "0000000002"));
        assertEquals(CustomerViews.RegisterStatus.PASSWORD_MISMATCH,
                CustomerViews.customerRegister("firstName", "lastName",
                        "e1@man.ir", "09123333333", "password", "password1", "1993-02-10", "MALE", "0000000012"));
        assertEquals(CustomerViews.RegisterStatus.PASSWORD_TOO_SHORT,
                CustomerViews.customerRegister("firstName", "lastName",
                        "em1a@man.ir", "09123331313", "passwor", "passwor", "1993-02-10", "MALE", "0000000102"));
        assertEquals(CustomerViews.RegisterStatus.NATIONAL_ID_IN_USE,
                CustomerViews.customerRegister("firstName", "lastName",
                        "12@man.ir", "09345633333", "password", "password", "1993-02-10", "MALE", "0000000000"));
        assertEquals(CustomerViews.RegisterStatus.INVALID_NATIONAL_ID,
                CustomerViews.customerRegister("firstName", "lastName",
                        "112@man.ir", "09345631233", "password", "password", "1993-02-10", "MALE", "00000000"));
        assertEquals(CustomerViews.RegisterStatus.TOO_YOUNG_TO_REGISTER,
                CustomerViews.customerRegister("firstName", "lastName",
                        "1224@man.ir", "09345633313", "password", "password", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "MALE", "0000000000"));
    }
}
