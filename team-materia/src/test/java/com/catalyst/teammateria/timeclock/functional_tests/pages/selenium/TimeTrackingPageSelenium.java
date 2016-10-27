package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.TimeTrackingPage;

public class TimeTrackingPageSelenium implements TimeTrackingPage {

    private static final String LOADING_IMG = "loading-image";

    private static final String CLOCK_IN_VISIBLE_TEXT = "Clock In";

    private static final String CLOCK_OUT_VISIBLE_TEXT = "Clock Out";

    private static final String ENABLED_NEXT_BUTTON_CLASS = "btn-primary";

    private static final String BEGIN_DATE_ID = "beginDate";

    private static final String CLOCK_BUTTON_ID = "clockBtn";

    private static final String BASE_URL = "http://localhost:8000/";

    private static final String PREVIOUS_BUTTON_ID = "previous";

    private static final String CLOCK_BUTTON = "clock";

    private static final String NEXT_BUTTON_ID = "next";

    private RemoteWebDriver driver;

    private WebDriverWait waitDriver;

    public TimeTrackingPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.driver.get(BASE_URL);
    }

    @Override
    public void clockIn() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(CLOCK_BUTTON)));
        if (CLOCK_IN_VISIBLE_TEXT.equals(driver
                .findElement(By.id(CLOCK_BUTTON)).getText())) {
            driver.findElement(By.id(CLOCK_BUTTON_ID)).click();
        }
    }

    @Override
    public void clockOut() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(CLOCK_BUTTON)));
        if (CLOCK_OUT_VISIBLE_TEXT.equals(driver.findElement(
                By.id(CLOCK_BUTTON)).getText())) {
            driver.findElement(By.id(CLOCK_BUTTON_ID)).click();
        }
    }

    @Override
    public void nextWeek() {        
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(NEXT_BUTTON_ID)));
        driver.findElement(By.id(NEXT_BUTTON_ID)).click();
    }

    @Override
    public void previousWeek() {        
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(PREVIOUS_BUTTON_ID)));
        driver.findElement(By.id(PREVIOUS_BUTTON_ID)).click();
    }

    @Override
    public String getWeekBegin() {
        // Wait for ajax call to complete
        this.waitDriver.until(ExpectedConditions
                .invisibilityOfElementLocated(By.id(LOADING_IMG)));
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(BEGIN_DATE_ID)));
        return driver.findElement(By.id(BEGIN_DATE_ID)).getText();
    }

    @Override
    public String getButtonValue() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(CLOCK_BUTTON)));
        return driver.findElement(By.id(CLOCK_BUTTON)).getText();
    }

    @Override
    public Boolean nextEnabled() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(NEXT_BUTTON_ID)));
        if (ENABLED_NEXT_BUTTON_CLASS.equals(driver.findElement(
                By.id(NEXT_BUTTON_ID)).getClass())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean previousEnabled() {
        if (ENABLED_NEXT_BUTTON_CLASS.equals(driver.findElement(
                By.id(PREVIOUS_BUTTON_ID)).getClass())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Float getHours() {
        // Determine the day of the week
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE");
        String today = dateFormat.format(c.getTime());
        today = today.toLowerCase();
        // Wait for the current day to be clickable
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(today)));
        Float hours = Float.parseFloat( (driver.findElement(By.id(today))
                .getText()));
        return hours;
    }
}
