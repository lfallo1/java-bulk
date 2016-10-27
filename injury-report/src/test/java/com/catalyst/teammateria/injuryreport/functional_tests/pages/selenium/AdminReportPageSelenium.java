package com.catalyst.teammateria.injuryreport.functional_tests.pages.selenium;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.json.JSONException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.catalyst.teammateria.injuryreport.functional_tests.pages.AdminReportPage;

public class AdminReportPageSelenium implements AdminReportPage {

    private static final String WEATHER_ID_JSON = "weatherId";

    private static final String WEATHER_JSON_OBJECT = "weather";

    private static final String BODY_PART_ID_JSON = "bodyPartId";

    private static final String BODYPART_JSON_OBJECT = "bodypart";

    private static final String SELECT_EMPLOYEE_ID = "employee";

    private static final String SELECT_INJURY_TYPE_ID = "injury-type";

    private static final String SELECT_POSITION_ID = "employee-position";

    private static final String SELECT_BODY_PART_ID = "body-part";

    private static final String WEATHER_TYPE_SELECT_ID = "weather-type";

    private static final String SELECT_BAR_GRAPH_ID = "bar-graph";

    private static final String SELECT_PIE_CHART_ID = "pie-chart";

    private static final String DATE_OF_INJURY_ID = "report-date";

    WebDriver driver;

    WebDriverWait waitDriver;

    public AdminReportPageSelenium(RemoteWebDriver driver) {
        this.driver = driver;
        this.waitDriver = new WebDriverWait(driver, 20);
        this.driver.get("http://localhost:8000/adminreport");
    }

    @Override
    public String getBodyPart() {
        return getFieldVisibleText(SELECT_BODY_PART_ID);
    }

    @Override
    public void setBodyPart(String bodyPart) {
        setFieldVisibleText(SELECT_BODY_PART_ID, bodyPart);
    }

    @Override
    public String getPosition() {
        return getFieldVisibleText(SELECT_POSITION_ID);
    }

    @Override
    public void setPosition(String position) {
        setFieldVisibleText(SELECT_POSITION_ID, position);
    }

    @Override
    public String getInjuryType() {
        return getFieldVisibleText(SELECT_INJURY_TYPE_ID);
    }

    @Override
    public void setInjuryType(String injuryType) {
        setFieldVisibleText(SELECT_INJURY_TYPE_ID, injuryType);
    }

    @Override
    public String getEmployee() {
        return getFieldVisibleText(SELECT_EMPLOYEE_ID);
    }

    @Override
    public void setEmployee(String employee) {
        setFieldVisibleText(SELECT_EMPLOYEE_ID, employee);
    }

    @Override
    public String getWeather() {
        return getFieldVisibleText(WEATHER_TYPE_SELECT_ID);
    }

    @Override
    public void setWeather(String weather) {
        setFieldVisibleText(WEATHER_TYPE_SELECT_ID, weather);
    }

    @Override
    public String getReportDate() {
        WebElement element = driver.findElement(By.id(DATE_OF_INJURY_ID));
        return element.getAttribute("value");
    }

