package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.InjuryReportingPage;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.InjuryReportingPageSelenium;

public class InjuryReportingTest {

    private static final String DATE_OF_INJURY_VALID = "11/18/2014";

    private static final String DATE_REPORTED_VALID = "11/19/2014";

    private static final String VALID_TIME = "01:30";

    private static final String DESCRIPTION_VALID = "something happened";

    private static final String BODYPART_VALID = "Head";

    private static final String INJURY_VALID = "Cut";

    private static final String WEATHER_VALID = "Clear";

    private static final String TEST_USER_ID = "3";

    private static final String TEST_USER_FULL_NAME = "test usertwo";

    private static final String TEST_EMPLOYEE_ID = "2";

    private static final String TEST_EMPLOYEE_FULLNAME = "test userone";

    private RemoteWebDriver driver;

    private SeleniumHelper helper;

    private InjuryReportingPage target;

    @Before
    public void setup() {
        driver = new FirefoxDriver();
        // Login in as a user
        helper = new SeleniumHelper();
        helper.loginDriverUser(driver);
        target = new InjuryReportingPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void testInputFields() {
        target.setDateOfInjury(DATE_OF_INJURY_VALID);
        assertEquals(DATE_OF_INJURY_VALID, target.getDateOfInjury());
        target.setDateReportedToManager(DATE_REPORTED_VALID);
        assertEquals(DATE_REPORTED_VALID, target.getDateReportedToManager());
        target.setWeather(WEATHER_VALID);
        assertEquals(target.getWeather(), WEATHER_VALID);
        target.setInjuryType(INJURY_VALID);
        assertEquals(target.getInjuryType(), INJURY_VALID);
        target.setBodyPart(BODYPART_VALID);
        assertEquals(BODYPART_VALID, target.getBodyPart());
        target.setTimeOfInjury(VALID_TIME);
        assertEquals(VALID_TIME, target.getTimeOfInjury());
        target.setDescription(DESCRIPTION_VALID);
        assertEquals(target.getDescription(), DESCRIPTION_VALID);
    }

    @Test
    public void testReportVisibility() {
        assertFalse(target.injuryReportVisible());
        target.setWeather(WEATHER_VALID);
        target.setInjuryType(INJURY_VALID);
        target.setBodyPart(BODYPART_VALID);
        target.setDescription(DESCRIPTION_VALID);
        target.setTimeOfInjury(VALID_TIME);
        target.clickNext();
        assertTrue(target.injuryReportVisible());
    }

    @Test
    public void testIncompleteReport() {
        target.clickNext();
        assertFalse(target.injuryReportVisible());
    }

    @Test
    public void testReportAccuracy() {
        // Add valid information
        target.setDateOfInjury(DATE_OF_INJURY_VALID);
        target.setWeather(WEATHER_VALID);
        target.setInjuryType(INJURY_VALID);
        target.setBodyPart(BODYPART_VALID);
        target.setDescription(DESCRIPTION_VALID);
        target.setTimeOfInjury(VALID_TIME);
        target.clickNext();
        // Assert that the correct information is in the report summary
        assertEquals(DATE_OF_INJURY_VALID, target.getReportDateOfInjury());
        assertEquals(TEST_EMPLOYEE_FULLNAME, target.getReportEmployeeName());
        assertEquals(TEST_EMPLOYEE_ID, target.getReportEmployeeId());
        assertEquals(TEST_USER_FULL_NAME, target.getReportUser());
        assertEquals(TEST_USER_ID, target.getReportUserId());
        assertEquals(VALID_TIME, target.getReportTimeOfInjury());
        assertEquals(WEATHER_VALID, target.getReportWeatherConditions());
        assertEquals(INJURY_VALID, target.getReportTypeOfInjury());
        assertEquals(BODYPART_VALID, target.getReportBodyPart());
        assertEquals(DESCRIPTION_VALID, target.getReportDescription());
    }

    @Test
    public void testConfirmationRedirect() {
        // Add valid information
        target.setDateOfInjury(DATE_OF_INJURY_VALID);
        target.setWeather(WEATHER_VALID);
        target.setInjuryType(INJURY_VALID);
        target.setBodyPart(BODYPART_VALID);
        target.setDescription(DESCRIPTION_VALID);
        target.setTimeOfInjury(VALID_TIME);
        target.clickNext();
        // Submit the form
        target.clickSubmit();
        // Assert that the redirect occured
        assertTrue(target.onConfirmationPage());
    }
}
