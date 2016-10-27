package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.AccountConfigPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.LoginPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.AccountConfigPageSelenium;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.LoginPageSelenium;

public class AccountConfigTest {

    private static final String BASE_URL                  = "http://localhost:8000/accountconfig";
    private static final String TEST_USER_PASSWORD_2      = "Password2?";
    private static final String TEST_USER_PASSWORD_1      = "Password1?";
    private static final String AMIN_ROLE_VISIBLE_TEXT    = "Admin";
    private static final String PASSWORD_TEST             = "testPassword1";
    private static final String USERNAME_TEST             = "testSeleniumUsername";
    private static final String LASTNAME_TEST             = "Lastnamexyz";
    private static final String FIRSTNAME_TEST            = "Firstnamexyz";
    private static final String EMAIL_TEST                = "testemail123@email.com";
    private static final String TEST_USER_VISIBLE_TEXT_1  = "Gainsborough, Aerith";
    private static final String TEST_USER_FIRST_NAME      = "Aerith";
    private static final String TEST_USER_LAST_NAME       = "Gainsborough";
    private static final String TEST_USER_USERNAME        = "test4";
    private static final String TEST_USER_EMAIL           = "test4@email.com";
    private static final String TEST_USER_ROLE            = "User";
    private static final String DEFAULT_USER_VISIBLE_TEXT = "SelectOne";
    private static final String EMPTY_STRING              = "";
    private static final String USER_ROLE_VISIBLE_TEXT    = "User";
    private static final int    RANDOM_STRING_LENGTH      = 3;
    private static final String USERNAME_VALID            = "testusername";
    private AccountConfigPage   target;
    private RemoteWebDriver     driver;
    SeleniumHelper              helper;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
        target = new AccountConfigPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    // Test that the default values of the input fields are correct
    @Test
    public void testDefaultValues() {
        // Assert that correctly navigated to the account configuration page
        assertTrue(target.accountConfigTitle());
        // Assert that the input fields have their default values
        assertEquals(DEFAULT_USER_VISIBLE_TEXT, target.getUser());
        assertEquals(EMPTY_STRING, target.getFirstName());
        assertEquals(EMPTY_STRING, target.getLastName());
        assertEquals(EMPTY_STRING, target.getUsername());
        assertEquals(EMPTY_STRING, target.getEmail());
        assertEquals(EMPTY_STRING, target.getPassword());
        assertEquals(USER_ROLE_VISIBLE_TEXT, target.getRole());
    }

    // Test that the fields properly receive input
    @Test
    public void testInput() {
        // Test that the user select dropdown box receives input properly
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        assertEquals(TEST_USER_VISIBLE_TEXT_1, target.getUser());
        // Assert that input fields receive input properly
        target.setFirstName(FIRSTNAME_TEST);
        assertEquals(FIRSTNAME_TEST, target.getFirstName());
        target.setLastName(LASTNAME_TEST);
        assertEquals(LASTNAME_TEST, target.getLastName());
        target.setUserName(USERNAME_TEST);
        assertEquals(USERNAME_TEST, target.getUsername());
        target.setEmail(EMAIL_TEST);
        assertEquals(EMAIL_TEST, target.getEmail());
        target.setPassword(PASSWORD_TEST);
        assertEquals(PASSWORD_TEST, target.getPassword());
        // Select active radio button, assert that it is selected and the
        // inactive radio button is not selected
        target.selectActive();
        assertTrue(target.getActive());
        assertFalse(target.getInactive());
        // Select the inactive radio button, assert that it is selected and the
        // active radio button is not selected
        target.selectInactive();
        assertTrue(target.getInactive());
        assertFalse(target.getActive());
    }

    // When the admin navigates to the test user, they should see the correct
    // information for that user
    @Test
    public void testUserInfoDisplay() {
        // Select the test user
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        // Assert that the correct test user information is displayed
        assertEquals(TEST_USER_FIRST_NAME, target.getFirstName());
        assertEquals(TEST_USER_LAST_NAME, target.getLastName());
        assertEquals(TEST_USER_USERNAME, target.getUsername());
        assertEquals(TEST_USER_EMAIL, target.getEmail());
        assertEquals(TEST_USER_ROLE, target.getRole());
    }

    // When the admin updates the user info for the test user, the updated
    // information should be displayed
    @Test
    public void testUpdateUserInfo() {
        // Change the user information fields to valid fields
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        target.setFirstName(FIRSTNAME_TEST);
        target.setLastName(LASTNAME_TEST);
        target.setUserName(USERNAME_TEST);
        target.setEmail(EMAIL_TEST);
        target.selectActive();
        // Update the user and confirm the user
        target.clickUpdate();
        target.confirmAlert();
        driver.navigate().refresh();
        // Select the updated user, assert
        target.setUser(LASTNAME_TEST + ", " + FIRSTNAME_TEST);
        assertEquals(FIRSTNAME_TEST, target.getFirstName());
        assertEquals(LASTNAME_TEST, target.getLastName());
        assertEquals(USERNAME_TEST, target.getUsername());
        assertEquals(EMAIL_TEST, target.getEmail());
        // Revert the changes made to the test user
        target.setFirstName(TEST_USER_FIRST_NAME);
        target.setLastName(TEST_USER_LAST_NAME);
        target.setUserName(TEST_USER_USERNAME);
        target.setEmail(TEST_USER_EMAIL);
        target.selectActive();
        target.clickUpdate();
        target.confirmAlert();
    }

