package com.catalyst.teammateria.timeclock.functional_tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.catalyst.teammateria.timeclock.functional_tests.pages.ReportingPage;
import com.catalyst.teammateria.timeclock.functional_tests.pages.selenium.ReportingPageSelenium;

public class ReportingTest {

    private static final int    EIGHT               = 8;
    private static final int    SIX                 = 6;
    private static final int    FOUR                = 4;
    private static final int    THREE               = 3;
    private ReportingPage       target;
    private RemoteWebDriver     driver;
    private SeleniumHelper      helper;
    private static final String REPORTING_URL       = "http://localhost:8000/reporting";
    private static final String EMPTY               = "";
    private static final String TOTAL_HOURS_BY_WEEK = "Total hours by week";
    private static final String OVERTIME            = "Overtime";
    private static final String ABSENT              = "Absent";
    private static final String TO                  = "to";
    private static final String SLASH               = "/";

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
        target = new ReportingPageSelenium(driver);
        driver.navigate().to(REPORTING_URL);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void initialTest() {
        // Check that no reports are initially selected, dropdowns are disabled
        assertEquals(EMPTY, target.getActiveReport());
        assertFalse(target.dateEnabled());
        assertFalse(target.usersEnabled());
    }

    @Test
    public void hoursReportTest() {
        // Select hours by week report, check related functionality
        target.clickHoursByWeek();
        assertEquals(TOTAL_HOURS_BY_WEEK, target.getActiveReport());
        // Check that selected date affects table information
        assertTrue(target.dateEnabled());
        String info = target.getCellText(1, 1);
        target.selectWeek(FOUR);
        assertNotEquals(info, target.getCellText(1, 1));
        // Check that selected user affects table information
        assertTrue(target.usersEnabled());
        Integer temp = target.getTableSize();
        target.selectUser(1);
        assertNotEquals(temp, target.getTableSize());
    }

    @Test
    public void overtimeReportTest() {
        // Select overtime report, check related functionality
        target.clickOvertime();
        assertEquals(OVERTIME, target.getActiveReport());
        // Check that selected date affects table information
        assertTrue(target.dateEnabled());
        Integer temp = target.getTableSize();
        target.selectWeek(THREE);
        assertNotEquals(temp, target.getTableSize());
        // Check that users dropdown is disabled for overtime report
        assertFalse(target.usersEnabled());
    }

    @Test
    public void absentReportTest() {
        // Select absent report, check related functionality
        target.clickAbsent();
        assertEquals(ABSENT, target.getActiveReport());
        // Check that selected week affects table information
        assertTrue(target.dateEnabled());
        String info = target.getCellText(1, 1);
        target.selectWeek(2);
        assertNotEquals(info, target.getCellText(1, 1));
        // Check that selected user affects table information
        assertTrue(target.usersEnabled());
        Integer temp = target.getTableSize();
        target.selectUser(2);
        assertNotEquals(temp, target.getTableSize());
    }

    // Asserts user information is displayed
    @Test
    public void hoverBeautifulNightmareTest() {
        target.clickAbsent();
        assertTrue(target.mouseOverShow(0));
        target.clickHoursByWeek();
        assertTrue(target.mouseOverShow(0));
        target.clickOvertime();
        assertTrue(target.mouseOverShow(0));
    }

    // Asserts ALL VALUES in the total hours column of the overtime report are
    // greater than 8
    @Test
    public void overtimeOverEightTest() {
        target.clickOvertime();
        int weeks = target.weekList().size();
        int table = 0;
        for (int i = 0; i < weeks; i++ ) {
            target.selectWeek(i);
            table = target.getTableSize();
            for (int j = 1; j < table; j++ ) {
                // Assert that over eight hours are clocked
                assertTrue(Double.parseDouble(target.getCellText(j, 2)) >= EIGHT);
                // Assert associated date is displayed
                assertNotNull(target.getCellText(j, 1));
            }
        }
    }

    // Assert no hours are displayed if a user was absent on the specified day
    @Test
    public void absentCheckTest() {
        target.clickAbsent();
        for (int j = 1; j < SIX; j++ ) {
            if (target.wasAbsent(1, j)) {
                assertEquals(EMPTY, target.getCellText(1, j));
            }
        }
    }

    // Assert total hours values are being displayed in table
    @Test
    public void showTotalsTest() {
        target.clickHoursByWeek();
        for (int i = 1; i < target.getTableSize(); i++ ) {
            assertTrue(Double.parseDouble(target.getCellText(i, 1)) >= 0);
        }
    }

    // Assert that specifying a user in the user dropdown will result in a one
    // row table
    @Test
    public void userSelectTest() {
        target.clickHoursByWeek();
        int table = target.getTableSize();
        target.selectUser(2);
        assertTrue(table > target.getTableSize());
        Integer test = 2;
        assertEquals(test, target.getTableSize());
    }

    // Assert each option in week dropdown represents one full week, displays
    // that week to table
    @Test
    public void checkWeekTest() {
        target.clickHoursByWeek();
        String range = target.getWeek();
        String[] dates = range.split(TO);
        String[] numbers1 = dates[0].split(SLASH);
        int day1 = Integer.parseInt(numbers1[1]);
        String[] numbers2 = dates[1].split("/");
        int day2 = Integer.parseInt(numbers2[1]);
        assertEquals( (day2 - day1), SIX);
    }
}
