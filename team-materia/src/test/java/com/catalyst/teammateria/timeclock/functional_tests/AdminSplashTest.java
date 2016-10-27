package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.AdminSplashPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.AdminSplashPageSelenium;

public class AdminSplashTest {

    private RemoteWebDriver driver;

    private AdminSplashPage target;

    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        target = new AdminSplashPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void testLoad() {
        assertTrue(target.adminSplashTitle());
    }

    @Test
    public void testLinkPresence() {
        assertTrue(target.accountConfigLinkPresent());
        assertTrue(target.timeManagementLinkPresent());
        assertTrue(target.reportingLinkPresent());
    }
}
