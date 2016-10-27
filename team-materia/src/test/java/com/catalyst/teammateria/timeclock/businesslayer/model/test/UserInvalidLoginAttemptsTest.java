package com.catalyst.teammateria.timeclock.businesslayer.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserInvalidLoginAttempts;

public class UserInvalidLoginAttemptsTest {

    UserInvalidLoginAttempts target = new UserInvalidLoginAttempts();

    @Test
    public void testGetAttemptId() {
        Integer expectedNumber = 1;
        target.setAttemptId(expectedNumber);
        assertEquals(expectedNumber, target.getAttemptId());
    }

    @Test
    public void testGetUserId() {
        Integer expectedNumber = 1;
        target.setUserId(expectedNumber);
        assertEquals(expectedNumber, target.getUserId());
    }

    @Test
    public void testGetAttempts() {
        Integer expectedAttempts = 2;
        target.setAttempts(expectedAttempts);
        assertEquals(expectedAttempts, target.getAttempts());
    }
    
    @Test
    public void hashCodeTest(){
        UserInvalidLoginAttempts attempt1 = new UserInvalidLoginAttempts();
        attempt1.setAttemptId(1);
        target.setAttemptId(2);
        int attempt1Hash = attempt1.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == attempt1Hash);
    }
    
    @Test
    public void equalsTest(){
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        UserInvalidLoginAttempts testAttempt = new UserInvalidLoginAttempts();
        target.setAttemptId(1);
        testAttempt.setAttemptId(target.getAttemptId());
        assertEquals(target,testAttempt);
    }
}
