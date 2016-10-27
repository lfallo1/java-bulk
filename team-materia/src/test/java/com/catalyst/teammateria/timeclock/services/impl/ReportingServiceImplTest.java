package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;
import com.catalyst.teammateria.timeclock.services.ReportingService;
import com.catalyst.teammateria.timeclock.servicesimpl.ReportingServiceImpl;
import com.catalyst.teammateria.timeclock.util.DateUtility;

@RunWith (MockitoJUnitRunner.class)
public class ReportingServiceImplTest {
    
    @InjectMocks
    private ReportingService target = new ReportingServiceImpl();

    @Mock
    UserTimeDao userTimeDao;
    
    @Mock
    UserDao userDao;
    
    @Test
    public void getOvertimeReportTest() {
        LocalDate date = new LocalDate();
        ArrayList<UserTimeMillis> expectedList = new ArrayList<UserTimeMillis>();
        when(userTimeDao.getAllUsersClockedInMoreThanEightHours(DateUtility.getStartOfWeek(date), DateUtility.getEndOfWeek(date))).thenReturn(expectedList);
        assertEquals(expectedList, target.getOvertimeReport(date));
    }
    
    @Test
    public void getTotalHoursReportValidTest() {
        LocalDate date = new LocalDate();
        User user = new User();
        user.setUsername("adietrich");
        ArrayList<UserTimeMillis> expectedListValid = new ArrayList<UserTimeMillis>();
        when(userTimeDao.getWeeklyTotalHours(DateUtility.getStartOfWeek(date), DateUtility.getEndOfWeek(date), user)).thenReturn(expectedListValid);
        assertEquals(expectedListValid, target.getTotalHoursReport(date, user.getUsername()));
    }
    
    @Test
    public void getTotalHoursReportInvalidTest() {
        LocalDate date = new LocalDate();
        User user = new User();
        user.setUsername("");
        ArrayList<UserTimeMillis> expectedListInvalid = new ArrayList<UserTimeMillis>();
        when(userTimeDao.getWeeklyTotalHours(DateUtility.getStartOfWeek(date), DateUtility.getEndOfWeek(date), user)).thenReturn(expectedListInvalid);
        assertEquals(expectedListInvalid, target.getTotalHoursReport(date, user.getUsername()));
        user.setUsername(null);
        when(userTimeDao.getWeeklyTotalHours(DateUtility.getStartOfWeek(date), DateUtility.getEndOfWeek(date), user)).thenReturn(expectedListInvalid);
        assertEquals(expectedListInvalid, target.getTotalHoursReport(date, user.getUsername()));
    }
    
    @Test
    public void getAbsentReportValidTest() {
        LocalDate date = new LocalDate();
        User user = new User();
        user.setUsername("adietrich");
        ArrayList<UserTimeMillis> expectedListValid = new ArrayList<UserTimeMillis>();
        when(userTimeDao.getWeeklyTotalHours(DateUtility.getStartOfWeek(date).plusDays(1), DateUtility.getEndOfWeek(date).minusDays(1), user)).thenReturn(expectedListValid);
        assertEquals(expectedListValid, target.getAbsentReport(date, user.getUsername()));
    }
    
    @Test
    public void getAbsentReportInvalidTest() {
        LocalDate date = new LocalDate();
        User user = new User();
        user.setUsername("");
        ArrayList<UserTimeMillis> expectedListInvalid = new ArrayList<UserTimeMillis>();
        when(userTimeDao.getDailyTotalHours(DateUtility.getStartOfWeek(date).plusDays(1), DateUtility.getEndOfWeek(date).minusDays(1), user)).thenReturn(expectedListInvalid);
        assertEquals(expectedListInvalid, target.getAbsentReport(date, user.getUsername()));
        user.setUsername(null);
        when(userTimeDao.getDailyTotalHours(DateUtility.getStartOfWeek(date).plusDays(1), DateUtility.getEndOfWeek(date).minusDays(1), user)).thenReturn(expectedListInvalid);
        assertEquals(expectedListInvalid, target.getAbsentReport(date, user.getUsername()));
    }
    
    @Test
    public void getUserListOrderByUsername(){
        ArrayList<User> expectedList = new ArrayList<User>();
        when(userDao.getAllSortByUsername()).thenReturn(expectedList);
        assertEquals(expectedList, target.getUserListOrderByUsername());
    }
}
