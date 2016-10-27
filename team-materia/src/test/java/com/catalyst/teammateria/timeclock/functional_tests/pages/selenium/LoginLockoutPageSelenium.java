package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.LoginLockoutPage;

/**
 * Selenium implementation of LoginLockoutPage interface
 * 
 * @author nPoloway
 */
public class LoginLockoutPageSelenium implements LoginLockoutPage {

    private static final String LOGIN_TITLE = "Login";
    private RemoteWebDriver     driver;
    private WebDriverWait       waitDriver;
    private static final String USERNAME    = "username";
    private static final String PASSWORD    = "password";
    private static final String BUTTON      = "button-login";
    private static final String URL         = "localhost:8000/login";

    public LoginLockoutPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(URL);
    }

    /**
     * Gets the username element
     * 
     * @return
     */
    private WebElement getUsernameElement() {
        return this.driver.findElementByName(USERNAME);
    }

    /**
     * Gets the password element
     * 
     * @return
     */
    private WebElement getPasswordElement() {
        return this.driver.findElementByName(PASSWORD);
    }

    @Override
    public void setUsername(String username) {
        this.getUsernameElement().sendKeys(username);
    }

    @Override
    public void clearFields() {
        this.getUsernameElement().clear();
        this.getPasswordElement().clear();
    }

    @Override
    public void setUserPassword(String password) {
        this.getPasswordElement().sendKeys(password);
    }

    @Override
    public void submitLogin() {
        WebElement button = this.driver.findElementById(BUTTON);
        button.click();
    }

    @Override
    public boolean loginTitle() {
        waitDriver.until(ExpectedConditions.titleContains(LOGIN_TITLE));
        if (LOGIN_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }
}
