package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Role;
import com.catalyst.teammateria.injuryreport.model.User;

public class UserTest {

    User target;

    @Before
    public void setup() {
        target = new User();
    }

    @Test
    public void getUserIdTest() {
        target.setUserId(1);
        assertEquals(1, target.getUserId());
    }

    @Test
    public void getUserNameTest() {
        target.setUserName("foo");
        assertEquals("foo", target.getUserName());
    }

    @Test
    public void getUserPassTest() {
        target.setUserPass("bar");
        assertEquals("bar", target.getUserPass());
    }

    @Test
    public void getRoleIdTest() {
        Role role = new Role();
        target.setRole(role);
        assertEquals(role, target.getRole());
    }

    @Test
    public void getEmployeeIdTest() {
        Employee employee = new Employee();
        target.setEmployee(employee);
        assertEquals(employee, target.getEmployee());
    }
    
    @Test
    public void isActiveTest() {
        target.setActive(true);
        assertTrue(target.isActive());
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
    public void hashTest() {
        User user = new User();
        user.setUserId(1);
        target.setUserId(2);
        int userHash = user.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == userHash);
    }
}
