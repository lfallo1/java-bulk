package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.AdminReviewPage;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.AdminReviewPageSelenium;

public class AdminReviewPageTest {

    private RemoteWebDriver driver;
    private SeleniumHelper  helper;
    private AdminReviewPage target;
    private Random          random = new Random();

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
        target = new AdminReviewPageSelenium(driver);
        driver.findElement(By.id("review")).click();
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Ignore
    public void filterById() {
        target.setId("9");
        assertEquals(target.getId(), "9");
        target.clickSearch();
        List<WebElement> filtered = target.getSearchResults();
        assertNotEquals(filtered.size(), 0);
        target.setId("2");
        assertEquals(target.getId(), "2");
        target.clickSearch();
        assertNotEquals(filtered.size(), target.getSearchResults().size());
    }

    @Ignore
    public void filterByName() {
        target.setFirstName("test");
        assertEquals(target.getFirstName(), "test");
        target.clickSearch();
        List<WebElement> filtered = target.getSearchResults();
        assertNotEquals(filtered.size(), 0);
        target.setLastName("userone");
        assertEquals(target.getLastName(), "userone");
        target.clickSearch();
        assertNotEquals(filtered.size(), target.getSearchResults().size());
    }

    @Ignore
    public void filterByDate() {
        target.setDate("11/19/2014");
        assertEquals(target.getDate(), "11/19/2014");
        target.clickSearch();
        List<WebElement> filtered = target.getSearchResults();
        assertNotEquals(filtered.size(), 0);
        target.setDate("11/20/2014");
        assertEquals(target.getDate(), "11/20/2014");
        target.clickSearch();
        assertNotEquals(target.getSearchResults().size(), filtered.size());
    }

    @Ignore
    public void filterByStatus() {
        target.setStatus(true);
        assertEquals(target.getStatus(), true);
        target.clickSearch();
        List<WebElement> filtered = target.getSearchResults();
        assertNotEquals(filtered.size(), 0);
        target.setStatus(false);
        assertEquals(target.getStatus(), false);
        target.clickSearch();
        assertNotEquals(target.getSearchResults().size(), filtered.size());
    }

    @Ignore
    public void enableReport() throws InterruptedException {
        target.setStatus(false);
        target.clickSearch();
        int filtered = target.getSearchResults().size();
        target.clickDisable(2);
        target.clickSearch();
        Thread.sleep(5000);
        assertNotEquals(filtered, target.getSearchResults().size());
    }

    @Ignore
    public void disableReport() {
        target.setStatus(true);
        target.clickSearch();
        int filtered = target.getSearchResults().size();
        target.clickDisable(2);
        target.clickSearch();
        assertNotEquals(target.getSearchResults().size(), filtered);
    }

    @Ignore
    public void editReport() {
        assertFalse(target.overlayIsDisplayed());
        target.clickSearch();
        int report = target.getSearchResults().size();
        report = random.nextInt(report - 1) + 1;
        target.clickReport(report);
        assertTrue(target.overlayIsDisplayed());
        target.setApproverComments("test");
        target.clickUpdate();
        driver.switchTo().alert().accept();
        target.clickCancel();
        assertFalse(target.overlayIsDisplayed());
        target.clickReport(report);
        assertEquals(target.getApproverComments(), "test");
    }

    @Ignore
    public void logout() throws InterruptedException {
        assertEquals(driver.getCurrentUrl(),
                "http://localhost:8000/adminreview");
        target.clickLogout();
        driver.navigate().refresh();
        Thread.sleep(5000);
        assertNotEquals(driver.getCurrentUrl(),
                "http://localhost:8000/adminreview");
    }
}
