package com.catalyst.teammateria.timeclock.businesslayer.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;

public class UserRoleTest {

    UserRole target = new UserRole();

    private static final Integer TEST_INT = 1;

    private static final String TEST_STRING = "";

    @Test
    public void testRoleId() {
        target.setRoleId(TEST_INT);
        Integer checkId = target.getRoleId();
        assertEquals(checkId, TEST_INT);
    }

    @Test
    public void testGetSetUserRole() {
        target.setUserRole(TEST_STRING);
        String checkRole = target.getUserRole();
        assertEquals(checkRole, TEST_STRING);
    }

    @Test
    public void testUserRole() {
        Integer newInt = 1;
        UserRole testUserRole = new UserRole(newInt, "");
        assertEquals(newInt, testUserRole.getRoleId());
        assertEquals("", testUserRole.getUserRole());
    }
    
    @Test
    public void hashCodeTest(){
        UserRole userRole1 = new UserRole();
        userRole1.setRoleId(1);
        target.setRoleId(2);
        int userRole1Hash = userRole1.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == userRole1Hash);
    }
    
    @Test
    public void equalsTest(){
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        UserRole testUserRole = new UserRole();
        target.setRoleId(1);
        testUserRole.setRoleId(target.getRoleId());
        assertEquals(target,testUserRole);
    }
}
