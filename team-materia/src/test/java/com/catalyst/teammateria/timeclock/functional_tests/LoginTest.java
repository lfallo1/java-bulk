package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.LoginPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.LoginPageSelenium;

/**
 * Functional test for the login page
 * 
 * @author gPorter
 */
public class LoginTest {

    private RemoteWebDriver driver;

    private LoginPage target;

    private final static String USERNAME_VALID_1 = "test1";

    private final static String PASSWORD_VALID_1 = "Password1?";

    private final static String USERNAME_VALID_2 = "test2";

    private final static String PASSWORD_VALID_2 = "Password2?";
    
    private final static String USERNAME_VALID_3 = "test3";

    private final static String ADMIN_USERNAME_VALID = "admin1";

    private final static String ADMIN_PASSWORD_VALID = "Password3?";

    private final static String USERNAME_INVALID_1 = "TestInvalidUsername";

    private final static String PASSWORD_INVALID_1 = "invalid";

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        target = new LoginPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    // Test the first matching and valid non-admin user name and password
    @Test
    public void validLoginTest1() {
        // Enter the first matching valid username and password
        target.setUsername(USERNAME_VALID_1);
        target.setUserPassword(PASSWORD_VALID_1);
        target.submitLogin();
        // Assert that the user has been redirected to the timetracking page
        assertTrue(target.timeTrackingTitle());
    }

    // Test the second matching valid non-admin user name and password
    @Test
    public void validLoginTest2() {
        // Enter the second matching valid username and password
        target.setUsername(USERNAME_VALID_2);
        target.setUserPassword(PASSWORD_VALID_2);
        target.submitLogin();
        // Assert that the user has been redirected to the timetracking page
        assertTrue(target.timeTrackingTitle());
    }

    // Test a mismatching but valid user name and password
    @Test
    public void validMismatchLoginTest() {
        // Enter a valid but mismatching username and password
        target.setUsername(USERNAME_VALID_1);
        target.setUserPassword(PASSWORD_VALID_2);
        target.submitLogin();
        // Assert that the login failed
        assertTrue(target.loginTitle());
    }

    // Test an invalid username and password
    @Test
    public void invalidLoginTest() {
        // Enter an invalid username and invalid password
        target.setUsername(USERNAME_INVALID_1);
        target.setUserPassword(PASSWORD_INVALID_1);
        target.submitLogin();
        // Assert that the login failed
        assertTrue(target.loginTitle());
    }

    // Test that a valid admin username and password redirects to the admin
    // splash page
    @Test
    public void validAdminLoginTest() {
        // Enter a valid and matching admin username and admin password
        target.setUsername(ADMIN_USERNAME_VALID);
        target.setUserPassword(ADMIN_PASSWORD_VALID);
        target.submitLogin();
        // Assert the administrator was redirected to the admin splash page
        assertTrue(target.adminSplashPageTitle());
    }

    // Test that a valid admin username and invalid password results in a login
    // failure
    @Test
    public void invalidAdminLoginTest1() {
        // Enter a valid admin username and invalid password
        target.setUsername(ADMIN_USERNAME_VALID);
        target.setUserPassword(PASSWORD_INVALID_1);
        target.submitLogin();
        // Assert that the login failed
        assertTrue(target.loginTitle());
    }

    // Test that a invalid admin username and valid admin password results in a
    // login failure
    @Test
    public void invalidAdminLoginTest2() {
        // Enter an invalid admin username and valid admin password
        target.setUsername(USERNAME_VALID_3);
        target.setUserPassword(ADMIN_PASSWORD_VALID);
        target.submitLogin();
        // Assert that the login failed
        assertTrue(target.loginTitle());
    }
}
