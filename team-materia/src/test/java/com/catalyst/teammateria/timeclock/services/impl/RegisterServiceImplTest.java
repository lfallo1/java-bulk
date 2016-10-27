package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;
import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;
import com.catalyst.teammateria.timeclock.services.RegisterService;
import com.catalyst.teammateria.timeclock.servicesimpl.RegisterServiceImpl;

@RunWith (MockitoJUnitRunner.class)
public class RegisterServiceImplTest {

    private static final String TRUE_STRING = "true";

    private static final String EMPTY_STRING = "";

    private static final String EMAIL_VALID = "user@email.com";

    private static final String USERNAME_VALID = "user";

    private static final String LAST_NAME_VALID = "Doe";

    private static final String FIRST_NAME_VALID = "John";

    private static final String PASSWORD_VALID = "Password1?";

    private static final String EMAIL_INVALID = "useratemaildotcom";

    private static final String USERNAME_INVALID = "user!";

    private static final String LAST_NAME_INVALID = "Doe!";

    private static final String FIRST_NAME_INVALID = "John!";

    private static final String PASSWORD_INVALID = "password";

    private static final String PASSWORD_CONFIRM_INVALID = "drowssap";

    private static final String ALL_VALIDATION_ERRORS = "Errors on first name<br>Errors on last name<br>Errors on username<br>Errors on email<br>Errors on password<br>Passwords do not match<br>";

    @InjectMocks
    private RegisterService target = new RegisterServiceImpl();

    @Mock
    UserDao userDao;

    @Mock
    UserPasswordDao userPasswordDao;

    @Mock
    User user;

    private RegistrationForm form = new RegistrationForm();

    @Test
    public void testCheckEmail() {
        // Test valid email
        Mockito.when(userDao.emailAvailable(EMAIL_VALID)).thenReturn(true);
        assertTrue(target.checkEmail(EMAIL_VALID));
        // Test invalid email
        Mockito.when(userDao.emailAvailable(EMAIL_INVALID)).thenReturn(false);
        assertFalse(target.checkEmail(EMAIL_INVALID));
    }

    @Test
    public void testCheckUsername() {
        // Test valid username
        Mockito.when(userDao.userNameAvailable(USERNAME_VALID))
                .thenReturn(true);
        assertTrue(target.checkUsername(USERNAME_VALID));
        // Test invalid username
        Mockito.when(userDao.userNameAvailable(USERNAME_INVALID)).thenReturn(
                false);
        assertFalse(target.checkUsername(USERNAME_INVALID));
    }

    @Test
    public void testValidate() {
        // Test a valid form, assert no errors are present
        setFormValid();
        String valid = target.validate(form).toString();
        assertEquals(EMPTY_STRING, valid);
        // Test an invalid form, assert that all errors are present
        setFormInvalid();
        String invalid = target.validate(form).toString();
        assertEquals(ALL_VALIDATION_ERRORS, invalid);
    }

    @Test
    public void testAddValidForm() {
        // Test valid forms
        setFormValid();
        // Add a valid form with a non-existent email
        Mockito.when(userDao.emailAvailable(EMAIL_VALID)).thenReturn(true);
        Mockito.when(userDao.userNameAvailable(USERNAME_VALID))
                .thenReturn(true);
        String added = target.add(form);
        assertTrue(TRUE_STRING.equals(added));
    }

    @Test
    public void testAddInvalidForm() {
        setFormInvalid();
        String added = target.add(form);
        assertFalse(TRUE_STRING.equals(added));
    }

    // Sets the form to a valid user fields
    private void setFormValid() {
        form = new RegistrationForm();
        form.setPassword(PASSWORD_VALID);
        form.setPasswordConfirm(PASSWORD_VALID);
        form.setFirstName(FIRST_NAME_VALID);
        form.setLastName(LAST_NAME_VALID);
        form.setUsername(USERNAME_VALID);
        form.setEmail(EMAIL_VALID);
    }

    // Sets the form to invalid user fields
    private void setFormInvalid() {
        form = new RegistrationForm();
        form.setPassword(PASSWORD_INVALID);
        form.setPasswordConfirm(PASSWORD_CONFIRM_INVALID);
        form.setFirstName(FIRST_NAME_INVALID);
        form.setLastName(LAST_NAME_INVALID);
        form.setUsername(USERNAME_INVALID);
        form.setEmail(EMAIL_INVALID);
    }
}
