package com.catalyst.teammateria.timeclock.util.test;

import java.lang.reflect.Constructor;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;

import com.catalyst.teammateria.timeclock.util.DateUtility;

public class DateUtilTest {

    @Test
    public void testGetStartOfWeek() {
        LocalDate date = DateUtility.getStartOfWeek(new LocalDate());
        Assert.assertFalse(date.getDayOfWeek() == DateTimeConstants.SATURDAY);
        Assert.assertTrue(date.getDayOfWeek() == DateTimeConstants.SUNDAY);
    }

    @Test
    public void testGetEndOfWeek() {
        LocalDate date = DateUtility.getEndOfWeek(new LocalDate());
        Assert.assertFalse(date.getDayOfWeek() == DateTimeConstants.SUNDAY);
        Assert.assertTrue(date.getDayOfWeek() == DateTimeConstants.SATURDAY);
    }
    
    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = DateUtility.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }
}
