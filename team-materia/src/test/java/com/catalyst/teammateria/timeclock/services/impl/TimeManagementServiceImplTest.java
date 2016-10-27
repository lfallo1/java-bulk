package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserTime;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.TimeManagementForm;
import com.catalyst.teammateria.timeclock.servicesimpl.TimeManagementServiceImpl;
import com.catalyst.teammateria.timeclock.util.DateUtility;

@RunWith (MockitoJUnitRunner.class)
public class TimeManagementServiceImplTest {

    private static final String RETURNED_UPDATE_SUCCESS = "true";

    private static final String NO_RETURNED_ERRORS = "";

    private static final String CLOCK_OUT_OVERLAP_ERROR = "Entry 1: The clock out time intersects entry 2<br>";

    private static final String CLOCK_IN_OVERLAP_ERROR = "Entry 1: The clock in time intersects entry 2<br>";

    private static final String SURROUNDED_TIME_ERROR = "Entry 1: This entry surrounds entry 2<br>";

    private static final String NO_TIME_ENTERED_ERROR = "Entry 1: No time entered<br>";

    private static final String NEGATIVE_TIME_ERROR = "Entry 1: The clock in time is after the clock out time.<br>";

    @InjectMocks
    private TimeManagementServiceImpl target;

    @Mock
    UserDao userDao;

    @Mock
    UserTimeDao userTimeDao;

    @Test
    public void constructorTest() {
        TimeManagementServiceImpl test = new TimeManagementServiceImpl();
        assertNotNull(test);
    }

    @Test
    public void getDaysTest() {
        ArrayList<LocalDate> expectedList = new ArrayList<LocalDate>();
        LocalDate date = new LocalDate();
        expectedList.add(DateUtility.getStartOfWeek(date));
        when(userTimeDao.getDaysWorked(1)).thenReturn(expectedList);
        assertEquals(expectedList, target.getDays(1));
    }

    @Test
    public void getUserTimeTest() {
        // Initialize a list of UserTime objects
        ArrayList<UserTime> expectedList = new ArrayList<UserTime>();
        // Return that list when queried from database
        when(
                userTimeDao.getUserTimeObjectsBetweenDatesForTimeManagement(
                        any(LocalDate.class), any(LocalDate.class),
                        any(User.class))).thenReturn(expectedList);
        // Assert that the expected list was returned
        assertEquals(expectedList, target.getUserTime(Mockito.anyInt(), 10L));
    }

