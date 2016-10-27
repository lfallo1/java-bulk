package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.TimeManagementPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.TimeManagementPageSelenium;

public class TimeManagementTest {

    private static final String CANCEL_REDIRECT_TITLE = "Admin Splash";

    private static final String CLOCK_TIME_INVALID = "foo";

    private static final String CLOCK_OUT_VALID_1 = "4:30pm";

    private static final String CLOCK_IN_VALID_1 = "8:30am";

    private static final String ADMIN_SPLASH_TITLE = CANCEL_REDIRECT_TITLE;

    private TimeManagementPage target;

    private RemoteWebDriver driver;

    SeleniumHelper helper;

    private static final String TIME_MANAGEMENT_URL = "http://localhost:8000/timemanagement";

    private static final String TEST_USER_VISIBLE_TEXT_1 = "Gainsborough, Aerith";

    private static final String TEST_USERS_FIRST_WEEK = "Week of 11/9/2014 Until 11/15/2014";

    private static final String TEST_USERS_FIRST_WEEK_BEFORE = "Week of 11/2/2014 Until 11/8/2014";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
        target = new TimeManagementPageSelenium(driver);
        driver.navigate().to(TIME_MANAGEMENT_URL);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    // Test that the user is redirected to the admin splash page when
    @Test
    public void testCancelButton() {
        assertEquals(driver.getCurrentUrl(), TIME_MANAGEMENT_URL);
        target.clickCancel();
        assertTrue(helper.onTitle(driver, ADMIN_SPLASH_TITLE, 10L));
    }

    // When the admin clicks on the inactive button, the inactive users should
    // be visible in the user dropdown box
    @Test
    public void testInactiveButton() {
        assertFalse(target.getActiveShown());
        List<String> userListActive = target.userList();
        // Switch to showing active and inactive users
        target.toggleActiveShown();
        assertTrue(target.getActiveShown());
        // Assert the users in the dropdown box have changed
        assertNotEquals(userListActive, target.userList());
    }

    // When the admin clicks the user dropdown box and clicks on the test user,
    // the week dropdown box should only display the weeks that the user worked
    @Test
    public void testDropdownFunctionality() {
        // Select the test user
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        // The test user's first week was the week of 11/9
        assertTrue(target.weekPresent(TEST_USERS_FIRST_WEEK));
        assertFalse(target.weekPresent(TEST_USERS_FIRST_WEEK_BEFORE));
    }

    // When an admin validly updates a user's clock in and clock out time, the
    // user's
    // clock in and clock out time should be updated
    @Test
    public void testChangeTimeValid() {
        // Select the test user and first week
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        target.selectWeekByVisibleText(TEST_USERS_FIRST_WEEK);
        // Get the original clock in and clock out time
        String originalClockIn = target.getFirstClockIn();
        String originalClockOut = target.getFirstClockOut();
        // Change the clock in and clockout times to something valid
        target.setFirstClockIn(CLOCK_IN_VALID_1);
        target.setFirstClockOut(CLOCK_OUT_VALID_1);
        target.clickUpdate();
        // Refresh and re-select the user and week
        this.driver.navigate().refresh();
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        target.selectWeekByVisibleText(TEST_USERS_FIRST_WEEK);
        // Assert the time was updated properly
        assertEquals(CLOCK_IN_VALID_1, target.getFirstClockIn());
        assertEquals(CLOCK_OUT_VALID_1, target.getFirstClockOut());
        // Reset back to the original time
        target.setFirstClockIn(originalClockIn);
        target.setFirstClockOut(originalClockOut);
        target.clickUpdate();
    }

    // When an admin enters an invalid clock in and clock out time, the user's
    // clock in and clock out should not be updated
    @Test
    public void testChangeTimeInvalid() {
        // Select the test user and first week
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        target.selectWeekByVisibleText(TEST_USERS_FIRST_WEEK);
        // Get the original clock in and clock out time
        String originalClockIn = target.getFirstClockIn();
        String originalClockOut = target.getFirstClockOut();
        // Change the clock in and clock out times to something invalid
        target.setFirstClockIn(CLOCK_TIME_INVALID);
        target.setFirstClockOut(CLOCK_TIME_INVALID);
        target.clickUpdate();
        // Refresh the page and re-navigate to the user
        this.driver.navigate().refresh();
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        target.selectWeekByVisibleText(TEST_USERS_FIRST_WEEK);
        // Assert the clock in and clock out has not changed
        assertEquals(originalClockIn, target.getFirstClockIn());
        assertEquals(originalClockOut, target.getFirstClockOut());
    }

    // When an admin updates a clock date to have a clock in after a clock out,
    // the user's clock date should not be updated
    @Test
    public void testChangeTimeWrongOrder() {
        // Select the test user and first week
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        target.selectWeekByVisibleText(TEST_USERS_FIRST_WEEK);
        // Get the original clock in and clock out time
        String originalClockIn = target.getFirstClockIn();
        String originalClockOut = target.getFirstClockOut();
        // Change the clock in to something earlier than the clock out
        target.setFirstClockIn(CLOCK_OUT_VALID_1);
        target.setFirstClockOut(CLOCK_IN_VALID_1);
        target.clickUpdate();
        // Refresh the page and re-navigate to the user
        this.driver.navigate().refresh();
        target.selectUserByVisibleText(TEST_USER_VISIBLE_TEXT_1);
        target.selectWeekByVisibleText(TEST_USERS_FIRST_WEEK);
        // Assert the clock in and clock out has not changed
        assertEquals(originalClockIn, target.getFirstClockIn());
        assertEquals(originalClockOut, target.getFirstClockOut());
    }

    // When an admin clicks on the cancel button, they should be redirected to
    // the admin splash page
    @Test
    public void clickCancelTest() {
        target.clickCancel();
        assertTrue(helper.onTitle(driver, CANCEL_REDIRECT_TITLE, 10L));
    }
}
