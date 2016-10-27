package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.AdminLogouts;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.AdminLogoutsSelenium;

public class AdminLogoutsTest {

    private RemoteWebDriver     driver;
    private SeleniumHelper      helper;
    private AdminLogouts        target;
    private static final String SPLASH_URL = "http://localhost:8000/";
    private static final String LOGOUT_URL = "http://localhost:8000/logout";
    private static final String REPORT_URL = "http://localhost:8000/adminreport";
    private static final String REVIEW_URL = "http://localhost:8000/adminreview";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
        target = new AdminLogoutsSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Ignore
    public void splashLogoutTest() {
        assertEquals(driver.getCurrentUrl(), SPLASH_URL);
        target.clickLogout();
        assertEquals(driver.getCurrentUrl(), LOGOUT_URL);
    }

    @Ignore
    public void reportLogoutTest() {
        target.clickReport();
        assertEquals(driver.getCurrentUrl(), REPORT_URL);
        target.clickLogout();
        assertEquals(driver.getCurrentUrl(), LOGOUT_URL);
    }

    @Ignore
    public void reviewLogoutTest() {
        target.clickReview();
        assertEquals(driver.getCurrentUrl(), REVIEW_URL);
        target.clickLogout();
        assertEquals(driver.getCurrentUrl(), LOGOUT_URL);
    }
}
