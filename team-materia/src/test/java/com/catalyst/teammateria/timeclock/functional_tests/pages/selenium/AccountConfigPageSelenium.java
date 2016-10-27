package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.AccountConfigPage;

public class AccountConfigPageSelenium implements AccountConfigPage {

    private static final String ROLE_ID                = "role";
    private static final String USER_DROPDOWN_ID       = "user";
    private static final String ACCOUNT_CONFIG_LINK_ID = "account-config-link";
    private static final String ADD_USER_BUTTON_ID     = "formSwapButton";
    private static final String UPDATE_BUTTON_ID       = "submit";
    private static final String INACTIVE_BUTTON_ID     = "inactiveButton";
    private static final String INACTIVE_ID            = "inactive";
    private static final String ACTIVE_ID              = "active";
    private static final String FIRST_NAME_ID          = "firstName";
    private static final String LAST_NAME_ID           = "lastName";
    private static final String USERNAME_ID            = "username";
    private static final String EMAIL_ID               = "email";
    private static final String PASSWORD_ID            = "password";
    private static final String VALUE_ATTRIBUTE        = "value";
    private static final String ACCOUNT_CONFIG_TITLE   = "Account Config";
    private RemoteWebDriver     driver;
    private WebDriverWait       waitDriver;

    public AccountConfigPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Click on the account config link
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(ACCOUNT_CONFIG_LINK_ID)));
        driver.findElement(By.id(ACCOUNT_CONFIG_LINK_ID)).click();
    }

    @Override
    public String getFirstName() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_NAME_ID)));
        return driver.findElement(By.id(FIRST_NAME_ID)).getAttribute(
                VALUE_ATTRIBUTE);
    }

    @Override
    public void setFirstName(String firstName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(FIRST_NAME_ID)));
        driver.findElement(By.id(FIRST_NAME_ID)).clear();
        driver.findElement(By.id(FIRST_NAME_ID)).sendKeys(firstName);
    }

    @Override
    public String getLastName() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(LAST_NAME_ID)));
        return driver.findElement(By.id(LAST_NAME_ID)).getAttribute(
                VALUE_ATTRIBUTE);
    }

    @Override
    public void setLastName(String lastName) {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(LAST_NAME_ID)));
        driver.findElement(By.id(LAST_NAME_ID)).clear();
        driver.findElement(By.id(LAST_NAME_ID)).sendKeys(lastName);
    }

    @Override
    public String getUsername() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(USERNAME_ID)));
        return driver.findElement(By.id(USERNAME_ID)).getAttribute(
                VALUE_ATTRIBUTE);
    }

    @Override
    public void setUserName(String username) {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(USERNAME_ID)));
        driver.findElement(By.id(USERNAME_ID)).clear();
        driver.findElement(By.id(USERNAME_ID)).sendKeys(username);
    }

    @Override
    public String getEmail() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(EMAIL_ID)));
        return driver.findElement(By.id(EMAIL_ID))
                .getAttribute(VALUE_ATTRIBUTE);
    }

    @Override
    public void setEmail(String email) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(EMAIL_ID)));
        driver.findElement(By.id(EMAIL_ID)).clear();
        driver.findElement(By.id(EMAIL_ID)).sendKeys(email);
    }

    @Override
    public String getPassword() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(PASSWORD_ID)));
        return driver.findElement(By.id(PASSWORD_ID)).getAttribute(
                VALUE_ATTRIBUTE);
    }

    @Override
    public void setPassword(String password) {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(PASSWORD_ID)));
        driver.findElement(By.id(PASSWORD_ID)).clear();
        driver.findElement(By.id(PASSWORD_ID)).sendKeys(password);
    }

    @Override
    public boolean getActive() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(ACTIVE_ID)));
        return driver.findElement(By.id(ACTIVE_ID)).isSelected();
    }

    @Override
    public void selectActive() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(ACTIVE_ID)));
        driver.findElement(By.id(ACTIVE_ID)).click();
    }

    @Override
    public boolean getInactive() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(INACTIVE_ID)));
        return driver.findElement(By.id(INACTIVE_ID)).isSelected();
    }

    @Override
    public void selectInactive() {
        this.waitDriver
                .until(ExpectedConditions.elementToBeClickable(this.driver
                        .findElementById(INACTIVE_ID)));
        driver.findElement(By.id(INACTIVE_ID)).click();
    }

    @Override
    public String getRole() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(ROLE_ID)));
        Select select = new Select(driver.findElement(By.id(ROLE_ID)));
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void setRole(String role) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElementById(ROLE_ID)));
        Select select = new Select(driver.findElement(By.id(ROLE_ID)));
        select.selectByVisibleText(role);
    }

    @Override
    public void clickInactiveButton() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(INACTIVE_BUTTON_ID)));
        driver.findElement(By.id(INACTIVE_BUTTON_ID)).click();
    }

    @Override
    public String getInactiveButtonValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clickUpdate() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(UPDATE_BUTTON_ID)));
        driver.findElement(By.id(UPDATE_BUTTON_ID)).click();
    }

    @Override
    public void clickCancel() {
        // TODO Auto-generated method stub
    }

    @Override
    public void clickAddUser() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(ADD_USER_BUTTON_ID)));
        driver.findElement(By.id(ADD_USER_BUTTON_ID)).click();
    }

    @Override
    public boolean accountConfigTitle() {
        waitDriver
                .until(ExpectedConditions.titleContains(ACCOUNT_CONFIG_TITLE));
        if (ACCOUNT_CONFIG_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public String getUser() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(USER_DROPDOWN_ID)));
        Select select = new Select(driver.findElement(By.id(USER_DROPDOWN_ID)));
        return select.getFirstSelectedOption().getText();
    }

    @Override
    public void setUser(String user) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(USER_DROPDOWN_ID)));
        Select select = new Select(driver.findElement(By.id(USER_DROPDOWN_ID)));
        select.selectByVisibleText(user);
    }

    @Override
    public void confirmAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Override
    public String getUpdateButtonVisibleText() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(UPDATE_BUTTON_ID)));
        WebElement submitButton = this.driver.findElement(By
                .id(UPDATE_BUTTON_ID));
        return submitButton.getText();
    }

    @Override
    public boolean userDropdownVisible() {
        WebElement userDropdown = this.driver.findElement(By
                .id(USER_DROPDOWN_ID));
        return userDropdown.isDisplayed();
    }

    @Override
    public boolean showInactiveButtonVisible() {
        WebElement showInactive = this.driver.findElement(By
                .id(INACTIVE_BUTTON_ID));
        return showInactive.isDisplayed();
    }

    @Override
    public boolean roleDropdownVisible() {
        WebElement role = this.driver.findElement(By.id(ROLE_ID));
        return role.isDisplayed();
    }

    @Override
    public boolean activeRadioButtonsVisible() {
        WebElement role = this.driver.findElement(By.name("user.active"));
        return role.isDisplayed();
    }

    @Override
    public boolean userPresent(String userVisibleText) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById(USER_DROPDOWN_ID)));
        Select select = new Select(driver.findElement(By.id(USER_DROPDOWN_ID)));
        // Get a list of all of the options
        List<WebElement> optionList = select.getOptions();
        for (int i = 0; i < optionList.size(); i++ ) {
            System.out.println(optionList.get(i).getText());
            // If the current visible text matches the passed in text, return
            // true
            String currentVisibleText = optionList.get(i).getText();
            if (userVisibleText.equals(currentVisibleText)) {
                return true;
            }
        }
        // Return false if no match is found
        return false;
    }
}
