package com.catalyst.teammateria.timeclock.functional_tests.pages;

import java.util.List;

/**
 * Defines basic functionality for the admin time management page
 * 
 * @author nPoloway
 */
public interface TimeManagementPage {

    /**
     * Gets user's name from the dropdown of available users
     * 
     * @return User's first and last name
     */
    public String getUserIndex();

    /**
     * Selects user's name given availability in the dropdown for users
     * 
     * @param firstName
     *            User's first name
     * @param lastName
     *            User's last name
     */
    public void selectUserName(Integer index);

    /**
     * Select a user in the user dropdown box by their visible text
     * 
     * @param user
     *            - the visible text of the user in the dropdown box
     */
    public void selectUserByVisibleText(String user);

    /**
     * Select a week from the week dropdown box by the visible text
     * 
     * @param week
     *            - the visible text of the week in the dropdown box
     */
    public void selectWeekByVisibleText(String week);

    /**
     * Returns the visible text of the selected user in the user drop down box
     * 
     * @return
     */
    public String getUserVisibleText();

    /**
     * Returns whether show all or show active is enabled
     * 
     * @return True if inactive users are shown, false otherwise
     */
    public Boolean getActiveShown();

    /**
     * Clicks the inactiveButton, toggles whether inactive users are shown or
     * not
     */
    public void toggleActiveShown();

    /**
     * Gets selected value from the week dropdown
     * 
     * @return selected week
     */
    public String getWeek();

    /**
     * Select week given availability in the week dropdown
     * 
     * @param week
     *            week to select
     */
    public void selectWeek(Integer index);

    /**
     * Gets value from clockInTime field
     * 
     * @return clock in time
     */
    public String getFirstClockIn();

   /**
    * Sets the value for the clock in field 
    * @param time
    * @param row - the row 
    */
    public void setFirstClockIn(String time);

    /**
     * Gets value from clockOutTime field
     * 
     * @return clock out time
     */
    public String getFirstClockOut();

    /**
     * Sets value for clockOutTime field
     * 
     * @param time
     *            time to be set for clock out
     */
    public void setFirstClockOut(String time);

    /**
     * Gets value from clockDate field
     * 
     * @return displayed date
     */
    public String getDate();

    /**
     * Sets value for clockDate field
     * 
     * @param date
     *            date to be set for clockDate
     */
    public void setDate(String date);

    /**
     * Clicks update button to change displayed clock in and clock out values
     */
    public void clickUpdate();

    /**
     * Clicks cancel button to return admin to splash page
     */
    public void clickCancel();

    /**
     * Gets all currently available options from user dropdown
     * 
     * @return List of user names
     */
    public List<String> userList();

    /**
     * Gets all currently available options from week dropdown
     * 
     * @return List of weeks
     */
    public List<String> weekList();

    /**
     * Returns true if the visible text is seen in the week dropdown box
     * 
     * @param week
     *            - the visible text of the week
     * @return
     */
    public boolean weekPresent(String week);
}
