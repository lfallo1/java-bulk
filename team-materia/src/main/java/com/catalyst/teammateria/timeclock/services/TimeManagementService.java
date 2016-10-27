package com.catalyst.teammateria.timeclock.services;

import java.util.List;

import org.joda.time.LocalDate;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.formbeans.TimeManagementForm;

/**
 * Implemented by the Time Management Web Service, and used to pull data from
 * the database for the hours worked for the user selected
 * 
 * @author aDietrich
 */
public interface TimeManagementService {

    /**
     * Returns a list of dates for the page to parse into weeks
     * 
     * @param userId
     *            the userId of the User to send to the UserTimeDao
     * @return List&lt;LocalDate&gt;
     */
    List<LocalDate> getDays(int user);

    /**
     * This method returns a list of user time objects for the user in question
     * 
     * @param userId
     * @param week
     *            (date that begins the week)
     * @return list of objects for the user
     */
    List<UserTime> getUserTime(int userId, long week);

    /**
     * This method updates the usertime objects located on the time management
     * page
     * 
     * @param timeManagementForm
     *            - the wrapper for all usertime objects
     * @param userId
     *            - user of these usertime objects
     * @return a String of validation errors or true if it passes
     */
    String updateUserTime(TimeManagementForm timeManagementForm, int userId);

    /**
     * Runs a series of checks per object. Loops through the list and checks to
     * see if there are errors: <br>
     * &bull; VS. self <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&bull; clockIn == clockOut <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&bull; clockIn &gt; clockOut <br>
     * &bull; VS. list <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&bull; list.clockIn &lt; this.clockIn &lt;
     * list.clockOut <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&bull; list.clockIn &lt; this.clockOut &lt;
     * list.clockOut <br>
     * &nbsp;&nbsp;&nbsp;&nbsp;&bull; this.clockIn &lt; list.clockIn &lt;
     * list.clockOut &lt; this.clockOut
     * 
     * @param timeManagementForm
     *            the form to validate @return a String of errors or an empty
     *            one if there are none
     */
    String validation(TimeManagementForm timeManagementForm);
    
    /**
     * Checks for empty or negative time values
     * @param timeManagementForm
     * @param i
     */
    void checkForEmptyOrNegativeTime(TimeManagementForm timeManagementForm, int i);
    
    /**
     * Appends errors with time values to an error string
     * @param timeManagementForm
     * @param i
     */
    void appendErrorsForUserTimes(TimeManagementForm timeManagementForm, int i);
    
    /**
     * Checks for clockIn and clockOut times within other clockIn and clockOut times
     * @param timeManagementForm
     * @param i
     * @param j
     */
    void entrySurroundsEntry(TimeManagementForm timeManagementForm, int i, int j);
    
    /**
     * Checks for intersecting times based on clockOut time
     * @param timeManagementForm
     * @param i
     * @param j
     */
    void clockOutIntersectsEntry(TimeManagementForm timeManagementForm, int i, int j);
    
    /**
     * Checks for intersecting times based on clockIn time
     * @param timeManagementForm
     * @param i
     * @param j
     */
    void clockInIntersectsEntry(TimeManagementForm timeManagementForm, int i, int j);
    
}
