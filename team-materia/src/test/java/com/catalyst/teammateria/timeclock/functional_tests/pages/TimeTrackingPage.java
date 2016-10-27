package com.catalyst.teammateria.timeclock.functional_tests.pages;

/**
 * Defines basic functionality for the user time tracking page
 * 
 * @author nPoloway
 */
public interface TimeTrackingPage {

    /**
     * Clock in current user given an enabled "clock in" button
     */
    public void clockIn();

    /**
     * Clock out current user given an enabled "clock out" button
     */
    public void clockOut();

    /**
     * View previous week of clocked hours for the current user, given the
     * presence of clocked hours in the previous week
     */
    public void previousWeek();

    /**
     * View next week of clocked hours for the current user, given the next week
     * has already occurred
     */
    public void nextWeek();

    /**
     * Get the beginDate value for the currently displayed week
     * 
     * @return beginDate
     */
    public String getWeekBegin();

    /**
     * Returns whether Clock In or Clock Out button is enabled
     * 
     * @return
     */
    public String getButtonValue();

    /**
     * Returns true if the next button is enabled
     * 
     * @return
     */
    public Boolean nextEnabled();

    /**
     * Returns true if the previous button is enabled
     * 
     * @return
     */
    public Boolean previousEnabled();

    /**
     * Get a collection of each number of hours worked by day
     * 
     * @return List of hours worked in a week
     */
    public Float getHours();
}
