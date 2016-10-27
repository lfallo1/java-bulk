package com.catalyst.teammateria.injuryreport.functional_tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.AdminReportPage;
import com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium.AdminReportPageSelenium;
import com.catalyst.teammateria.injuryreport.service.ReportingService;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "../../../../../../src/main/webapp/WEB-INF/web.xml" })
public class AdminReportTest {

    private static final String VALID_DATE = "11/10/2014";

    private static final String VALID_WEATHER_VISIBLE_TEXT = "Rain";

    private static final String VALID_INJURY_TYPE_VISIBLE_TEXT = "Cut";

    private static final String VALID_POSITION_VISIBLE_TEXT = "Management";

    private static final String USER_ONE_VISBLE_TEXT = "userone, test";

    private static final String VALID_BODYPART_VISIBLE_TEXT = "Arm";

    private RemoteWebDriver driver;

    private SeleniumHelper helper;

    private AdminReportPage target;

    @Autowired
    ReportingService reportingService;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        // Login as an administrator
        helper = new SeleniumHelper();
        helper.loginDriverAdmin(driver);
        // Navigate to reporting page
        target = new AdminReportPageSelenium(driver);
    }

    @After
    public void tearDown() {
        this.driver.close();
    }

    @Test
    public void testInput() {
        target.setBodyPart(VALID_BODYPART_VISIBLE_TEXT);
        assertEquals(target.getBodyPart(), VALID_BODYPART_VISIBLE_TEXT);
        target.setEmployee(USER_ONE_VISBLE_TEXT);
        assertEquals(target.getEmployee(), USER_ONE_VISBLE_TEXT);
        target.setPosition(VALID_POSITION_VISIBLE_TEXT);
        assertEquals(target.getPosition(), VALID_POSITION_VISIBLE_TEXT);
        target.setInjuryType(VALID_INJURY_TYPE_VISIBLE_TEXT);
        assertEquals(VALID_INJURY_TYPE_VISIBLE_TEXT, target.getInjuryType());
        target.setWeather(VALID_WEATHER_VISIBLE_TEXT);
        assertEquals(target.getWeather(), VALID_WEATHER_VISIBLE_TEXT);
        target.setReportDate(VALID_DATE);
        assertEquals(VALID_DATE, target.getReportDate());
    }

    // The pie chart and bar graph should return the same data
    @Test
    public void testBarAndPie() {
        String chartPercentEnabled = target.getPercentEnabled();
        target.selectBarGraph();
        String barPercentEnabled = target.getPercentEnabled();
        assertEquals(chartPercentEnabled, barPercentEnabled);
    }

    // Test that the report is delivering accurate results without any filters
    @Test
    public void testReportAccuracy() {
        String reportedEnabled = target.getPercentEnabled();
        String actualEnabled = target.getPercentEnabledActual();
        assertEquals(reportedEnabled, actualEnabled);
    }

    // Test that the report is delivering accurate results with filters
    @Test
    public void testBodyPartFilter() {
        target.setBodyPart(VALID_BODYPART_VISIBLE_TEXT);
        String displayedPercentEnabled = target.getPercentEnabled();
        String actualPercentEnabled = target
                .getPercentEnabledBodyPartActual("Arm");
        assertEquals(displayedPercentEnabled, actualPercentEnabled);
    }

    @Test
    public void testWeatherFilter() {
        target.setWeather(VALID_WEATHER_VISIBLE_TEXT);
        String displayedPercentEnabled = target.getPercentEnabled();
        String actualPercentEnabled = target
                .getPercentEnabledWeatherActual(VALID_WEATHER_VISIBLE_TEXT);
        assertEquals(displayedPercentEnabled, actualPercentEnabled);
    }
}
