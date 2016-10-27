package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.LoginLockoutPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.LoginLockoutPageSelenium;

/**
 * Functional test to lock out users
 * 
 * @author nPoloway
 */
public class LockoutTest {

    private static final String VALID_USERNAME = "test";

    private static final String INVALID_USERNAME = "wrong";

    private static final String INVALID_PASSWORD_A = "nope";

    private static final String INVALID_PASSWORD_B = "try";

    private static final String INVALID_PASSWORD_C = "again";

    private RemoteWebDriver driver;

    private LoginLockoutPage target;

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        target = new LoginLockoutPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    // Test a username with account, attempt login with wrong passwords
    @Test
    public void failValidUserTest() {
        target.setUsername(VALID_USERNAME);
        target.setUserPassword(INVALID_PASSWORD_C);
        target.submitLogin();
        assertTrue(target.loginTitle());
        target.clearFields();
        target.setUsername(VALID_USERNAME);
        target.setUserPassword(INVALID_PASSWORD_B);
        target.submitLogin();
        assertTrue(target.loginTitle());
        target.clearFields();
        target.setUsername(VALID_USERNAME);
        target.setUserPassword(INVALID_PASSWORD_A);
        target.submitLogin();
        // Third submit with invalid password from the same username will
        // lock out user
        assertTrue(target.loginTitle());
        target.clearFields();
    }

    // Test a non-existent username, attempt login without account and with
    // wrong passwords
    @Test
    public void failInvalidUserTest() {
        target.setUsername(INVALID_USERNAME);
        target.setUserPassword(INVALID_PASSWORD_A);
        target.submitLogin();
        assertTrue(target.loginTitle());
        target.clearFields();
        target.setUsername(INVALID_USERNAME);
        target.setUserPassword(INVALID_PASSWORD_B);
        target.submitLogin();
        assertTrue(target.loginTitle());
        target.clearFields();
        target.setUsername(INVALID_USERNAME);
        target.setUserPassword(INVALID_PASSWORD_C);
        target.submitLogin();
        // Third submit from both invalid passwords and non-existent username
        // will lock out user
        assertTrue(target.loginTitle());
        target.clearFields();
    }
}
