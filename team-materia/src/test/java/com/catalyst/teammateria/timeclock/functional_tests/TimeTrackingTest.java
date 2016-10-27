package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.TimeTrackingPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.TimeTrackingPageSelenium;

public class TimeTrackingTest {

    private static final String CLOCK_OUT_VISIBLE_TEXT = "Clock Out";

    private static final String CLOCK_IN_VISIBLE_TEXT = "Clock In";

    private static final String CLOCK_IN_VALUE = CLOCK_IN_VISIBLE_TEXT;
    
    private static final String CLOCK_OUT_VALUE = CLOCK_OUT_VISIBLE_TEXT;

    private RemoteWebDriver driver;

    private TimeTrackingPage target;

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        SeleniumHelper helper = new SeleniumHelper();
        helper.loginDriverUser(driver);
        target = new TimeTrackingPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    

    /* Tests previous button is disabled when earliest week is displayed */
    @Test
    public void testPreviousDisable() {
        while (target.previousEnabled() == true) {
            assertTrue(target.previousEnabled());
            target.previousWeek();
        }
        assertFalse(target.previousEnabled());
    }

    // Tests next button exists and can be clicked 
    @Test
    public void testNextLink() {
        target.previousWeek();
        String currentDate = target.getWeekBegin();
        target.nextWeek();        
        assertNotEquals(target.getWeekBegin(), currentDate);
    }
    
    // Test previous button exists and can be clicked 
    @Test
    public void testPreviousLink() {
        String currentDate = target.getWeekBegin();
        target.previousWeek();        
        assertNotEquals(target.getWeekBegin(), currentDate);
    }

    /* Tests next button is disabled when current week is displayed */
    @Test
    public void testNextDisable() {
        while (target.nextEnabled() == true) {
            assertTrue(target.nextEnabled());
            target.nextWeek();
        }
        assertFalse(target.nextEnabled());
    }

    /* Tests clock in button exists and clocks in a user */
    @Test
    public void testClockIn() {
        target.clockIn();
        assertEquals(CLOCK_OUT_VISIBLE_TEXT, target.getButtonValue());
    }

    /* Tests clock out button exists and clocks out user */
    @Test
    public void testClockOut() {
        target.clockOut();
        assertEquals(CLOCK_IN_VISIBLE_TEXT, target.getButtonValue());
    }

    /* Tests that values for hours logged are being displayed to the page */
    @Test
    public void testHoursDisplay() {
        Float hours = target.getHours();
        assertNotNull(hours);
    }

    /* Tests that hours logged will change after a clocked in user clocks out */
    @Test
    public void testHoursChanged() {
        float start = target.getHours();
        target.clockIn();
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            assert false;
        }
        target.clockOut();
        driver.navigate().refresh();
        Float end = target.getHours();
        assertFalse(start == end);
    }
    
    /*
     * When the user clicks in, and logs out. When the user logs back in, they
     * should still be clocked in and capable of clocking out
     */
    @Test
    public void testClockinAndLogout(){
        // Clock in
        target.clockIn();
        // Logout and log back in
        SeleniumHelper helper = new SeleniumHelper();
        helper.clickLogout(driver);
        helper.loginDriverUser(driver);        
        // Assert that the clock out button is active
        assertEquals(target.getButtonValue(), CLOCK_OUT_VALUE);
        // Clock out and assert that the clock in button is active        
        target.clockOut();        
        assertEquals(target.getButtonValue(), CLOCK_IN_VALUE);
    }
    
    @Test 
    public void testClockoutAndLogout(){
        // Clock in and out
        target.clockIn();
        target.clockOut();
        // Logout
        SeleniumHelper helper = new SeleniumHelper();
        helper.clickLogout(driver);
        // Log back in
        helper.loginDriverUser(driver);
        // Assert that the clock in button is active
        String buttonValue = target.getButtonValue();
        assertEquals(buttonValue, CLOCK_IN_VALUE);
    }
    
}
