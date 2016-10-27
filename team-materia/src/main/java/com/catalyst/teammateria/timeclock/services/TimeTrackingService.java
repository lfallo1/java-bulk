package com.catalyst.teammateria.timeclock.services;

import java.util.List;

import org.joda.time.LocalDate;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;

/**
 * This should be implemented by the Time Tracking Web Service, and used to pull
 * data from the database from week to week as well as the clock in and clock
 * out functionality
 * 
 * @author dGrimes
 */
public interface TimeTrackingService {

    /**
     * This service should accept a Date and return the UserTime objects
     * associated with the time frame requested. </br> </br> The date will be
     * used to get the encompassing week which will in turn send the appropriate
     * range requests to the database
     * 
     * @param session
     * @param date
     *            - any date <em>likely to use Date.now()</em>
     * @return List&lt;UserTime&gt; - Sunday to Saturday
     */
    List<UserTime> getWeek(User user, LocalDate date);

    /**
     * Clock In Method
     * 
     * @param user
     *            - the user to clock in
     */
    void clockIn(User user);

    /**
     * Clock Out Method
     * 
     * @param user
     *            - the user to clock out
     */
    void clockOut(User user);

    /**
     * A method to retrieve the date of the oldest record for a user
     * 
     * @param user
     *            - for whom to gather said date
     */
    LocalDate getOldest(User user);

    /**
     * A method to retrieve the current clock in / clock out status of an
     * individual
     * 
     * @param user
     *            - the individual
     * @return their current status (true - in, false - out);
     */
    boolean currentlyOpenTimeTicket(User user);
}