    // When the add user button is clicked and valid user information is entered
    // and submitted, the new user should be able to log in
    @Test
    public void addValidUserTest() {
        // Generate random valid user information
        target.clickAddUser();
        String firstNameRandom = FIRSTNAME_TEST
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, false);
        String lastNameRandom = LASTNAME_TEST
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, false);
        String validUsername = USERNAME_VALID
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, true);
        String validFakeEmail = RandomStringUtils.random(RANDOM_STRING_LENGTH,
                true, true) + "@email.com";
        String validPassword = "Password"
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, true);
        // Add the valid random user information to the form and submit
        target.setFirstName(firstNameRandom);
        target.setLastName(lastNameRandom);
        target.setUserName(validUsername);
        target.setEmail(validFakeEmail);
        target.setPassword(validPassword);
        target.clickUpdate();
        // Confirm the alert and logout
        target.confirmAlert();
        helper.clickLogout(driver);
        // Login using the added user, assert that redirected to timetracking
        // page
        helper.loginUser(driver, validUsername, validPassword);
        LoginPage loginPage = new LoginPageSelenium(driver);
        assertTrue(loginPage.timeTrackingTitle());
    }

    // When trying to add a user with invalid user credentials, the user should
    // not be created
    @Test
    public void addInvalidUserTest() {
        // Switch to add user...
        target.clickAddUser();
        // Enter a valid first and last name...
        target.setFirstName(FIRSTNAME_TEST);
        target.setLastName(LASTNAME_TEST);
        // Enter a valid and random email...
        String validFakeEmail = RandomStringUtils.random(RANDOM_STRING_LENGTH,
                true, true) + "@email.com";
        target.setEmail(validFakeEmail);
        // Enter a valid and random password...
        String validPassword = "Password"
                + RandomStringUtils.random(RANDOM_STRING_LENGTH, true, true);
        target.setPassword(validPassword);
        // Enter a valid but already used username
        target.setUserName(TEST_USER_USERNAME);
        // Attempt to add the user and log out
        target.clickUpdate();
        assertTrue(target.accountConfigTitle());
        helper.clickLogout(driver);
        // Attempt to login using the failed added user, assert that it was not
        // successful
        helper.loginUser(driver, TEST_USER_USERNAME, validPassword);
        LoginPage loginPage = new LoginPageSelenium(driver);
        assertTrue(loginPage.loginTitle());
    }

    // Test that the proper UI changes occur when switching to adding a user
    @Test
    public void clickAddUserTest() {
        assertTrue(target.userDropdownVisible());
        // Click on the add user button
        target.clickAddUser();
        // Assert that the Update button changed to an Add button
        assertEquals("Add", target.getUpdateButtonVisibleText());
    }

    // Test that the active status of a user can be changed
    @Test
    public void checkActiveChangeTest() {
        // Assert that the active test user is present when navigating
        assertTrue(target.userPresent(TEST_USER_VISIBLE_TEXT_1));
        // Change the test user to an inactive user
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        target.selectInactive();
        target.clickUpdate();
        target.confirmAlert();
        driver.navigate().refresh();
        // Refresh the page, assert the inactive user is not visible
        assertFalse(target.userPresent(TEST_USER_VISIBLE_TEXT_1));
        // Show inactive user, assert the inactive user is visible
        target.clickInactiveButton();
        assertTrue(target.userPresent(TEST_USER_VISIBLE_TEXT_1));
        // Change the user back to active and refresh
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        target.selectActive();
        target.clickUpdate();
        target.confirmAlert();
        driver.navigate().refresh();
        // Assert that the active user is visible again
        assertTrue(target.userPresent(TEST_USER_VISIBLE_TEXT_1));
    }

    // Test that a user can be given an admin role
    @Test
    public void checkUserRoleChangeTest() {
        // Change a test user to an admin
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        target.setRole(AMIN_ROLE_VISIBLE_TEXT);
        target.clickUpdate();
        target.confirmAlert();
        driver.navigate().refresh();
        // Assert that the user is now an admin
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        assertEquals(AMIN_ROLE_VISIBLE_TEXT, target.getRole());
        // Change the test user back to a regular user
        target.setRole(USER_ROLE_VISIBLE_TEXT);
        target.clickUpdate();
        target.confirmAlert();
        driver.navigate().refresh();
        // Assert that the test user now is back to a regular user.
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        assertEquals(USER_ROLE_VISIBLE_TEXT, target.getRole());
    }

    // Test that an admin can change the password of a user
    @Test
    public void passwordChangeTest() {
        // Change the test user's password
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        target.setPassword(TEST_USER_PASSWORD_2);
        target.clickUpdate();
        target.confirmAlert();
        // Logout
        helper.clickLogout(driver);
        // Log back in as the test user using the new password, assert that
        // login success occurred using the selenium implementation for the
        // login page
        LoginPage loginPage = new LoginPageSelenium(driver);
        helper.loginUser(driver, TEST_USER_USERNAME, TEST_USER_PASSWORD_2);
        // Assert that the password successfully changed
        assertTrue(loginPage.timeTrackingTitle());
        // Logout and log back in as the admin
        helper.clickLogout(driver);
        helper.loginDriverAdmin(driver);
        driver.get(BASE_URL);
        driver.navigate().refresh();
        // Change the password back to the original
        target.setUser(TEST_USER_VISIBLE_TEXT_1);
        target.setPassword(TEST_USER_PASSWORD_1);
        target.clickUpdate();
        target.confirmAlert();
    }
}
