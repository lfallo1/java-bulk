package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.EmployeeSearchPage;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.EmployeeSearchPageSelenium;

public class EmployeeSearchTest {

    private static final String INVALID_NAME = "1";

    private static final String TEST_FIRST_NAME = "test";

    @SuppressWarnings ("unused")
    private static final String USER1_VISIBLE_TEXT = "userone, test";

    private EmployeeSearchPage target;

    private RemoteWebDriver driver;

    private SeleniumHelper helper;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverUser(driver);
        target = new EmployeeSearchPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void testGetAll() {
        // Click on lookup to return all employees
        target.clickLookup();
        // Assert that user1 and user2 both showed up
        assertTrue(target.employee1Visible());
    }

    @Test
    public void testSearchByFirstName() {
        // There are two users with the first name 'test', user1 and user 2
        target.setFirstName(TEST_FIRST_NAME);
        target.clickLookup();
        // Assert that both user 1 and user 2 showed up
        assertTrue(target.employee1Visible());
    }

    @Test
    public void testSearchByLastName() {
        // Only user1 has a first name of 'user1'
        target.setlastName("userone");
        target.clickLookup();
        // Assert that 'user1' showed up and 'user2' did not
        assertTrue(target.employee1Visible());
        assertFalse(target.employee3Visible());
    }

    @Test
    public void testInjuryReportRedirect() {
        target.clickLookup();
        target.clickEmployee1();
        assertTrue(target.onInjuryReportingTitle());
    }

    // Users cannot report an injury on themselves
    @Test
    public void testInvalidEmployee() {
        // Search for the logged in user
        target.setlastName("usertwo");
        target.clickLookup();
        // Assert that the logged in user did not show up
        assertFalse(target.employee2Visible());
    }

    // Users cannot navigate to their own injury reporting page
    @Test
    public void testInjuryReportInvalidUrl() {
        // Navigate to the injury reporting page for the logged in user
        driver.get("http://localhost:8000/injuryreporting?employeeId=3&firstName=test&lastName=usertwo");
        driver.navigate().refresh();
        // Assert that the user is still on
        assertTrue(target.onEmployeeSearchTitle());
    }

    @Test
    public void testErrors() {
        // An error message should appear when the user searches for an invalid
        // first name
        target.setFirstName(INVALID_NAME);
        target.clickLookup();
        assertTrue(target.isErrorMessagePresent());
        // An error message should appear when the user searches for an invalid
        // last name
        target.setlastName(INVALID_NAME);
        target.clickLookup();
        assertTrue(target.isErrorMessagePresent());
        // An error message should appear when the user searches for an invalid
        // employee number
        target.setId("bar");
        target.clickLookup();
        assertTrue(target.isErrorMessagePresent());
    }
}
