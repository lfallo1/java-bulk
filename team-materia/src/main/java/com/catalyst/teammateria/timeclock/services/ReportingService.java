package com.catalyst.teammateria.timeclock.services;

import java.util.List;

import org.joda.time.LocalDate;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;

/**
 * Methods used to retrieve data used for
 * reporting purposes
 * @author lfallon
 *
 */
public interface ReportingService {
	/**
	 * Get all days in which a user worked for more than
	 * eight hours within the selected week
	 * @param date
	 * @return
	 */
	List<UserTimeMillis> getOvertimeReport(LocalDate date);

	/**
	 * get total time worked per employee for the given week.
	 * Pass in a null username to get all employees
	 * @param date
	 * @param username
	 * @return
	 */
	List<UserTimeMillis> getTotalHoursReport(LocalDate date, String username);

	/**
	 * Get list of UserTime objects, one per day for each employee where they
	 * worked for more than 0ms
	 * 
	 * @param date
	 * @param username
	 * @return
	 */
	List<UserTimeMillis> getAbsentReport(LocalDate date, String username);

	/**
	 * Get all users ordered by username
	 * @return
	 */
	List<User> getUserListOrderByUsername();

}
