package com.catalyst.teammateria.timeclock.businesslayer.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;

public class UserTest {

    User                         target      = new User();
    UserRole                     role        = new UserRole();
    LocalDate                    testDate    = new LocalDate(0);
    LocalDateTime                testStamp   = new LocalDateTime(0);
    private static final Integer testInt     = 1;
    private static final String  TEST_STRING = "";
    private static final Boolean testBool    = false;

    @Test
    public void testUserId() {
        target.setUserId(testInt);
        Integer checkUserId = target.getUserId();
        assertEquals(checkUserId, testInt);
    }

    @Test
    public void testUsername() {
        target.setUsername(TEST_STRING);
        String checkUsername = target.getUsername();
        assertEquals(checkUsername, TEST_STRING);
    }

    @Test
    public void testFirstName() {
        target.setFirstName(TEST_STRING);
        String checkFirstName = target.getFirstName();
        assertEquals(checkFirstName, TEST_STRING);
    }

    @Test
    public void testLastName() {
        target.setLastName(TEST_STRING);
        String checkLastName = target.getLastName();
        assertEquals(checkLastName, TEST_STRING);
    }

    @Test
    public void testEmail() {
        target.setEmail(TEST_STRING);
        String checkEmail = target.getEmail();
        assertEquals(checkEmail, TEST_STRING);
    }

    @Test
    public void testRole() {
        target.setRole(role);
        UserRole checkRole = target.getRole();
        assertEquals(checkRole, role);
    }

    @Test
    public void testActive() {
        target.setActive(testBool);
        Boolean checkActive = target.getActive();
        assertEquals(checkActive, testBool);
    }

    @Test
    public void testCreated() {
        target.setCreated(testStamp);
        LocalDateTime checkCreated = target.getCreated();
        assertEquals(checkCreated, testStamp);
    }

    @Test
    public void testUpdated() {
        target.setUpdated(testDate);
        LocalDate checkUpdated = target.getUpdated();
        assertEquals(checkUpdated, testDate);
    }

    @Test
    public void hashCodeTest() {
        User user1 = new User();
        user1.setUserId(1);
        target.setUserId(2);
        int user1Hash = user1.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == user1Hash);
    }

    @Test
    public void equalsTest() {
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        User testUser = new User();
        target.setUserId(1);
        testUser.setUserId(target.getUserId());
        assertEquals(target, testUser);
    }

    @Test
    public void specialConstructorTest() {
        User testUser = new User("firstname", "lastname", 1);
        assertEquals(testUser.getFirstName(), "firstname");
        assertEquals(testUser.getLastName(), "lastname");
        assertTrue(testUser.getUserId() == 1);
    }
}
