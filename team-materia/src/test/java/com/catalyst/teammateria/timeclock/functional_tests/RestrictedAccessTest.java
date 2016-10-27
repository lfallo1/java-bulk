package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.LoginPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.LoginPageSelenium;

public class RestrictedAccessTest {

    private static final String TIME_MANAGEMENT_URL = "/timemanagement";
    private static final String ACCOUNT_CONFIG_URL  = "/accountconfig";
    private LoginPage           target;
    private RemoteWebDriver     driver;
    // private final static String TIME_TRACKING_TITLE = "Time Tracking";
    private final static String BASE_URL            = "localhost:8000";
    private final static String ADMIN_SPLASH_URL    = "/adminsplash";

    /*
     * Add following variables when we have admin pages
     */
    // private final static String ACCOUNT_CONFIG_URL =
    // "localhost:8000/accountconfig";
    // private final static String TIME_MANAGEMENT_URL =
    // "localhost:8000/timeManagement";
    // private final static String REPORTING_URL = "localhost:8000/reporting";
    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        SeleniumHelper helper = new SeleniumHelper();
        helper.loginDriverUser(driver);
        target = new LoginPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void testSplashRestriction() {
        // Try to navigate to admin splash page
        driver.navigate().to(BASE_URL + ADMIN_SPLASH_URL);
        // Assert page hasn't changed
        assertTrue(target.timeTrackingTitle());
        driver.navigate().to(BASE_URL + ACCOUNT_CONFIG_URL);
        assertTrue(target.timeTrackingTitle());
        driver.navigate().to(BASE_URL + TIME_MANAGEMENT_URL);
        assertTrue(target.timeTrackingTitle());
    }
}
