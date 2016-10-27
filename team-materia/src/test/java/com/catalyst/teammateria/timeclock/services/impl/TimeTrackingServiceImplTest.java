package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.servicesimpl.TimeTrackingServiceImpl;

@RunWith (MockitoJUnitRunner.class)
public class TimeTrackingServiceImplTest {

    @InjectMocks
    private TimeTrackingServiceImpl target;

    @Mock
    UserTimeDao userTimeDao;

    @Test
    public void testSetUserTimeDao() {
        target.setUserTimeDao(userTimeDao);
    }

    @Test
    public void testGetWeek() {
        // Init inial params
        LocalDate date = new LocalDate();
        User user = new User();
        // Return a usertime list when requested
        List<UserTime> expectedTimeList = new ArrayList<UserTime>();
        when(
                userTimeDao.getUserTimeObjectsBetweenDates(
                        any(LocalDate.class), any(LocalDate.class),
                        any(User.class))).thenReturn(expectedTimeList);
        List<UserTime> actualTimeList = target.getWeek(user, date);
        // Assert that the correct user time list is returned
        assertEquals(expectedTimeList, actualTimeList);
    }

    @Test
    public void testClockIn() {
        // Clock in with a fake user
        User user = new User();
        target.clockIn(user);
        // Assert that a usertime was added and not updated
        verify(userTimeDao, times(1)).addUserTime(any(UserTime.class));
        verify(userTimeDao, never()).updateUserTime(any(UserTime.class));
    }

    @Test
    public void testClockOut() {
        // Clock out with a fake user
        User user = new User();
        UserTime expectedUserTime = new UserTime();
        //LocalDate clockDate = LocalDate.now();
        expectedUserTime.setClockDate(LocalDate.now());
        when(userTimeDao.getUserTimeByStillOpen(user)).thenReturn(
                expectedUserTime);        
        target.clockOut(user);
        // Assert that a usertime was updated, but no new usertime was created
        verify(userTimeDao, never()).addUserTime(any(UserTime.class));
        verify(userTimeDao, times(1)).updateUserTime(any(UserTime.class));               
    }

    @Test
    public void testCurrentlyOpenTimeTicket() {
        User user = new User();
        target.currentlyOpenTimeTicket(user);
        Mockito.verify(userTimeDao).checkOpenTicketStatus(user);
    }

    @Test
    public void testGetOldest() {
        LocalDate expectedDate = new LocalDate();
        User user = new User();
        when(userTimeDao.getOldestRecord(user)).thenReturn(expectedDate);
        LocalDate actualDate = target.getOldest(user);
        assertEquals(expectedDate, actualDate);
    }
}
