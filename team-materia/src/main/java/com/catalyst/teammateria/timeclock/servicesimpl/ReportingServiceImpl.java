package com.catalyst.teammateria.timeclock.servicesimpl;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;
import com.catalyst.teammateria.timeclock.services.ReportingService;
import com.catalyst.teammateria.timeclock.util.DateUtility;

/**
 * The implementation of ReportingService
 * 
 * @author aDietrich
 *
 */
@Service
public class ReportingServiceImpl implements ReportingService {
	private UserTimeDao userTimeDao;
	
	private UserDao userDao;

	@Autowired
	public void setUserTimeDao(UserTimeDao userTimeDao) {
		this.userTimeDao = userTimeDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}	
	
	@Override
	public List<UserTimeMillis> getOvertimeReport(LocalDate date) {
		return userTimeDao.getAllUsersClockedInMoreThanEightHours(DateUtility.getStartOfWeek(date), DateUtility.getEndOfWeek(date));
	}

	@Override
	public List<UserTimeMillis> getTotalHoursReport(LocalDate date, String username) {
		User user = username == null || "".equals(username) ? null : userDao.getUserByUsername(username);
		return userTimeDao.getWeeklyTotalHours(DateUtility.getStartOfWeek(date), DateUtility.getEndOfWeek(date), user);
	}

	@Override
	public List<UserTimeMillis> getAbsentReport(LocalDate date, String username) {
		User user = username == null || "".equals(username) ? null : userDao.getUserByUsername(username);
		return userTimeDao.getDailyTotalHours(DateUtility.getStartOfWeek(date).plusDays(1), DateUtility.getEndOfWeek(date).minusDays(1), user);
	}

	@Override
	public List<User> getUserListOrderByUsername() {
		return userDao.getAllSortByUsername();
	}

}
