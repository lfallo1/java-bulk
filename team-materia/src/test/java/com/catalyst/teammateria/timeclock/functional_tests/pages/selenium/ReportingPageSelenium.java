package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.ReportingPage;

public class ReportingPageSelenium implements ReportingPage {

    private static final String URL         = "http://localhost:8000/";
    private static final String TOTAL_HOURS = "TotalHrsThumbnail";
    private static final String OVERTIME    = "OvertimeThumbnail";
    private static final String ABSENT      = "AbsentThumbnail";
    private static final String DATES       = "select-list-dates";
    private static final String USERS       = "select-list-users";
    private static final String LOGOUT      = "logout-nav";
    private static final String TR          = "tr";
    private static final String TD          = "td";
    private static final String H3          = "h3";
    private static final String DIV         = "div";
    private static final String SPAN        = "span";
    private static final String CLASS       = "class";
    private static final String ACTIVE      = "active";
    private static final String SUCCESS     = "success";
    private static final String TD_USER     = "td-user";
    private RemoteWebDriver     driver;
    private WebDriverWait       waitDriver;

    public ReportingPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(URL);
    }

    @Override
    public void clickHoursByWeek() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(TOTAL_HOURS)));
        driver.findElement(By.id(TOTAL_HOURS)).click();
    }

    @Override
    public void clickOvertime() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(OVERTIME)));
        driver.findElement(By.id(OVERTIME)).click();
    }

    @Override
    public void clickAbsent() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(ABSENT)));
        driver.findElement(By.id(ABSENT)).click();
    }

    @Override
    public Boolean dateEnabled() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        if (driver.findElement(By.id(DATES)).isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean usersEnabled() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        if (driver.findElement(By.id(USERS)).isEnabled()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getWeek() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(DATES)));
        Select week = new Select(driver.findElement(By.id(DATES)));
        return week.getFirstSelectedOption().getText();
    }

    @Override
    public void selectWeek(Integer index) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(DATES)));
        Select week = new Select(driver.findElement(By.id(DATES)));
        week.selectByIndex(index);
    }

    @Override
    public void selectUser(Integer index) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(USERS)));
        Select user = new Select(driver.findElement(By.id(USERS)));
        user.selectByIndex(index);
    }

    @Override
    public List<WebElement> weekList() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(DATES)));
        Select date = new Select(driver.findElement(By.id(DATES)));
        return date.getOptions();
    }

    @Override
    public Integer getTableSize() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        return driver.findElements(By.tagName(TR)).size();
    }

    @Override
    public String getCellText(int tableRow, int tableColumn) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        List<WebElement> table = driver.findElements(By.tagName(TR));
        List<WebElement> rows = table.get(tableRow)
                .findElements(By.tagName(TD));
        return rows.get(tableColumn).getText();
    }

    @Override
    public String getActiveReport() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        if (driver.findElement(By.id(TOTAL_HOURS)).getAttribute(CLASS)
                .endsWith(ACTIVE)) {
            return driver.findElement(By.id(TOTAL_HOURS))
                    .findElement(By.tagName(H3)).getText();
        } else if (driver.findElement(By.id(OVERTIME)).getAttribute(CLASS)
                .endsWith(ACTIVE)) {
            return driver.findElement(By.id(OVERTIME))
                    .findElement(By.tagName(H3)).getText();
        } else if (driver.findElement(By.id(ABSENT)).getAttribute(CLASS)
                .endsWith(ACTIVE)) {
            return driver.findElement(By.id(ABSENT))
                    .findElement(By.tagName(H3)).getText();
        } else {
            return "";
        }
    }

    @Override
    public Boolean mouseOverShow(Integer row) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        Actions builder = new Actions(driver);
        List<WebElement> userRows = driver.findElementsByClassName(TD_USER);
        builder.click(userRows.get(row).findElement(By.tagName(SPAN)))
                .perform();
        if (userRows.get(row).findElement(By.tagName(DIV)).isDisplayed()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean wasAbsent(int tableRow, int tableColumn) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOGOUT)));
        List<WebElement> table = driver.findElements(By.tagName(TR));
        List<WebElement> rows = table.get(tableRow)
                .findElements(By.tagName(TD));
        WebElement inner = rows.get(tableColumn);
        if (inner.getAttribute(CLASS).endsWith(SUCCESS)) {
            return false;
        } else {
            return true;
        }
    }
}
