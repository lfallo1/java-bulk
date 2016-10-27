package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.LoginPage;

/**
 * Since because SELENIUM if there's enough to login page.
 * 
 * @author nPoloway
 */
public class LoginPageSelenium implements LoginPage {

    private RemoteWebDriver     driver;
    private WebDriverWait       waitDriver;
    private static final String URL                         = "localhost:8000/login";
    private static final String INPUT_USERNAME              = "inputUsername";
    private static final String INPUT_PASSWORD              = "inputPassword";
    private static final String ERROR_MESSAGE               = "errorMessage";
    private static final String SPRING_SECURITY_REMEMBER_ME = "_spring_security_remember_me";
    private static final String SUBMIT                      = "submit";
    private static final String VALUE                       = "value";
    private static final String TITLE                       = "title";
    private static final String LOGIN_PAGE                  = "Login Page";

    /**
     * Constructor for LoginPageSelenium object
     * 
     * @param driver
     *            webdriver to control page
     */
    public LoginPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get(URL);
    }

    /**
     * Finds textbox webelement containing Username string
     * 
     * @return textbox webelement
     */
    private WebElement getUsernameElement() {
        return this.driver.findElementById(INPUT_USERNAME);
    }

    /**
     * Finds textbox webelement containing Password string
     * 
     * @return textbox webelement
     */
    private WebElement getPasswordElement() {
        return this.driver.findElementById(INPUT_PASSWORD);
    }

    /**
     * Finds paragraph webelement containing error message string
     * 
     * @return paragraph webelement
     */
    private WebElement getErrorElement() {
        return this.driver.findElementById(ERROR_MESSAGE);
    }

    /**
     * Finds checkbox webelement
     * 
     * @return remember me checkbox webelement
     */
    private WebElement getRememberElement() {
        return this.driver.findElementByName(SPRING_SECURITY_REMEMBER_ME);
    }

    /**
     * Finds button element
     * 
     * @return submit button element
     */
    private WebElement getLoginElement() {
        return this.driver.findElementByName(SUBMIT);
    }

    @Override
    public String getUsername() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .id(INPUT_USERNAME)));
        return this.getUsernameElement().getAttribute(VALUE);
    }

    @Override
    public String getPassword() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .id(INPUT_PASSWORD)));
        return this.getPasswordElement().getAttribute(VALUE);
    }

    @Override
    public String getErrorMessage() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .id(ERROR_MESSAGE)));
        return this.getErrorElement().getText();
    }

    @Override
    public void setUsername(String username) {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .id(INPUT_USERNAME)));
        this.getUsernameElement().sendKeys(username);
    }

    @Override
    public void setPassword(String password) {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .id(INPUT_PASSWORD)));
        this.getPasswordElement().sendKeys(password);
    }

    @Override
    public void toggleRememberMe() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .name(SPRING_SECURITY_REMEMBER_ME)));
        this.getRememberElement().click();
    }

    @Override
    public Boolean isRemembered() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .name(SPRING_SECURITY_REMEMBER_ME)));
        if (this.getRememberElement().isSelected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void submitLogin() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .name(SUBMIT)));
        this.getLoginElement().click();
    }

    @Override
    public Boolean loginTitle() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .tagName(TITLE)));
        if (LOGIN_PAGE.equals(driver.getTitle())) {
            return true;
        } else {
            return false;
        }
    }
}
