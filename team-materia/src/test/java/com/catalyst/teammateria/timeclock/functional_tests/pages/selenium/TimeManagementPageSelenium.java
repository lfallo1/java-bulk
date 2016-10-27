package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.TimeManagementPage;

public class TimeManagementPageSelenium implements TimeManagementPage {

    private static final String FIRST_ROW_CLOCK_OUT_ID = "1";

    private static final String VALUE_ATTRIBUTE = "value";

    private static final String FIRST_ROW_CLOCK_IN_ID = "0";

    private static final String WEEK_DROPDOWN_ID = "week";

    private static final String URL = "http://localhost:8000/";

    private static final String USER = "user";

    private static final String WEEK = WEEK_DROPDOWN_ID;

    private static final String SUBMIT = "submit";

    private static final String CANCEL = "Cancel";

    private static final String INDEX = "index";

    private static final String INACTIVE_BUTTON = "inactiveButton";

    private static final String INACTIVE_SHOWN = "Inactive Shown";

    private static final String CLOCK_DATE = "clockDate";

    private static final String CLOCK_IN_TIME = "clockInTime";

    private static final String CLOCK_OUT_TIME = "clockOutTime";

    private static final String USER_DROPDOWN_ID = "user";

    private RemoteWebDriver driver;

    private WebDriverWait waitDriver;

    public TimeManagementPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(URL);
    }

    @Override
    public String getUserIndex() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(USER)));
        Select user = new Select(driver.findElement(By.id(USER)));
        return user.getFirstSelectedOption().getAttribute(INDEX);
    }

    @Override
    public void selectUserName(Integer index) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(USER)));
        Select user = new Select(driver.findElement(By.id(USER)));
        user.selectByIndex(index);
    }

    @Override
    public Boolean getActiveShown() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(INACTIVE_BUTTON)));
        if (INACTIVE_SHOWN.equals(driver.findElement(By.id(INACTIVE_BUTTON))
                .getText())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void toggleActiveShown() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(INACTIVE_BUTTON)));
        driver.findElement(By.id(INACTIVE_BUTTON)).click();
    }

    @Override
    public String getWeek() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(WEEK)));
        Select week = new Select(driver.findElement(By.id(WEEK)));
        return week.getFirstSelectedOption().getAttribute(INDEX);
    }

    @Override
    public void selectWeek(Integer index) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(WEEK)));
        Select week = new Select(driver.findElement(By.id(WEEK)));
        week.selectByIndex(index);
    }

    @Override
    public String getFirstClockIn() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_ROW_CLOCK_IN_ID)));
        return driver.findElement(By.id(FIRST_ROW_CLOCK_IN_ID)).getAttribute(
                VALUE_ATTRIBUTE);
    }

    @Override
    public void setFirstClockIn(String time) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_ROW_CLOCK_IN_ID)));
        WebElement firstClockIn = driver.findElement(By
                .id(FIRST_ROW_CLOCK_IN_ID));
        firstClockIn.clear();
        firstClockIn.sendKeys(time);
    }

    @Override
    public String getFirstClockOut() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_ROW_CLOCK_OUT_ID)));
        return driver.findElement(By.id(FIRST_ROW_CLOCK_OUT_ID)).getAttribute(
                VALUE_ATTRIBUTE);
    }

    @Override
    public void setFirstClockOut(String time) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_ROW_CLOCK_OUT_ID)));
        WebElement firstClockIn = driver.findElement(By
                .id(FIRST_ROW_CLOCK_OUT_ID));
        firstClockIn.clear();
        firstClockIn.sendKeys(time);
    }

    @Override
    public String getDate() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(CLOCK_DATE)));
        return driver.findElement(By.id(CLOCK_DATE)).getText();
    }

    @Override
    public void setDate(String date) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(CLOCK_DATE)));
        driver.findElement(By.id(CLOCK_DATE)).sendKeys(date);
    }

    @Override
    public void clickUpdate() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(SUBMIT)));
        driver.findElement(By.id(SUBMIT)).click();
    }

    @Override
    public void clickCancel() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementByLinkText(CANCEL)));
        driver.findElementByLinkText(CANCEL).click();
    }

    @Override
    public List<String> userList() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(USER)));
        Select user = new Select(driver.findElement(By.id(USER)));
        List<WebElement> elementList = user.getOptions();
        List<String> retString = new ArrayList<String>();
        for (int i = 0; i < elementList.size(); i++ ) {
            retString.add(elementList.get(i).getText());
        }
        return retString;
    }

    @Override
    public List<String> weekList() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(WEEK)));
        Select week = new Select(driver.findElement(By.id(WEEK)));
        List<WebElement> elementList = week.getOptions();
        List<String> retString = new ArrayList<String>();
        for (int i = 0; i < elementList.size(); i++ ) {
            retString.add(elementList.get(i).getText());
        }
        return retString;
    }

    @Override
    public void selectUserByVisibleText(String user) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(USER_DROPDOWN_ID)));
        Select select = new Select(driver.findElement(By.id(USER_DROPDOWN_ID)));
        select.selectByVisibleText(user);
    }

    @Override
    public String getUserVisibleText() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(USER_DROPDOWN_ID)));
        Select select = new Select(driver.findElement(By.id(USER_DROPDOWN_ID)));
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public boolean weekPresent(String week) {
        if (week == null) {
            return false;
        }
        List<String> weekList = this.weekList();
        for (int i = 0; i < weekList.size(); i++ ) {
            if (week.equals(weekList.get(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void selectWeekByVisibleText(String week) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(WEEK_DROPDOWN_ID)));
        Select select = new Select(driver.findElement(By.id(WEEK_DROPDOWN_ID)));
        select.selectByVisibleText(week);
    }
}