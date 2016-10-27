package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.AdminLogouts;

public class AdminLogoutsSelenium implements AdminLogouts {

    private RemoteWebDriver     driver;
    private WebDriverWait       waitDriver;
    private static final String URL     = "http://localhost:8000";
    private static final String LOGOUT  = "Logout";
    private static final String REPORTS = "reports";
    private static final String REVIEW  = "review";

    public AdminLogoutsSelenium(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(URL);
    }

    @Override
    public void clickLogout() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementByLinkText(LOGOUT)));
        driver.findElementByLinkText(LOGOUT).click();
    }

    @Override
    public void clickReport() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id(REPORTS))));
        driver.findElement(By.id(REPORTS)).click();
    }

    @Override
    public void clickReview() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id(REVIEW))));
        driver.findElement(By.id(REVIEW)).click();
    }
}
