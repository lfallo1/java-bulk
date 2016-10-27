package com.catalyst.teammateria.timeclock.formbeans.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;

@RunWith (MockitoJUnitRunner.class)
public class UserTimeMillisTest {

    @InjectMocks
    private UserTimeMillis    target;
    private UserTime          userTime;
    private static final long MILLIS = 0;

    @Test
    public void testUserTime() {
        target.setUserTime(userTime);
        assertEquals(target.getUserTime(), userTime);
    }

    @Test
    public void testHours() {
        target.setMillis(MILLIS);
        assertEquals(target.getMillis(), MILLIS);
    }

    @Test
    public void testConstructor() {
        UserTimeMillis test = new UserTimeMillis(userTime, MILLIS);
        assertEquals(test.getMillis(), MILLIS);
        assertEquals(test.getUserTime(), userTime);
    }
}
