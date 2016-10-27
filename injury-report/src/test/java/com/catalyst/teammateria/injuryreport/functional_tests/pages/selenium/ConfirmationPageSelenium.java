package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.ConfirmationPage;

/**
 * Here's for the do with some SELENIUM all over your precious confirmation
 * page.
 * 
 * @author nPoloway
 */
public class ConfirmationPageSelenium implements ConfirmationPage {

    private RemoteWebDriver driver;
    private WebDriverWait   waitDriver;

    /**
     * Constructor for ConfirmationPageSelenium object
     * 
     * @param driver
     *            webdriver to control confirmation page
     */
    public ConfirmationPageSelenium(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("http://localhost:8000");
    }

    /**
     * Finds button element
     * 
     * @return submit button element
     */
    private WebElement getSubmitElement() {
        return this.driver.findElementById("submit");
    }

    /**
     * Finds header element containing prompt for user to record confirmation
     * number
     * 
     * @return header element
     */
    private WebElement getPromptElement() {
        return this.driver.findElementByTagName("h3");
    }

    /**
     * Finds paragraph element containing confirmation number
     * 
     * @return paragraph element
     */
    private WebElement getIdElement() {
        return this.driver.findElementByTagName("p");
    }

    /**
     * Finds anchor element with the login page href
     * 
     * @return anchor element
     */
    private WebElement getHomeElement() {
        return this.driver.findElementByTagName("a");
    }

    /**
     * Finds header element containing successful submission confirmation
     * 
     * @return header element
     */
    private WebElement getConfirmation() {
        return this.driver.findElementByTagName("h1");
    }

    @Override
    public void clickSubmit() {
        waitDriver.until(ExpectedConditions.presenceOfElementLocated(By
                .id("submit")));
        this.getSubmitElement().click();
    }

    @Override
    public Boolean promptIsPresent() {
        waitDriver.until(ExpectedConditions.titleContains("Confirmation"));
        if ("".equals(this.getPromptElement().getText())) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean idIsPresent() {
        waitDriver.until(ExpectedConditions.titleContains("Confirmation"));
        if (this.getIdElement().getText().length() < 18) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void clickHome() {
        waitDriver.until(ExpectedConditions.titleContains("Confirmation"));
        this.getHomeElement().click();
    }

    @Override
    public Boolean confirmationTitle() {
        waitDriver.until(ExpectedConditions.titleContains("Confirmation"));
        if ("Confirmation".equals(driver.getTitle())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean loginTitle() {
        waitDriver.until(ExpectedConditions.elementToBeClickable(By
                .name("submit")));
        if ("Login".equals(this.getConfirmation().getText())) {
            return true;
        } else {
            return false;
        }
    }
}
