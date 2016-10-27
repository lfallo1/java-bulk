package com.catalyst.teammateria.timeclock.dao;

import java.util.List;

import org.joda.time.LocalDate;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;

/**
 * Defines basic database operations for UserTime DAO Create (persist) new
 * UserTime object in database. Updates an existing UserTime object and merges
 * it back to database
 * 
 * @author aDietrich
 */
public interface UserTimeDao {

    /**
     * Add new UserTime in database
     * 
     * @param userTime
     */
    void addUserTime(UserTime userTime);

    /**
     * Save changes made to a UserTime
     * 
     * @param userTime
     * @return
     */
    void updateUserTime(UserTime userTime);

    /**
     * Return a list of UserTime objects for a given user between the specified
     * dates
     * 
     * @param startDate
     * @param endDate
     * @param user
     * @return
     */
    List<UserTime> getUserTimeObjectsBetweenDates(LocalDate startDate,
            LocalDate endDate, User user);

    /**
     * Return the oldest recorded date for a given user
     * 
     * @param user
     *            - the user for whom to find the oldest record
     * @return Date - oldest record for the individual in database
     */
    LocalDate getOldestRecord(User user);

    /**
     * Return the UserTime object that lacks a clock out time from the user
     * listed
     * 
     * @param user
     *            - the user to check
     * @return the still open UserTime object
     */
    UserTime getUserTimeByStillOpen(User user);

    /**
     * Return whether there is a UserTime object that lacks a clock out time
     * from the user
     * 
     * @param user
     *            - the user to check
     * @return whether there is an ticket or not
     */
    boolean checkOpenTicketStatus(User user);

    /**
     * Returns the date of the most current clockout
     * 
     * @param user
     * @return
     */
    LocalDate getNewestRecord(User user);

    /**
     * Return distinct list of Users that have a record in which they both
     * clocked in and out
     * 
     * @return
     */
    List<User> getDistinctUserList();

    /**
     * Return a list of Date objects. One Date object is returned per week
     */
    List<LocalDate> getDistinctListWeeks();

    /**
     * Return List of UserTotalHours objects, where an employee worked for 8 or
     * more hours for a given day (Note: This will return only one UserTime
     * object per employee/day where they exceeded 8hrs.. i.e., if user#1 worked
     * 3hrs in the morning then 6 hours in the afternoon, only that 3 hour slice
     * object would be returned)
     * 
     * @return
     */
    List<UserTimeMillis> getAllUsersClockedInMoreThanEightHours(
            LocalDate startDate, LocalDate endDate);

    /**
     * Returns a list of UserTotalHours objects for the current week. Note: Pass
     * in a null user object if you do not want results to be filtered by user
     * 
     * @return
     */
    List<UserTimeMillis> getAllByWeek(LocalDate endWeek, LocalDate startWeek);

    /**
     * Returns a list of date (one per week) worked by an individual
     * 
     * @return List&lt;LocalDate&gt;
     */
    List<LocalDate> getDaysWorked(int userId);

    /**
     * Get distinct list of weeks
     * 
     * @return
     */
    List<LocalDate> getActiveWeeks();

    /**
     * Get a list of UserTimeHours objects, one for each user within the date
     * span. Each UserTimeHours object will contain one UserTime object along
     * with the total time (in milliseconds) of how long the user worked
     * 
     * @param startDate
     * @param endDate
     * @param user
     * @return
     */
    List<UserTimeMillis> getWeeklyTotalHours(LocalDate startDate,
            LocalDate endDate, User user);

    /**
     * Just like getUserTimeObjectsBetweenDates(), except allows half finished
     * ones to be returned as well
     * 
     * @param startDate
     * @param endDate
     * @param user
     * @return
     */
    List<UserTime> getUserTimeObjectsBetweenDatesForTimeManagement(
            LocalDate startDate, LocalDate endDate, User user);

    /**
     * Get total hours worked grouped by day and user
     * @param startDate
     * @param endDate
     * @param user
     * @return List<UserTimeMillis>
     */
	List<UserTimeMillis> getDailyTotalHours(LocalDate startDate,
			LocalDate endDate, User user);
}
