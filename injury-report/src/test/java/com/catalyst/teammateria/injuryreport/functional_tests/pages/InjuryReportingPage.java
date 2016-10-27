package com.catalyst.teammateria.injuryreport.functional_tests.pages;

public interface InjuryReportingPage {

    /**
     * Set the value of the date of injury date picker input field
     * 
     * @param dateOfInjury
     */
    public void setDateOfInjury(String dateOfInjury);

    /**
     * Get the value of the date of injury date picker input field
     * 
     * @return
     */
    public String getDateOfInjury();

    /**
     * Set the value of the date reported to manager date picker input field as
     * mm/dd/yyyy
     * 
     * @param dateReportedToManager
     */
    public void setDateReportedToManager(String dateReportedToManager);

    /**
     * Get the value of the date reported to manager date picker input field as
     * mm/dd/yyyy
     * 
     * @return
     */
    public String getDateReportedToManager();

    /**
     * Select an option in the weather dropdown box
     * 
     * @param weatherVisibleText
     *            - the visible text of the option to be selected
     */
    public void setWeather(String weatherVisibleText);

    /**
     * Return the visible text of the selected weather option
     * 
     * @return
     */
    public String getWeather();

    /**
     * Select an option in the injury type dropdown box
     * 
     * @param injuryType
     *            - the visible text of the option to be selected
     */
    public void setInjuryType(String injuryType);

    /**
     * Get the visible text of the selected injury type option
     * 
     * @return
     */
    public String getInjuryType();

    /**
     * Select an option in the bodypart dropdown box
     * 
     * @param bodyPart
     *            - the visible text of the option to be selected
     */
    public void setBodyPart(String bodyPart);

    /**
     * Return the visible text of the selected body part
     * 
     * @return
     */
    public String getBodyPart();

    /**
     * Set the time of injury time picker value
     * 
     * @param timeOfInjury
     *            - the time of injury in 24-hour format, 00:00
     */
    public void setTimeOfInjury(String timeOfInjury);

    /**
     * Get the time of injury
     * 
     * @return
     */
    public String getTimeOfInjury();

    /**
     * Sets the value of the description input text field
     * 
     * @param description
     */
    public void setDescription(String description);

    /**
     * Get the value of the description input text field
     * 
     * @return
     */
    public String getDescription();

    /**
     * Click the next button
     */
    public void clickNext();

    /**
     * Returns true if the injury report is visible, else false
     * 
     * @return
     */
    public boolean injuryReportVisible();

    public String getReportEmployeeName();

    public String getReportEmployeeId();

    public String getReportUser();

    public String getReportUserId();

    public String getReportDateOfInjury();

    /**
     * Return the displayed date reported to management in the injured employee
     * report
     * 
     * @return
     */
    public String getReportDateReported();
    
    public String getReportDateFiled();
    
    public String getReportWeatherConditions();
    
    public String getReportTypeOfInjury();
    
    public String getReportBodyPart();
    
    public String getReportTimeOfInjury();
    
    public String getReportDescription();
    
    /**
     * Submit the injury report from the report summary
     */
    public void clickSubmit();
    
    /**
     * Returns true if on confirmation page, else false
     * @return
     */
    public boolean onConfirmationPage();
}