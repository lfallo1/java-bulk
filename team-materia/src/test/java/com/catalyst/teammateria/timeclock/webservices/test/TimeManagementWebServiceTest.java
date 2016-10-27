package com.catalyst.teammateria.timeclock.webservices.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.formbeans.TimeManagementForm;
import com.catalyst.teammateria.timeclock.services.TimeManagementService;
import com.catalyst.teammateria.timeclock.webservices.TimeManagementWebService;

@RunWith (MockitoJUnitRunner.class)
public class TimeManagementWebServiceTest {

    @InjectMocks
    private TimeManagementWebService target  = new TimeManagementWebService();
    @Mock
    TimeManagementService            timeManagementService;
    @Mock
    List<LocalDate>                  localDateList;
    @Mock
    TimeManagementForm               timeManagementForm;
    private static final int         USER_ID = 0;

    @Test
    public void constructorTest() {
        TimeManagementWebService test = new TimeManagementWebService();
        assertNotNull(test);
    }

    @Test
    public void setTimeManagementServiceTest() {
        target.setTimeManagementService(timeManagementService);
    }

    @Test
    public void getDaysWorkedForWeeksTest() {
        target.getDaysWorkedForWeeks(anyInt());
        verify(timeManagementService).getDays(anyInt());
    }

    @Test
    public void getUserTimeTest() {
        target.getUserTime(anyInt(), anyLong());
        verify(timeManagementService).getUserTime(anyInt(), anyLong());
    }

    @Test
    public void updateUserTimeTest() {
        target.updateUserTime(USER_ID, timeManagementForm);
        verify(timeManagementService).updateUserTime(timeManagementForm,
                USER_ID);
    }
}
