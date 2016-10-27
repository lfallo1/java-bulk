package com.catalyst.teammateria.timeclock.formbeans.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;

@RunWith (MockitoJUnitRunner.class)
public class RegistrationFormTest {

    @InjectMocks
    private RegistrationForm    target;
    private static final String FIRST_NAME       = "firstName";
    private static final String LAST_NAME        = "lastName";
    private static final String USERNAME         = "firstLast";
    private static final String EMAIL            = "first@last.com";
    private static final String PASSWORD         = "password";
    private static final String PASSWORD_CONFIRM = "password";

    @Test
    public void testFirstName() {
        target.setFirstName(FIRST_NAME);
        assertEquals(target.getFirstName(), FIRST_NAME);
    }

    @Test
    public void testLastName() {
        target.setLastName(LAST_NAME);
        assertEquals(target.getLastName(), LAST_NAME);
    }

    @Test
    public void testUsername() {
        target.setUsername(USERNAME);
        assertEquals(target.getUsername(), USERNAME);
    }

    @Test
    public void testEmail() {
        target.setEmail(EMAIL);
        assertEquals(target.getEmail(), EMAIL);
    }

    @Test
    public void testPassword() {
        target.setPassword(PASSWORD);
        assertEquals(target.getPassword(), PASSWORD);
    }

    @Test
    public void testPasswordConfirm() {
        target.setPasswordConfirm(PASSWORD_CONFIRM);
        assertEquals(target.getPasswordConfirm(), PASSWORD_CONFIRM);
    }
}
