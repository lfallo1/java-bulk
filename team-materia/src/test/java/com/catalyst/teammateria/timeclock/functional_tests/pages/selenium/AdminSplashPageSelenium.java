package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.SeleniumHelper;
import com.catalyst.teammateria.timeclock.functional_tests.pages.AdminSplashPage;

public class AdminSplashPageSelenium implements AdminSplashPage {

    private RemoteWebDriver driver;

    private WebDriverWait waitDriver;

    private static final String ADMIN_SPLASH_TITLE = "Admin Splash";

    public AdminSplashPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        SeleniumHelper helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
    }

    @Override
    public boolean adminSplashTitle() {
        waitDriver.until(ExpectedConditions.titleContains(ADMIN_SPLASH_TITLE));
        if (ADMIN_SPLASH_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean accountConfigLinkPresent() {
        int numAccountConfigLink = driver.findElements(
                By.id("account-config-link")).size();
        if (numAccountConfigLink == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean timeManagementLinkPresent() {        
        int numTimeManagementLink = driver.findElements(
                By.id("time-management-link")).size();
        if (numTimeManagementLink == 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean reportingLinkPresent() {
        int numReportingLink = driver.findElements(By.id("reporting-link"))
                .size();
        if (numReportingLink == 1) {
            return true;
        }
        return false;
    }
}
