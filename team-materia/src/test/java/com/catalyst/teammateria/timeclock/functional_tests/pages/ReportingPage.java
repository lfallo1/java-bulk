package com.catalyst.teammateria.timeclock.functional_tests.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * Defines basic functionality for the admin reporting page
 * 
 * @author nPoloway
 */
public interface ReportingPage {

    /**
     * Clicks the total hours by week button
     */
    public void clickHoursByWeek();

    /**
     * Clicks the overtime button
     */
    public void clickOvertime();

    /**
     * clicks the absent button
     */
    public void clickAbsent();

    /**
     * Returns whether the date dropdown is enabled
     * 
     * @return true if enabled
     */
    public Boolean dateEnabled();

    /**
     * Returns whether the users dropdown is disabled
     * 
     * @return true if enabled
     */
    public Boolean usersEnabled();

    /**
     * Get currently selected index of the date dropdown
     * 
     * @return selected index
     */
    public String getWeek();

    /**
     * Selects specified selected index for the date dropdown
     * 
     * @param index
     *            index to select
     */
    public void selectWeek(Integer index);

    /**
     * Selects specified selected index for the users dropdown
     * 
     * @param index
     *            index to select
     */
    public void selectUser(Integer index);

    /**
     * Gets all currently available options from date dropdown
     * 
     * @return List of weeks
     */
    public List<WebElement> weekList();

    /**
     * Gets number of rows in the currently displayed table
     * 
     * @return number of
     *         <tr>
     *         tags
     */
    public Integer getTableSize();

    /**
     * Gets contents of specified <td>tag in String form
     * 
     * @param tableRow
     *            <tr>
     *            number
     * @param tableColumn
     *            <td> number
     * @return String representation of <td> tag
     */
    public String getCellText(int tableRow, int tableColumn);

    /**
     * Gets title of currently selected report page
     * 
     * @return String report title
     */
    public String getActiveReport();

    /**
     * Tells whether the tooltip shows up for a given user
     * <tr>
     * tag
     * 
     * @param row
     *            specified which user row should be checked
     * @return True if tooltip works
     */
    public Boolean mouseOverShow(Integer row);

    /**
     * Tells whether specified user was absent on a specified day
     * 
     * @param tableRow
     *            ` represents user specification
     * @param tableColumn
     *            represents weekday specification
     * @return True if user was absent on that day
     */
    public Boolean wasAbsent(int tableRow, int tableColumn);
}
