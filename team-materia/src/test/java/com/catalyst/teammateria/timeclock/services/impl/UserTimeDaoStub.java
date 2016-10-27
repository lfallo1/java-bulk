package com.catalyst.teammateria.timeclock.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;

public class UserTimeDaoStub implements UserTimeDao {

    UserTime expectedUserTime;

    List<UserTime> expectedTimeList;

    public UserTimeDaoStub() {
        expectedUserTime = new UserTime();
        expectedUserTime.setClockDate(LocalDate.now().minusDays(1));
        expectedTimeList = new ArrayList<UserTime>();
    }

    @Override
    public void updateUserTime(UserTime userTime) {
        expectedUserTime.setClockDate(LocalDate.now());
    }

    @Override
    public UserTime getUserTimeByStillOpen(User user) {
        // expectedUserTime.setClockIn(LocalTime.now().minusHours(1));
        return expectedUserTime;
    }

    @Override
    public List<UserTime> getUserTimeObjectsBetweenDates(LocalDate startDate,
            LocalDate endDate, User user) {
        return expectedTimeList;
    }

    @Override
    public void addUserTime(UserTime userTime) {
        // TODO Auto-generated method stub
    }

    @Override
    public LocalDate getOldestRecord(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean checkOpenTicketStatus(User user) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public LocalDate getNewestRecord(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getDistinctUserList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LocalDate> getDistinctListWeeks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserTimeMillis> getAllUsersClockedInMoreThanEightHours(
            LocalDate startDate, LocalDate endDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserTimeMillis> getAllByWeek(LocalDate endWeek,
            LocalDate startWeek) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LocalDate> getDaysWorked(int userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<LocalDate> getActiveWeeks() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserTimeMillis> getWeeklyTotalHours(LocalDate startDate,
            LocalDate endDate, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserTime> getUserTimeObjectsBetweenDatesForTimeManagement(
            LocalDate startDate, LocalDate endDate, User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserTimeMillis> getDailyTotalHours(LocalDate startDate,
            LocalDate endDate, User user) {
        // TODO Auto-generated method stub
        return null;
    }
}
