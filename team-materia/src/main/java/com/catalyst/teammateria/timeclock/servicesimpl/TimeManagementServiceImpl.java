package com.catalyst.teammateria.timeclock.servicesimpl;

import java.util.List;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.TimeManagementForm;
import com.catalyst.teammateria.timeclock.services.TimeManagementService;
import com.catalyst.teammateria.timeclock.util.DateUtility;

/**
 * Implementation of TimeManagementService
 * 
 * @author aDietrich
 */
@Service
public class TimeManagementServiceImpl implements TimeManagementService {

    private UserDao userDao;

    private UserTimeDao userTimeDao;

    private static final int NUM_OF_DAYS = 7;

    private static final String ENTRY_STRING = "Entry ";

    private StringBuilder errors = new StringBuilder();

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setUserTimeDao(UserTimeDao userTimeDao) {
        this.userTimeDao = userTimeDao;
    }

    @Override
    public List<LocalDate> getDays(int userId) {
        List<LocalDate> list = userTimeDao.getDaysWorked(userId);
        for (int i = 0; i < list.size(); i++ ) {
            list.set(i, DateUtility.getStartOfWeek(list.get(i)));
        }
        return list;
    }

    @Override
    public List<UserTime> getUserTime(int userId, long week) {
        LocalDate sun = new LocalDate(week, DateTimeZone.UTC);
        LocalDate sat = new LocalDate(week, DateTimeZone.UTC)
                .plusDays(NUM_OF_DAYS);
        return userTimeDao.getUserTimeObjectsBetweenDatesForTimeManagement(sun,
                sat, userDao.getUserById(userId));
    }

    @Override
    public String updateUserTime(TimeManagementForm timeManagementForm, int userId) {
        String errorsFinal = validation(timeManagementForm);
        // if there are no errors put all the user times through
        if (errorsFinal.equals("")) {
            for (int i = 0; i < timeManagementForm.getUserTimeList().size(); i++ ) {
                timeManagementForm.getUserTimeList().get(i)
                        .setUserId(userDao.getUserById(userId));
                userTimeDao.updateUserTime(timeManagementForm.getUserTimeList()
                        .get(i));
            }
            return "true";
        }
        // else return the error string
        return errorsFinal;
    }

    @Override
    public String validation(TimeManagementForm timeManagementForm) {
        for (int i = 0; i < timeManagementForm.getUserTimeList().size(); i++ ) {
            checkForEmptyOrNegativeTime(timeManagementForm, i);
            appendErrorsForUserTimes(timeManagementForm, i);
        }
        return errors.toString();
    }    

    @Override
    public void checkForEmptyOrNegativeTime(TimeManagementForm timeManagementForm, int i) {
        // Check for empty time
        LocalTime clockIn = timeManagementForm.getUserTimeList().get(i).getClockIn();
        LocalTime clockOut = timeManagementForm.getUserTimeList().get(i).getClockOut();
        if (clockIn.equals(clockOut)) {
            errors.append(ENTRY_STRING).append(i + 1)
                    .append(": No time entered<br>");
            // check for negative time
        } else if (clockIn.isAfter(clockOut)) {
            errors.append(ENTRY_STRING)
                    .append(i + 1)
                    .append(": The clock in time is after the clock out time.<br>");
        }
    }

    @Override
    public void appendErrorsForUserTimes(TimeManagementForm timeManagementForm, int i) {
        // Loop against the list
        for (int j = i; j < timeManagementForm.getUserTimeList().size(); j++ ) {
            // if to check that this is not the object && that these two
            // objects share the same date
            LocalDate clockDate = timeManagementForm.getUserTimeList().get(i).getClockDate();
            LocalDate clockDate2 = timeManagementForm.getUserTimeList().get(j).getClockDate();
            if (i != j && clockDate.isEqual(clockDate2)) {
                clockInIntersectsEntry(timeManagementForm, i, j);
                clockOutIntersectsEntry(timeManagementForm, i, j);
                entrySurroundsEntry(timeManagementForm, i, j);
            }
        }
    }

    @Override
    public void entrySurroundsEntry(TimeManagementForm timeManagementForm, int i, int j) {
        // check to see if this entry starts before and ends after
        // another
        LocalTime clockIn = timeManagementForm.getUserTimeList().get(i).getClockIn();
        LocalTime clockOut = timeManagementForm.getUserTimeList().get(i).getClockOut();
        LocalTime clockIn2 = timeManagementForm.getUserTimeList().get(j).getClockIn();
        LocalTime clockOut2 = timeManagementForm.getUserTimeList().get(j).getClockOut();
        if (clockIn.isBefore(clockIn2) && clockOut.isAfter(clockOut2)) {
            errors.append(ENTRY_STRING).append(i + 1)
                    .append(": This entry surrounds entry ")
                    .append(j + 1).append("<br>");
        }
    }

    @Override
    public void clockOutIntersectsEntry(TimeManagementForm timeManagementForm, int i, int j) {
        // check to see if this clock out is in between the clock in
        // and out of another
        LocalTime clockOut = timeManagementForm.getUserTimeList().get(i).getClockOut();
        LocalTime clockOut2 = timeManagementForm.getUserTimeList().get(j).getClockOut();
        LocalTime clockIn = timeManagementForm.getUserTimeList().get(j).getClockIn();
        if (clockOut.isBefore(clockOut2) && clockOut.isAfter(clockIn)) {
            errors.append(ENTRY_STRING).append(i + 1)
                    .append(": The clock out time intersects entry ")
                    .append(j + 1).append("<br>");
        }
    }

    @Override
    public void clockInIntersectsEntry(TimeManagementForm timeManagementForm, int i, int j) {
        // check for clock in time between the clock in and out of
        // another
        LocalTime clockIn = timeManagementForm.getUserTimeList().get(i).getClockIn();
        LocalTime clockIn2 = timeManagementForm.getUserTimeList().get(j).getClockIn();
        LocalTime clockOut = timeManagementForm.getUserTimeList().get(j).getClockOut();
        if (clockIn.isBefore(clockOut) && clockIn.isAfter(clockIn2)) {
            errors.append(ENTRY_STRING).append(i + 1)
                    .append(": The clock in time intersects entry ")
                    .append(j + 1).append("<br>");
        }
    }
}
