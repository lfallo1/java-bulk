package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.ConfirmationPage;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.ConfirmationPageSelenium;

/**
 * Functional tests for the confirmation page
 * 
 * @author nPoloway
 */
public class ConfirmationPageTest {

    private ConfirmationPage target;
    private RemoteWebDriver  driver;
    private SeleniumHelper   helper;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverUser(driver);
        target = new ConfirmationPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Ignore
    public void whatHappensIf() {
        assertTrue(true);
    }

    @Ignore
    public void testPromptDisplay() {
        // Get to confirmation page
        helper.createDummyData(driver);
        target.clickSubmit();
        // Assert user is prompted to record confirmation number
        assertTrue(target.promptIsPresent());
    }

    @Ignore
    public void testIdDisplay() {
        // Get to confirmation page
        helper.createDummyData(driver);
        target.clickSubmit();
        // Assert confirmation number is present
        assertTrue(target.idIsPresent());
    }

    @Ignore
    public void testFinishLoginRedirect() {
        // Get to confirmation page
        helper.createDummyData(driver);
        target.clickSubmit();
        assertTrue(target.confirmationTitle());
        // Finish confirmation review, assert login redirect
        target.clickHome();
        assertTrue(target.loginTitle());
    }

    @Ignore
    public void testPreventRedirects() {
        // Get to confirmation page
        helper.createDummyData(driver);
        target.clickSubmit();
        assertTrue(target.confirmationTitle());
        // Attempt navigation to another page
        driver.navigate()
                .to("http://localhost:8000/injuryreporting?employeeId=2&firstName=test&lastName=user1");
        // Assert login redirect
        assertTrue(target.loginTitle());
    }
}
