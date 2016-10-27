package com.catalyst.teammateria.timeclock.servicesimpl;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.services.TimeTrackingService;
import com.catalyst.teammateria.timeclock.util.DateUtility;

/**
 * Implementation of TimeTrackingService
 * 
 * @author aDietrich
 */
@Service
public class TimeTrackingServiceImpl implements TimeTrackingService {

    private static final LocalTime END_OF_DAY = new LocalTime(23, 59, 59);

    private UserTimeDao userTimeDao;

    /**
     * Sets data access object for interaction with UserTime table
     * 
     * @param userTimeDao
     *            UserTimeDao to be assigned
     */
    @Autowired
    public void setUserTimeDao(UserTimeDao userTimeDao) {
        this.userTimeDao = userTimeDao;
    }

    @Override
    public List<UserTime> getWeek(User user, LocalDate date) {
        LocalDate startOfWeek = DateUtility.getStartOfWeek(date);
        LocalDate endOfWeek = DateUtility.getEndOfWeek(date);
        return userTimeDao.getUserTimeObjectsBetweenDates(startOfWeek,
                endOfWeek, user);
    }

    @Override
    public void clockIn(User user) {
        UserTime usertime = new UserTime();
        usertime.setUserId(user);
        usertime.setClockDate(new LocalDate());
        usertime.setClockIn(new LocalTime());
        userTimeDao.addUserTime(usertime);
    }

    @Override
    public void clockOut(User user) {
        UserTime usertime = userTimeDao.getUserTimeByStillOpen(user);
        if (usertime.getClockDate().isEqual(getNow())) {
            usertime.setClockOut(new LocalTime());
            userTimeDao.updateUserTime(usertime);
        } else {
            // Set the clock out to the end of the day
            usertime.setClockOut(END_OF_DAY);
            // push it to the database
            userTimeDao.updateUserTime(usertime);
            // clock In at the start of the day
            UserTime newUserTime = new UserTime();
            newUserTime.setUserId(user);
            newUserTime.setClockDate(usertime.getClockDate().plusDays(1));
            newUserTime.setClockIn(new LocalTime(0, 0, 0));
            userTimeDao.addUserTime(newUserTime);
            // then clock out now (this allows multi-day clock in clock outs)
            clockOut(user);
        }
    }

    @Override
    public boolean currentlyOpenTimeTicket(User user) {
        return userTimeDao.checkOpenTicketStatus(user);
    }

    @Override
    public LocalDate getOldest(User user) {
        return userTimeDao.getOldestRecord(user);
    }

    /**
     * This method is used to return a static method call so that the methods
     * can be tested in jUnit
     * 
     * @return LocalDate.now()
     */
    public LocalDate getNow() {
        return LocalDate.now();
    }
}