    @Override
    public void setReportDate(String date) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .id(DATE_OF_INJURY_ID))));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("$('#" + DATE_OF_INJURY_ID + "').val('" + date + "');");
    }

    

    @Override
    public void selectPieChart() {
        this.waitDriver.until(
                ExpectedConditions.elementToBeClickable(this.driver
                        .findElement(By.id(SELECT_PIE_CHART_ID)))).click();
    }

    @Override
    public void selectBarGraph() {
        this.waitDriver.until(
                ExpectedConditions.elementToBeClickable(this.driver
                        .findElement(By.id(SELECT_BAR_GRAPH_ID)))).click();
    }

    @Override
    public String getPercentEnabled() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id("chart"))));
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            assert false;
        }
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By
                        .cssSelector("text"))));        
        return driver.findElement(By.cssSelector("text")).getText();
    }
    
    @Override
    public String getPercentEnabledActual() {
        // Get the report list as JSON from the javascript executor
        JSONArray jsonArr = getReportList();
        // Iterate over the list, counting the number of enabled reports
        int trueCount = 0;
        for (int i = 0; i < jsonArr.length(); i++ ) {
            try {
                JSONObject currReport = new JSONObject(jsonArr.get(i)
                        .toString());
                if (currReport.getString("enabled").equals("true")) {
                    trueCount++ ;
                }
            } catch (JSONException e) {
                assert false;
                return "";
            }
        }
        // Determine the percent enabled
        Double retLong = 100 * (double) (trueCount) / (double)jsonArr.length();
        // Format and return the percentage as a string
        retLong = (double)Math.round(retLong * 10);
        retLong = retLong / 10;
        return "" + retLong + "%";
    }

    @Override
    public String getPercentEnabledBodyPartActual(String optionVisibleText) {
        // Determine the id of the selectBox
        Integer optionValue = getIdByVisibleTextAndSelectBoxId(
                SELECT_BODY_PART_ID, optionVisibleText);
        // Determine the accurate percent disabled using the JSON dataset
        // filtered for the given body part
        Double percentEnabled = calculatePercentEnabledWithFilter(optionValue,
                BODYPART_JSON_OBJECT, BODY_PART_ID_JSON);
        // Format and return the percentage as a string
        return "" + percentEnabled + "%";
    }

    @Override
    public String getPercentEnabledWeatherActual(String optionVisibleText) {
        // Determine the id of the selectBox
        Integer optionValue = getIdByVisibleTextAndSelectBoxId(
                WEATHER_TYPE_SELECT_ID, optionVisibleText);
        // Determine the accurate percent disabled using the JSON dataset
        // filtered for the given weather type
        Double percentEnabled = calculatePercentEnabledWithFilter(optionValue,
                WEATHER_JSON_OBJECT, WEATHER_ID_JSON);
        // Format and return the percentage as a string
        return "" + percentEnabled + "%";
    }

    private Double calculatePercentEnabledWithFilter(Integer optionValue,
            String jsonObjectKey, String jsonObjectIdKey) {
        JSONArray jsonArr = getReportList();
        // Iterate over the list, counting the number of enabled reports
        int trueCount = 0;
        int totalCount = 0;
        // Iterate over the dataset
        for (int i = 0; i < jsonArr.length(); i++ ) {
            try {
                JSONObject currReport = new JSONObject(jsonArr.get(i)
                        .toString());
                JSONObject currObject = currReport.getJSONObject(jsonObjectKey);                
                Integer currObjectId = Integer.parseInt(currObject
                        .getString(jsonObjectIdKey));
                // If the option matches the searched option, increment count
                // accordingly
                if (optionValue.equals(currObjectId)) {
                    if (currReport.getString("enabled").equals("true")) {
                        trueCount++ ;
                        totalCount++ ;
                    } else if (currReport.getString("enabled").equals("false")) {
                        totalCount++ ;
                    }
                }
            } catch (JSONException e) {
                assert false;
                return -1d;
            }
        }
        // Determine the percent enabled
        Double percentEnabled = 100 * (double) (trueCount)
                / (double) (totalCount);
        percentEnabled = (double)Math.round(percentEnabled * 10);
        percentEnabled = percentEnabled / 10;
        return percentEnabled;
    }

    public Integer getIdByVisibleTextAndSelectBoxId(String id,
            String visibleText) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id(id))));
        Select select = new Select(driver.findElement(By.id(id)));
        List<WebElement> optionList = select.getOptions();
        for (int i = 0; i < optionList.size(); i++ ) {
            WebElement currOption = optionList.get(i);
            if (visibleText.equals(currOption.getText())) {
                return Integer.parseInt(currOption.getAttribute("value"));
            }
        }
        return -1;
    }
    
    private String getFieldVisibleText(String id) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id(id))));
        Select select = new Select(driver.findElement(By.id(id)));
        return select.getFirstSelectedOption().getText();
    }

    private void setFieldVisibleText(String id, String text) {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id(id))));
        Select select = new Select(driver.findElement(By.id(id)));
        select.selectByVisibleText(text);
    }
    
    public JSONArray getReportList() {
        this.waitDriver.until(ExpectedConditions
                .elementToBeClickable(this.driver.findElement(By.id("chart"))));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        @SuppressWarnings ("unchecked")
        List<Object> reports = (List<Object>)js.executeScript("return dataset");
        JSONArray jsonArr = new JSONArray(reports);
        return jsonArr ;
    }

}