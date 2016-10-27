package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.LoginPage;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.LoginPageSelenium;

/**
 * Functional tests for the login page
 * 
 * @author nPoloway
 */
public class LoginPageTest {

    private RemoteWebDriver     driver;
    private LoginPage           target;
    private final static String INVALID_CREDENTIAL = "invalid";
    private final static String ADMIN_USERNAME     = "testUser1";
    private final static String USER_USERNAME      = "testUser2";
    private static final String VALID_PASSWORD     = "Password1?";
    private static final String EMPTY              = "";

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        target = new LoginPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Ignore
    public void testErrorMessage() {
        // Assert error message appears
        target.submitLogin();
        assertNotEquals(target.getErrorMessage(), EMPTY);
    }

    @Ignore
    public void testUsernameOperability() {
        // Check that password textbox is empty
        assertEquals(target.getPassword(), EMPTY);
        // Fill with bad data
        target.setPassword(INVALID_CREDENTIAL);
        // Check that password textbox is empty
        assertEquals(target.getUsername(), EMPTY);
        // FILL IT WITH TRASH
        target.setUsername(INVALID_CREDENTIAL);
        // Assert that textboxes contain specified values
        assertEquals(target.getPassword(), INVALID_CREDENTIAL);
        assertEquals(INVALID_CREDENTIAL, target.getUsername());
        // Store current checkbox value then change it
        Boolean currentRemember = target.isRemembered();
        target.toggleRememberMe();
        // Assert checkbox was clicked
        assertNotEquals(currentRemember, target.isRemembered());
    }

    @Ignore
    public void requireFieldsTest() {
        // Assert error message appears when submit is attempted without fields
        assertEquals(target.getPassword(), EMPTY);
        assertEquals(target.getUsername(), EMPTY);
        target.submitLogin();
        assertNotNull(target.getErrorMessage());
    }

    @Ignore
    public void testInvalidUsernameAndPassword() {
        // Assert error message appears if user credentials are invalid
        target.setUsername(INVALID_CREDENTIAL);
        target.setPassword(INVALID_CREDENTIAL);
        target.submitLogin();
        assertNotNull(target.getErrorMessage());
    }

    @Ignore
    public void testInvalidUsername() {
        // Assert username must be on record
        target.setUsername(INVALID_CREDENTIAL);
        target.setPassword(VALID_PASSWORD);
        target.submitLogin();
        assertNotNull(target.getErrorMessage());
    }

    @Ignore
    public void testInvalidPassword() {
        // Assert password must match username
        target.setUsername(ADMIN_USERNAME);
        target.setPassword(INVALID_CREDENTIAL);
        target.submitLogin();
        assertNotNull(target.getErrorMessage());
    }

    @Ignore
    public void adminLoginSuccessTest() {
        // Assert successful admin login will redirect
        target.setUsername(ADMIN_USERNAME);
        target.setPassword(VALID_PASSWORD);
        target.submitLogin();
        assertFalse(target.loginTitle());
    }

    @Ignore
    public void userLoginSuccessTest() {
        // Assert successful user login will redirect
        target.setUsername(USER_USERNAME);
        target.setPassword(VALID_PASSWORD);
        target.submitLogin();
        assertFalse(target.loginTitle());
    }
}