    @Test
    public void checkForEmptyTimeTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        ArrayList<UserTime> list = new ArrayList<UserTime>();
        // Create a new usertime with a clock in and clock out
        UserTime userTime = new UserTime();
        LocalTime clockIn = new LocalTime();
        LocalTime clockOut = new LocalTime();
        userTime.setClockIn(clockIn);
        userTime.setClockOut(clockOut);
        // Add the usertime to the list
        list.add(userTime);
        // Add the list to the time form
        timeForm.setUserTimeList(list);
        // Assert that an error is returned for no time entered when validating
        String retString = target.validation(timeForm);
        assertEquals(NO_TIME_ENTERED_ERROR, retString);
    }

    @Test
    public void checkForNegativeTimeTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        ArrayList<UserTime> list = new ArrayList<UserTime>();
        // Create a new usertime with a clock in and clock out
        UserTime userTime = new UserTime();
        userTime.setClockIn(LocalTime.now().plusHours(1));
        userTime.setClockOut(LocalTime.now());
        // Add the usertime to the list
        list.add(userTime);
        // Add the list to the time form
        timeForm.setUserTimeList(list);
        // Assert that an error is returned for no time entered when validating
        String retString = target.validation(timeForm);
        assertEquals(NEGATIVE_TIME_ERROR, retString);
    }

    @Test
    public void entrySurroundsEntryTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        // Create a user time list and add 2 user times for the form
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        LocalTime clockTime = new LocalTime();
        // Set up the first usertime in the form with a valid information
        UserTime userTimeFirst = new UserTime();
        userTimeFirst.setClockDate(LocalDate.now());
        // Give the first usertime valid and postive clock times
        userTimeFirst.setClockIn(clockTime);
        userTimeFirst.setClockOut(clockTime.plusHours(3));
        // Set up the second usertime in the form lying within the first
        // usertime
        UserTime userTimeSecond = new UserTime();
        userTimeSecond.setClockDate(LocalDate.now());
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        // Give the second usertime valid and postive clock times that both lie
        // within with the first usertime
        userTimeSecond.setClockIn(clockTime.plusHours(1));
        userTimeSecond.setClockOut(clockTime.plusHours(2));
        // Add the list to the time form
        timeForm.setUserTimeList(userTimeList);
        // Assert that the error alerts the user to the failure
        String returnedErrors = target.validation(timeForm);
        assertEquals(SURROUNDED_TIME_ERROR, returnedErrors);
    }

    // Test that validation fails when a second clock in overlaps with a
    // previous clock out
    @Test
    public void overlapClockInEntryTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        // Create a user time list and add 2 user times for the form
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        // Set up the first usertime in the form with a valid information
        UserTime userTimeFirst = validUserTime(3, 5);
        // Give the second usertime that overlaps with the clock in of the first
        // usertime
        UserTime userTimeSecond = validUserTime(2, 4);
        // Add the usertimes to the form
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        timeForm.setUserTimeList(userTimeList);
        // Assert that the error alerts the user to the failure
        String returnedErrors = target.validation(timeForm);
        assertEquals(CLOCK_IN_OVERLAP_ERROR, returnedErrors);
    }

    // Test that validation fails when the first clock out overlaps with the
    // second clock in
    @Test
    public void clockOutIntersectsEntryTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        // Create a user time list and add 2 user times for the form
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        // Set up the first usertime in the form with a valid information
        UserTime userTimeFirst = validUserTime(1, 3);
        // Give the second usertime that overlaps with the clock in of the first
        // usertime
        UserTime userTimeSecond = validUserTime(2, 4);
        // Add the usertimes to the form
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        timeForm.setUserTimeList(userTimeList);
        // Assert that the error alerts the user to the failure
        String returnedErrors = target.validation(timeForm);
        assertEquals(CLOCK_OUT_OVERLAP_ERROR, returnedErrors);
    }

    // When the form has 2 clock dates that exist and do not overlap, no errors
    // should be returned
    @Test
    public void validEntryTest() {
        TimeManagementForm timeForm = new TimeManagementForm();
        UserTime userTimeFirst = validUserTime(1, 2);
        UserTime userTimeSecond = validUserTime(3, 4);
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        timeForm.setUserTimeList(userTimeList);
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        // Assert that no errors were returned
        String returnedErrors = target.validation(timeForm);
        assertEquals(NO_RETURNED_ERRORS, returnedErrors);
    }

    // If the second usertime has a date later than the first usertime, the
    // entry is still valid
    @Test
    public void secondUserTimeAfterFirstTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        // Create a user time list and add 2 user times for the form
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        // Set up the first usertime in the form with a valid information
        UserTime userTimeFirst = validUserTime(1, 2);
        UserTime userTimeSecond = validUserTime(3, 4);
        // Give the second usertime a clock date the day after the first
        // usertime
        userTimeSecond.setClockDate(userTimeFirst.getClockDate().plusDays(1));
        // Add the usertimes to the form
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        timeForm.setUserTimeList(userTimeList);
        // Assert that no errors are returned
        String returnedErrors = target.validation(timeForm);
        assertEquals(NO_RETURNED_ERRORS, returnedErrors);
    }

    // When there is both negative time and an overlap with clockouts, the
    // returned error should contain both
    @Test
    public void overlapAndNegativeTimeTest() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        // Create a user time list and add 2 user times for the form
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        // Set up the first usertime in the form with a valid information
        UserTime userTimeFirst = validUserTime(4, 2);
        // Give the second usertime that overlaps with the clock in of the first
        // usertime
        UserTime userTimeSecond = validUserTime(1, 3);
        // Add the usertimes to the form
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        timeForm.setUserTimeList(userTimeList);
        // Assert that the error alerts the user to the failure
        String returnedErrors = target.validation(timeForm);
        assertEquals(NEGATIVE_TIME_ERROR + CLOCK_OUT_OVERLAP_ERROR,
                returnedErrors);
    }

    @Test
    public void updateUserTimeTestInvalidTime() {
        // Init a time management form to validate
        TimeManagementForm timeForm = new TimeManagementForm();
        // Create a user time list and add 2 user times for the form
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        // Set up the first usertime in the form with a valid information
        UserTime userTimeFirst = validUserTime(5, 3);
        // Give the second usertime that overlaps with the clock in of the first
        // usertime
        UserTime userTimeSecond = validUserTime(2, 4);
        // Add the usertimes to the form
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        timeForm.setUserTimeList(userTimeList);
        String returnedErrors = target.updateUserTime(timeForm, 1);
        assertFalse(returnedErrors.equals(RETURNED_UPDATE_SUCCESS));
    }

    // When updating a user with valid time, then the no errors should be
    // returned
    @Test
    public void updateUserTimeTestValidTime() {
        TimeManagementForm timeForm = new TimeManagementForm();
        UserTime userTimeFirst = validUserTime(1, 2);
        UserTime userTimeSecond = validUserTime(3, 4);
        ArrayList<UserTime> userTimeList = new ArrayList<UserTime>();
        timeForm.setUserTimeList(userTimeList);
        userTimeList.add(userTimeFirst);
        userTimeList.add(userTimeSecond);
        String returnedErrors = target.updateUserTime(timeForm, 0);
        assertTrue(returnedErrors.equals(RETURNED_UPDATE_SUCCESS));
    }
   
    @Ignore
    private UserTime validUserTime(int clockInDelta, int clockOutDelta) {
        UserTime returnTime = new UserTime();
        returnTime.setClockDate(LocalDate.now());
        LocalTime clockTime = new LocalTime();
        returnTime.setClockIn(clockTime.plusHours(clockInDelta));
        returnTime.setClockOut(clockTime.plusHours(clockOutDelta));
        return returnTime;
    }
}
