package com.catalyst.teammateria.timeclock.businesslayer.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;

public class UserPasswordTest {

    UserPassword target = new UserPassword();

    private static final Integer TEST_INT = 1;

    private static final String TEST_STRING = "";

    @Test
    public void testId() {
        target.setUserId(TEST_INT);
        Integer checkId = target.getUserId();
        assertEquals(checkId, TEST_INT);
    }

    @Test
    public void testHash() {
        target.setUserHash(TEST_STRING);
        String checkHash = target.getUserHash();
        assertEquals(checkHash, TEST_STRING);
    }

    @Test
    public void testPassword() {
        UserPassword newPassword = new UserPassword(TEST_INT, TEST_STRING);
        assertEquals(TEST_INT, newPassword.getUserId());
        assertEquals(TEST_STRING, newPassword.getUserHash());
    }
    
    @Test
    public void hashCodeTest(){
        UserPassword userPass1 = new UserPassword();
        userPass1.setUserId(1);
        target.setUserId(2);
        int userPass1Hash = userPass1.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == userPass1Hash);
    }
    
    @Test
    public void equalsTest(){
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        UserPassword testUserPass = new UserPassword();
        target.setUserId(1);
        testUserPass.setUserId(target.getUserId());
        assertEquals(target,testUserPass);
    }
}
