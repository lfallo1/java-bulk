package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.AdminReviewPage;

public class AdminReviewPageSelenium implements AdminReviewPage {

    private RemoteWebDriver driver;
    private WebDriverWait   waitDriver;

    public AdminReviewPageSelenium(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("http://localhost:8000");
    }

    @Override
    public String getId() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("reportId")));
        return driver.findElement(By.id("reportId")).getAttribute("value");
    }

    @Override
    public void setId(String id) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("reportId")));
        driver.findElementById("reportId").clear();
        driver.findElementById("reportId").sendKeys(id);
    }

    @Override
    public String getFirstName() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById("firstName")));
        return driver.findElement(By.id("firstName")).getAttribute("value");
    }

    @Override
    public void setFirstName(String firstName) {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById("firstName")));
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys(firstName);
    }

    @Override
    public String getLastName() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("lastName")));
        return driver.findElement(By.id("lastName")).getAttribute("value");
    }

    @Override
    public void setLastName(String lastName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("lastName")));
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys(lastName);
    }

    @Override
    public String getDate() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("date")));
        return driver.findElementById("date").getAttribute("value");
    }

    @Override
    public void setDate(String date) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("date")));
        driver.findElementById("date").clear();
        driver.findElementById("date").sendKeys(date);
    }

    @Override
    public Boolean getStatus() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("active")));
        Select select = new Select(driver.findElementById("active"));
        if ("true"
                .equals(select.getFirstSelectedOption().getAttribute("value"))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setStatus(Boolean isEnabled) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("active")));
        Select select = new Select(driver.findElementById("active"));
        if (isEnabled) {
            select.selectByValue("true");
        } else {
            select.selectByValue("false");
        }
    }

    @Override
    public void clickSearch() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById("search")));
        driver.findElementById("search").click();
    }

    @Override
    public List<WebElement> getSearchResults() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("searchResults")));
        return driver.findElementsByClassName("col-lg-12");
    }

    @Override
    public void clickReport(int rowNumber) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("searchResults")));
        List<WebElement> table = driver.findElementById("searchResults")
                .findElements(By.className("col-lg-12"));
        table.get(rowNumber).findElement(By.className("link")).click();
    }

    @Override
    public void clickDisable(int rowNumber) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("searchResults")));
        List<WebElement> table = driver.findElementById("searchResults")
                .findElements(By.className("col-lg-12"));
        table.get(rowNumber).findElement(By.className("enabler")).click();
    }

    @Override
    public void clickLogout() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .className("navbar-right"))));
        driver.findElement(By.className("navbar-right")).click();
    }

    @Override
    public Boolean overlayIsDisplayed() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .className("navbar-right"))));
        if (driver.findElement(By.id("overlay")).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clickUpdate() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("formSubmit"))));
        driver.findElement(By.id("formSubmit")).click();
    }

    @Override
    public void clickCancel() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id("back"))));
        driver.findElement(By.id("back")).click();
    }

    @Override
    public void setApproverComments(String comments) {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElement(By.id("comments"))));
        driver.findElement(By.id("comments")).clear();
        driver.findElement(By.id("comments")).sendKeys(comments);
    }

    @Override
    public String getApproverComments() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElement(By.id("comments"))));
        return driver.findElement(By.id("comments")).getAttribute("value");
    }
}
