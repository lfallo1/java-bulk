package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.*;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.AddUserPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.AddUserPageSelenium;

public class AddUserTest {

    private static final int RANDOM_STRING_LENGTH = 3;

    private static final String PASSWORD_VALID = "Password1?";

    private static final String PASSWORD_INVALID_1 = "password";

    private static final String PASSWORD_INVALID_2 = "Password";

    private static final String PASSWORD_INVALID_3 = "password1?";

    private static final String EMAIL_VALID_FAKE = "user@email.com";

    private static final String EMAIL_DOMAIN_FAKE = "@email.com";

    private static final String EMAIL_ADDRESS_FAKE = "testemail";

    private static final String EMAIL_INVALID = "useratemaildotcom";

    private static final String USERNAME_VALID = "testusername";

    private static final String USERNAME_INVALID = "username%";

    private static final String LASTNAME_VALID = "Lastname";

    private static final String LASTNAME_INVALID = "Lastname";

    private static final String FIRSTNAME_VALID = "Firstname";

    private static final String FIRSTNAME_INVALID = "Firstname1";

    private RemoteWebDriver driver;

    private AddUserPage target;

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        target = new AddUserPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    /* Test that strings can be entered into input fields */
    @Test
    public void testFieldEntry() {
        target.setFirstName(FIRSTNAME_VALID);
        assertEquals(FIRSTNAME_VALID, target.getFirstName());
        target.setLastName(LASTNAME_VALID);
        assertEquals(LASTNAME_VALID, target.getLastName());
        target.setUserName(USERNAME_VALID);
        assertEquals(USERNAME_VALID, target.getUserName());
        target.setEmail(EMAIL_VALID_FAKE);
        assertEquals(EMAIL_VALID_FAKE, target.getEmail());
        target.setPassword(PASSWORD_VALID);
        assertEquals(PASSWORD_VALID, target.getPassword());
        target.setConfirmPassword(PASSWORD_VALID);
        assertEquals(PASSWORD_VALID, target.getConfirmPassword());
    }

    @Test
    public void testValidEntry() {
        // Generate random valid username
        String validUsername = USERNAME_VALID
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, true);
        // Generate random valid email
        String validFakeEmail = EMAIL_ADDRESS_FAKE
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, true)
                + EMAIL_DOMAIN_FAKE;
        // set valid forms
        target.setFirstName(FIRSTNAME_VALID);
        target.setLastName(LASTNAME_VALID);
        target.setUserName(validUsername);
        target.setEmail(validFakeEmail);
        target.setPassword(PASSWORD_VALID);
        target.setConfirmPassword(PASSWORD_VALID);
        target.submitRegisterForm();
        // Assert that the page was redirected to the success page
        assertTrue(target.registrationSuccessTitle());
    }

    /* Test invalid and valid passwords */
    @Test
    public void testInvalidPasswords() {
        // Add valid first name, last name, email, and username
        target.setFirstName(FIRSTNAME_VALID);
        target.setLastName(LASTNAME_VALID);
        target.setUserName(USERNAME_VALID);
        target.setEmail(EMAIL_VALID_FAKE);
        // Test invalid passwords
        target.setPassword(PASSWORD_INVALID_1);
        target.setConfirmPassword(PASSWORD_INVALID_1);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
        target.setPassword(PASSWORD_INVALID_2);
        target.setConfirmPassword(PASSWORD_INVALID_2);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
        target.setPassword(PASSWORD_INVALID_3);
        target.setConfirmPassword(PASSWORD_INVALID_3);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
    }

    /* Test valid and invalid username */
    @Test
    public void testInvalidUsername() {
        // Enter valid fields
        target.setFirstName(FIRSTNAME_VALID);
        target.setLastName(LASTNAME_VALID);
        target.setEmail(EMAIL_VALID_FAKE);
        target.setPassword(PASSWORD_VALID);
        target.setConfirmPassword(PASSWORD_VALID);
        // Enter invalid username, submit, and assert an error
        target.setUserName(USERNAME_INVALID);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
    }

    @Test
    public void testInvalidFirstName() {
        // Enter valid fields
        target.setLastName(LASTNAME_VALID);
        target.setUserName(USERNAME_INVALID);
        target.setEmail(EMAIL_VALID_FAKE);
        target.setPassword(PASSWORD_VALID);
        target.setConfirmPassword(PASSWORD_VALID);
        // Enter invalid first name, submit, and assert an error
        target.setFirstName(FIRSTNAME_INVALID);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
    }

    @Test
    public void testInvalidLastName() {
        // Enter valid fields
        target.setFirstName(FIRSTNAME_VALID);
        target.setUserName(USERNAME_INVALID);
        target.setEmail(EMAIL_VALID_FAKE);
        target.setPassword(PASSWORD_VALID);
        target.setConfirmPassword(PASSWORD_VALID);
        // Enter invalid last name, submit, and assert an error
        target.setLastName(LASTNAME_INVALID);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
    }

    @Test
    public void testInvalidEmail() {
        target.setFirstName(FIRSTNAME_VALID);
        target.setLastName(LASTNAME_VALID);
        target.setUserName(USERNAME_INVALID);
        target.setPassword(PASSWORD_VALID);
        target.setConfirmPassword(PASSWORD_VALID);
        target.setEmail(EMAIL_INVALID);
        target.submitRegisterForm();
        assertTrue(target.registerTitle());
    }
}
