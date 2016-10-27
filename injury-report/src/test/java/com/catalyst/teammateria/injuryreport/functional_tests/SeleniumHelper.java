package com.catalyst.teammateria.injuryreport.functional_tests;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Helper class for selenium tests
 * 
 * @author gPorter
 */
public class SeleniumHelper {

    WebDriverWait waitDriver;
    Random        rand = new Random();

    // public SeleniumHelper(WebDriver driver){
    // this.waitDriver = new WebDriverWait(driver, 20);
    // }
    public void loginDriverUser(WebDriver driver) {
        driver.get("http://localhost:8000/login");
        driver.findElement(By.id("inputUsername")).sendKeys("testUser2");
        driver.findElement(By.id("inputPassword")).sendKeys("Password1?");
        driver.findElement(By.name("submit")).click();
    }

    public void loginDriverAdmin(WebDriver driver) {
        driver.get("http://localhost:8000/login");
        driver.findElement(By.id("inputUsername")).sendKeys("testUser1");
        driver.findElement(By.id("inputPassword")).sendKeys("Password1?");
        driver.findElement(By.name("submit")).click();
    }

    // Creates a random report to quickly access confirmation page and injury
    // report page
    public void createDummyData(WebDriver driver) {
        // Show list of all users, select one at random
        driver.findElement(By.id("lookUp")).click();
        List<WebElement> buttons = driver.findElements(By.tagName("a"));
        int randomNum = rand.nextInt( (buttons.size() - 1) + 1) + 1;
        buttons.get(randomNum - 1).click();
        // Select both calendars
        List<WebElement> calendars = driver.findElements(By
                .className("ui-datepicker-calendar"));
        randomNum = rand.nextInt( (28 - 1) + 1) + 1;
        for (WebElement calendar : calendars) {
            // Select random day on each calendar
            calendar.findElement(By.linkText(Integer.toString(randomNum)))
                    .click();
            randomNum = rand.nextInt( (28 - randomNum) + 1) + randomNum;
        }
        // Select random weather option
        List<WebElement> weather = driver.findElement(By.id("weatherSelect"))
                .findElements(By.tagName("option"));
        randomNum = rand.nextInt( (weather.size() - 1) + 1) + 1;
        weather.get(randomNum).click();
        // Select random injurytype option
        List<WebElement> injury = driver.findElement(By.id("injuryTypeSelect"))
                .findElements(By.tagName("option"));
        randomNum = rand.nextInt( (injury.size() - 1) + 1) + 1;
        injury.get(randomNum).click();
        // Select random bodypart option
        List<WebElement> bodyPart = driver.findElement(By.id("bodyPartSelect"))
                .findElements(By.tagName("option"));
        randomNum = rand.nextInt( (bodyPart.size() - 1) + 1) + 1;
        bodyPart.get(randomNum).click();
        // Click time textbox to display timepicker
        driver.findElement(By.id("timepicker")).click();
        // Select random hour
        randomNum = rand.nextInt( (23 - 1) + 1) + 1;
        List<WebElement> hours = driver.findElement(
                By.className("ui-timepicker-hours")).findElements(
                By.tagName("td"));
        hours.get(randomNum).click();
        // Select random minute
        randomNum = rand.nextInt( (11 - 1) + 1) + 1;
        List<WebElement> minutes = driver.findElement(
                By.className("ui-timepicker-minutes")).findElements(
                By.tagName("td"));
        minutes.get(randomNum).click();
        // Mark report description with test notifier
        driver.findElement(By.id("description")).sendKeys("Test report");
        // Submit report
        driver.findElement(By.id("next")).click();
    }
}
