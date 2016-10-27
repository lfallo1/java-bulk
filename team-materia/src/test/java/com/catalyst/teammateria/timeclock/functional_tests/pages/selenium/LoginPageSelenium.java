package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.LoginPage;

/**
 * Selenium implementation of LoginPage interface
 * 
 * @author nPoloway
 */
public class LoginPageSelenium implements LoginPage {

    private static final String LOGIN_TITLE = "Login";

    private static final String ADMIN_SPLASH_TITLE = "Admin Splash";

    private static final String TIME_TRACKING_TITLE = "Time Tracking";

    private RemoteWebDriver driver;

    private WebDriverWait waitDriver;

    private static final String USERNAME = "username";

    private static final String PASSWORD = "password";

    private static final String BUTTON = "button-login";

    private static final String ERROR = "errorPlaceholder";

    private static final String URL = "localhost:8000/login";

    public LoginPageSelenium(RemoteWebDriver driver) {
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

    /**
     * Gets error message after it appears if at all
     * 
     * @return
     */
    private WebElement getErrorMessageElement() {
        return this.waitDriver.until(ExpectedConditions
                .presenceOfElementLocated(By.id(ERROR)));
    }

    @Override
    public String getUserName() {
        return this.getUsernameElement().getText();
    }

    @Override
    public String getUserPassword() {
        return this.getPasswordElement().getText();
    }

    @Override
    public void setUsername(String username) {
        this.getUsernameElement().sendKeys(username);
    }

    @Override
    public void setUserPassword(String password) {
        this.getPasswordElement().sendKeys(password);
    }

    @Override
    public String getErrorMessage() {
        return this.getErrorMessageElement().getText();
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

    @Override
    public boolean adminSplashPageTitle() {
        waitDriver.until(ExpectedConditions.titleContains(ADMIN_SPLASH_TITLE));
        if (ADMIN_SPLASH_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean timeTrackingTitle() {
        waitDriver.until(ExpectedConditions.titleContains(TIME_TRACKING_TITLE));
        if (TIME_TRACKING_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }
}
