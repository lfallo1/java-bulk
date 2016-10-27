package com.catalyst.teammateria.timeclock.functional_tests.pages.selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.catalyst.teammateria.timeclock.functional_tests.pages.AddUserPage;

public class AddUserPageSelenium implements AddUserPage {

    private static final String REGISTRATION_SUCCESS_TITLE = "Login";
    private static final String REGISTER_TITLE = "Register";
    private RemoteWebDriver driver;
    private WebDriverWait   waitDriver;

    public AddUserPageSelenium(RemoteWebDriver driver) {
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        this.driver.get("http://localhost:8000/register");
    }

    @Override
    public String getFirstName() {
        return driver.findElement(By.id("firstName")).getAttribute("value");        
    }

    @Override
    public void setFirstName(String firstName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("firstName")));
        driver.findElement(By.id("firstName")).clear();
        driver.findElement(By.id("firstName")).sendKeys(firstName);        
    }

    @Override
    public String getLastName() {
        return driver.findElement(By.id("lastName")).getAttribute("value");     
    }

    @Override
    public void setLastName(String lastName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("lastName")));
        driver.findElement(By.id("lastName")).clear();
        driver.findElement(By.id("lastName")).sendKeys(lastName);        
    }

    @Override
    public String getUserName() {
        return driver.findElement(By.id("username")).getAttribute("value");     
    }

    @Override
    public void setUserName(String userName) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("username")));
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys(userName);        
    }

    @Override
    public String getEmail() {
        return driver.findElement(By.id("email")).getAttribute("value");     
    }

    @Override
    public void setEmail(String email) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("email")));
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys(email);   
        
    }

    @Override
    public String getPassword() {
        return driver.findElement(By.id("password")).getAttribute("value");     
    }

    @Override
    public void setPassword(String password) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("password")));
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);          
    }

    @Override
    public String getConfirmPassword() {
        return driver.findElement(By.id("passwordConfirm")).getAttribute("value");     
    }

    @Override
    public void setConfirmPassword(String confirmPassword) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver
                        .findElementById("passwordConfirm")));
        driver.findElement(By.id("passwordConfirm")).clear();
        driver.findElement(By.id("passwordConfirm")).sendKeys(confirmPassword);
    }

    @Override
    public void submitRegisterForm() {
        driver.findElement(By.id("submit")).click();        
    }

    @Override
    public boolean registerTitle() {
        waitDriver.until(ExpectedConditions.titleContains(REGISTER_TITLE));
        if (REGISTER_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean registrationSuccessTitle() {
        waitDriver.until(ExpectedConditions.titleContains(REGISTRATION_SUCCESS_TITLE));
        if (REGISTRATION_SUCCESS_TITLE.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }
}
