package com.catalyst.teammateria.timeclock.functional_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class for selenium tests
 * 
 * @author nPoloway
 */
public class SeleniumHelper {

    /**
     * Logs in a test user, redirects to time tracking page
     */
    public void loginDriverUser(WebDriver driver) {
        driver.get("http://localhost:8000/login");
        driver.findElement(By.name("username")).sendKeys("test1");
        driver.findElement(By.name("password")).sendKeys("Password1?");
        driver.findElement(By.id("button-login")).click();
    }

    /**
     * Logs in admin, redirects to admin splash page
     */
    public void loginDriverAdmin(WebDriver driver) {
        driver.get("http://localhost:8000/login");
        driver.findElement(By.name("username")).sendKeys("admin1");
        driver.findElement(By.name("password")).sendKeys("Password3?");
        driver.findElement(By.id("button-login")).click();
    }

    /**
     * Clicks the logout button in the navbar
     * 
     * @param driver
     */
    public void clickLogout(WebDriver driver) {
        driver.findElement(By.id("logout-nav")).click();
    }

    /**
     * Logs in a user
     * 
     * @param username
     *            - a valid username
     * @param password
     *            - matching password
     * @param driver
     *            - the current webdriver
     */
    public void loginUser(WebDriver driver, String username, String password) {
        driver.get("http://localhost:8000/login");
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id("button-login")).click();
    }

    /**
     * Return true if the current page's title matches the passed in title
     * 
     * @param driver
     *            - The current WebDriver
     * @param title
     *            - The searched title
     * @param timeout
     *            - The maximum time(seconds) to wait for title to appear
     * @return
     */
    public boolean onTitle(WebDriver driver, String title, Long timeout) {
        WebDriverWait waitDriver = new WebDriverWait(driver, timeout);
        waitDriver.until(ExpectedConditions.titleContains(title));
        if (title.equals(driver.getTitle())) {
            return true;
        }
        return false;
    }
}
