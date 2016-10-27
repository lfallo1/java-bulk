package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.InjuryReportingPage;

public class InjuryReportingPageSelenium implements InjuryReportingPage {

    private static final String CONFIRMATION_TITLE = "Confirmation";

    private static final String DATE_OF_INJURY_ID = "dateOfInjury";

    private static final String DATE_REPORTED_TO_MANAGER_ID = "dateOfInjury";

    private WebDriver driver;

    private WebDriverWait waitDriver;

    public InjuryReportingPageSelenium(WebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://localhost:8000/injuryreporting?employeeId=2&firstName=test&lastName=userone");
    }

    @Override
    public void setDateOfInjury(String dateOfInjury) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id(DATE_OF_INJURY_ID))));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("$('#" + DATE_OF_INJURY_ID + "').val('" + dateOfInjury
                + "');");
    }

    @Override
    public String getDateOfInjury() {
        WebElement element = driver.findElement(By.id(DATE_OF_INJURY_ID));
        return element.getAttribute("value");
    }

    @Override
    public void setDateReportedToManager(String dateReportedToManager) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id(DATE_REPORTED_TO_MANAGER_ID))));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("$('#" + DATE_REPORTED_TO_MANAGER_ID + "').val('"
                + dateReportedToManager + "');");
    }

    @Override
    public String getDateReportedToManager() {
        WebElement element = driver.findElement(By
                .id(DATE_REPORTED_TO_MANAGER_ID));
        return element.getAttribute("value");
    }

    @Override
    public void setWeather(String weatherVisibleText) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("weatherSelect"))));
        Select select = new Select(driver.findElement(By.id("weatherSelect")));
        select.selectByVisibleText(weatherVisibleText);
    }

    @Override
    public String getWeather() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("weatherSelect"))));
        Select select = new Select(driver.findElement(By.id("weatherSelect")));
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void setInjuryType(String injuryType) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("injuryTypeSelect"))));
        Select select = new Select(
                driver.findElement(By.id("injuryTypeSelect")));
        select.selectByVisibleText(injuryType);
    }

    @Override
    public String getInjuryType() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("injuryTypeSelect"))));
        Select select = new Select(
                driver.findElement(By.id("injuryTypeSelect")));
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void setBodyPart(String bodyPart) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("bodyPartSelect"))));
        Select select = new Select(driver.findElement(By.id("bodyPartSelect")));
        select.selectByVisibleText(bodyPart);
    }

    @Override
    public String getBodyPart() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("bodyPartSelect"))));
        Select select = new Select(driver.findElement(By.id("bodyPartSelect")));
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void setTimeOfInjury(String timeOfInjury) {
        this.waitDriver.until(
                ExpectedConditions.elementToBeClickable(this.driver
                        .findElement(By.id("timepicker")))).sendKeys(
                timeOfInjury);
    }

    @Override
    public String getTimeOfInjury() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("timepicker"))));
        return driver.findElement(By.id("timepicker")).getAttribute("value");
    }

    @Override
    public void setDescription(String description) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("description"))));
        driver.findElement(By.id("description")).sendKeys(description);
    }

    @Override
    public String getDescription() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("description"))));
        return driver.findElement(By.id("description")).getAttribute("value");
    }

    @Override
    public void clickNext() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id("next"))));
        driver.findElement(By.id("next")).click();
    }

    @Override
    public boolean injuryReportVisible() {
        if (driver.findElement(By.id("injuryReport")).isDisplayed()) {
            return true;
        }
        return false;
    }

    @Override
    public String getReportEmployeeName() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("injuredEmployee"))));
        return driver.findElement(By.id("injuredEmployee")).getText();
    }

    @Override
    public String getReportEmployeeId() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("employeeId"))));
        return driver.findElement(By.id("employeeId")).getText();
    }

    @Override
    public String getReportUser() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("injuryReporter"))));
        return driver.findElement(By.id("injuryReporter")).getText();
    }

    @Override
    public String getReportUserId() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("reporterId"))));
        return driver.findElement(By.id("reporterId")).getText();
    }

    @Override
    public String getReportDateOfInjury() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("injuryDate"))));
        return driver.findElement(By.id("injuryDate")).getText();
    }

    @Override
    public String getReportDateReported() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("reportedDate"))));
        return driver.findElement(By.id("reportedDate")).getText();
    }

    @Override
    public String getReportDateFiled() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("filedDate"))));
        return driver.findElement(By.id("filedDate")).getText();
    }

    @Override
    public String getReportWeatherConditions() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("weatherCondition"))));
        return driver.findElement(By.id("weatherCondition")).getText();
    }

    @Override
    public String getReportTypeOfInjury() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("typeOfInjury"))));
        return driver.findElement(By.id("typeOfInjury")).getText();
    }

    @Override
    public String getReportBodyPart() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("bodyPartAffected"))));
        return driver.findElement(By.id("bodyPartAffected")).getText();
    }

    @Override
    public String getReportDescription() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("reportDescription"))));
        return driver.findElement(By.id("reportDescription")).getText();
    }

    @Override
    public String getReportTimeOfInjury() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id("injuryTime"))));
        return driver.findElement(By.id("injuryTime")).getText();
    }

    @Override
    public boolean onConfirmationPage() {
        this.waitDriver.until(ExpectedConditions
                .titleContains(CONFIRMATION_TITLE));
        if (CONFIRMATION_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public void clickSubmit() {
        this.waitDriver.until(
                ExpectedConditions.elementToBeClickable(this.driver
                        .findElement(By.id("submit")))).click();
    }
}