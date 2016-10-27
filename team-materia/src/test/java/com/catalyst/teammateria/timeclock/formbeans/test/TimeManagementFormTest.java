package com.catalyst.teammateria.timeclock.formbeans.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.formbeans.TimeManagementForm;

@RunWith (MockitoJUnitRunner.class)
public class TimeManagementFormTest {

    @InjectMocks
    private TimeManagementForm target;
    List<UserTime>             userTimeList;

    @Test
    public void testUserTimeList() {
        target.setUserTimeList(userTimeList);
        assertEquals(userTimeList, target.getUserTimeList());
    }
}
