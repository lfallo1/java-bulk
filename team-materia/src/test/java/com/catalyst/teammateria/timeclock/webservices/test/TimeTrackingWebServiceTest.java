package com.catalyst.teammateria.timeclock.webservices.test;

import javax.servlet.http.HttpSession;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.services.TimeTrackingService;
import com.catalyst.teammateria.timeclock.webservices.TimeTrackingWebService;

@RunWith (MockitoJUnitRunner.class)
public class TimeTrackingWebServiceTest {

    @InjectMocks
    TimeTrackingWebService target = new TimeTrackingWebService();

    @Mock
    TimeTrackingService timeTrackingService;

    HttpSession session = new SessionStub();

    @Mock
    User user;

    @Test
    public void testSetTimeTrackingService() {
        target.setTimeTrackingService(timeTrackingService);
    }

    @Test
    public void testMyHoursGET() {
        Long date = 1l;
        target.myHoursGET(session, date);
        verify(timeTrackingService, atLeastOnce()).getWeek(any(User.class),
                any(LocalDate.class));
    }

    @Test
    public void testMyClockStatusGET() {
        when(timeTrackingService.currentlyOpenTimeTicket(any(User.class)))
                .thenReturn(true);
        assertTrue(target.myClockStatusGET(session));
    }

    @Test
    public void testOldestWeekGET() {
        LocalDate expectedDate = new LocalDate();
        when(timeTrackingService.getOldest(any(User.class))).thenReturn(
                expectedDate);
        LocalDate actualDate = target.oldestWeekGET(session);
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testClockInPOST() {
        target.clockInPOST(session);
        // Clock in should only occur once upon clocking in
        verify(timeTrackingService, times(1)).clockIn(
                (User)session.getAttribute(anyString()));
    }

    @Test
    public void testClockOutPOST() {
        target.clockOutPOST(session);
        // Clock out should only occur once upon clocking out
        verify(timeTrackingService, times(1)).clockOut(
                (User)session.getAttribute(anyString()));
    }
}
