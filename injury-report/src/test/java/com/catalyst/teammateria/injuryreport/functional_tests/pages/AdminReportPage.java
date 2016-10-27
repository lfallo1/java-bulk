package com.catalyst.teammateria.injuryreport.functional_tests.pages;

/**
 * Interface for the admin report page target
 * 
 * @author gPorter
 */
public interface AdminReportPage {

    /**
     * Return the selected visible text of the body part select box
     * 
     * @return
     */
    public String getBodyPart();

    /**
     * Select a bodypart option by selecting visible text in the bodypart
     * dropdown box
     * 
     * @param bodyPart
     *            - the visible text of the bodypart to be selected
     */
    public void setBodyPart(String bodyPart);

    /**
     * Return the visible text of the selected position in the position dropdown
     * box
     * 
     * @return
     */
    public String getPosition();

    /**
     * Select a position option by selecting visible text in the position
     * dropdown box
     * 
     * @param position
     *            - the visible text of the position to be selected
     */
    public void setPosition(String position);

    /**
     * Return the visible text of the selected injury type in the injury type
     * dropdown box
     * 
     * @return
     */
    public String getInjuryType();

    /**
     * Select an injury type option by selecting visible text in the injury type
     * dropdown box
     * 
     * @param injuryType
     *            - the visible text of the injury type to be selected
     */
    public void setInjuryType(String injuryType);

    /**
     * Return the visible text of the selected employee
     * 
     * @return
     */
    public String getEmployee();

    /**
     * Select an employee option by selecting visible text in the injury type
     * dropdown box
     * 
     * @param employee
     *            - the visible text of the employee to be selected
     */
    public void setEmployee(String employee);

    /**
     * Return the visible text of the selected weather type
     * 
     * @return
     */
    public String getWeather();

    /**
     * Select a weather type option by selecting visible text in the weather
     * type dropdown box
     * 
     * @param weather
     */
    public void setWeather(String weather);

    /**
     * Return the value of the report date jquery date-picker
     * 
     * @return
     */
    public String getReportDate();

    /**
     * Set the value of the report date jquery date-picker
     * 
     * @param date
     *            - string value of the date
     */
    public void setReportDate(String date);

    /**
     * Select the pie chart option
     */
    public void selectPieChart();

    /**
     * Select the bar graph option
     */
    public void selectBarGraph();

    /**
     * Return the visible text of the percent enabled report.
     * 
     * @return
     */
    public String getPercentEnabled();   

    /**
     * Returns the expected visible text of the actual percent enabled of the
     * unfiltered JSON report received from the ajax call without any filters
     * 
     * @return
     */
    public String getPercentEnabledActual();
    
    public String getPercentEnabledBodyPartActual(String optionVisibleText);
    
    public String getPercentEnabledWeatherActual(String optionVisibleText);   
    
}
