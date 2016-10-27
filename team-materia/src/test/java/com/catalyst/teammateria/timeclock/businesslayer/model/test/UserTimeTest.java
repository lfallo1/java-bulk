package com.catalyst.teammateria.timeclock.businesslayer.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;

public class UserTimeTest {

    UserTime target = new UserTime();

    private static final Integer TEST_INT = 1;

    User TEST_USER = new User();

    LocalTime TEST_TIME = new LocalTime(0);

    LocalDate TEST_DATE = new LocalDate(0);

    @Test
    public void testId() {
        target.setId(TEST_INT);
        Integer checkId = target.getId();
        assertEquals(checkId, TEST_INT);
    }

    @Test
    public void testUserId() {
        target.setUserId(TEST_USER);
        User checkUser = target.getUser();
        assertEquals(checkUser, TEST_USER);
    }

    @Test
    public void testClockIn() {
        target.setClockIn(TEST_TIME);
        LocalTime checkClockIn = target.getClockIn();
        assertEquals(checkClockIn, TEST_TIME);
    }

    @Test
    public void testClockOut() {
        target.setClockOut(TEST_TIME);
        LocalTime checkClockOut = target.getClockOut();
        assertEquals(checkClockOut, TEST_TIME);
    }

    @Test
    public void testClockDate() {
        target.setClockDate(TEST_DATE);
        LocalDate checkClockDate = target.getClockDate();
        assertEquals(checkClockDate, TEST_DATE);
    }
    
    @Test
    public void hashCodeTest(){
        UserTime userTime1 = new UserTime();
        userTime1.setId(1);
        target.setId(2);
        int userTime1Hash = userTime1.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == userTime1Hash);
    }
    
    @Test
    public void equalsTest(){
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        UserTime testUserTime = new UserTime();
        target.setId(1);
        testUserTime.setId(target.getId());
        assertEquals(target,testUserTime);
    }
}
