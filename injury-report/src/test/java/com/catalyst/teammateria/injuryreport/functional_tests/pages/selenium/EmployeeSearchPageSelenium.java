package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.EmployeeSearchPage;

public class EmployeeSearchPageSelenium implements EmployeeSearchPage {

    private static final String EMPLOYEE_SEARCH_TITLE = "Employee Search";

    private static final String INJURY_REPORTING_TITLE = "Injury Reporting";

    private static final String USER2_VISIBLE_TEXT = "usertwo, test";

    private static final String USER1_VISIBLE_TEXT = "userone, test";

    private static final String EMPLOYEE_ID_INPUT_ID = "employeeId";

    private static final String LAST_NAME_INPUT_ID = "lastName";

    private static final String FIRST_NAME_INPUT_ID = "firstName";

    private static final String LOOK_UP_ID = "lookUp";

    private RemoteWebDriver driver;

    private WebDriverWait waitDriver;

    public EmployeeSearchPageSelenium(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("http://localhost:8000");
    }

    @Override
    public void setFirstName(String firstName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_NAME_INPUT_ID)));
        driver.findElement(By.id(FIRST_NAME_INPUT_ID)).clear();
        driver.findElement(By.id(FIRST_NAME_INPUT_ID)).sendKeys(firstName);
    }

    @Override
    public void setlastName(String lastName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(LAST_NAME_INPUT_ID)));
        driver.findElement(By.id(LAST_NAME_INPUT_ID)).clear();
        driver.findElement(By.id(LAST_NAME_INPUT_ID)).sendKeys(lastName);
    }

    @Override
    public void setId(String Id) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(EMPLOYEE_ID_INPUT_ID)));
        driver.findElement(By.id(EMPLOYEE_ID_INPUT_ID)).clear();
        driver.findElement(By.id(EMPLOYEE_ID_INPUT_ID)).sendKeys(Id);
    }

    @Override
    public void clickLookup() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(LOOK_UP_ID)));
        driver.findElement(By.id(LOOK_UP_ID)).click();
    }

    @Override
    public Boolean employee1Visible() {
        List<WebElement> allUsers = getUserAnchors();
        for (int i = 0; i < allUsers.size(); i++ ) {
            WebElement currentElement = allUsers.get(i);
            if (currentElement.getText().contains(USER1_VISIBLE_TEXT)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean employee2Visible() {
        List<WebElement> allUsers = getUserAnchors();
        for (int i = 0; i < allUsers.size(); i++ ) {
            WebElement currentElement = allUsers.get(i);
            if (currentElement.getText().contains(USER2_VISIBLE_TEXT)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean employee3Visible() {
        List<WebElement> allUsers = getUserAnchors();
        for (int i = 0; i < allUsers.size(); i++ ) {
            WebElement currentElement = allUsers.get(i);
            if (currentElement.getText().contains("user, user")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clickEmployee1() {
        clickByUserByVisibleText(USER1_VISIBLE_TEXT);
    }

    @Override
    public void clickEmployee2() {
        clickByUserByVisibleText(USER2_VISIBLE_TEXT);
    }

    @Override
    public Boolean onInjuryReportingTitle() {
        this.waitDriver.until(ExpectedConditions
                .titleContains(INJURY_REPORTING_TITLE));
        if (INJURY_REPORTING_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean onEmployeeSearchTitle() {
        this.waitDriver.until(ExpectedConditions
                .titleContains(EMPLOYEE_SEARCH_TITLE));
        if (EMPLOYEE_SEARCH_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    private List<WebElement> getUserAnchors() {
        List<WebElement> returnList = driver
                .findElementsByClassName("btn-default");
        return returnList;
    }

    private void clickByUserByVisibleText(String text) {
        if (text == null) {
            return;
        }
        List<WebElement> allUsers = getUserAnchors();
        for (int i = 0; i < allUsers.size(); i++ ) {
            WebElement currentElement = allUsers.get(i);
            if (currentElement.getText().contains(text)) {
                currentElement.click();
                return;
            }
        }
    }

    @Override
    public Boolean isErrorMessagePresent() {
        try {
            this.driver.switchTo().alert().dismiss();
            return true;
        } 
        catch (NoAlertPresentException Ex) {
            return false;
        }
    }
}