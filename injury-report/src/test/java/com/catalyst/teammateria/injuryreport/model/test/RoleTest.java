package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.Role;

public class RoleTest {

    Role target;

    @Before
    public void setup() {
        target = new Role();
    }

    @Test
    public void getRoleIdTest() {
        target.setRoleId(1);
        assertEquals(1, target.getRoleId());
    }

    @Test
    public void getRoleNameTest() {
        target.setRoleName("foo");
        assertEquals("foo", target.getRoleName());
    }

    @Test
    public void equalsTest() {
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        Role testRole = new Role();
        target.setRoleId(1);
        testRole.setRoleId(target.getRoleId());
        assertEquals(target, testRole);
    }

    @Test
    public void hashTest() {
        Role role = new Role();
        role.setRoleId(1);
        target.setRoleId(2);
        assertNotEquals(target.hashCode(), role.hashCode());
    }

    @Test
    public void roleTest() {
        Role role1 = new Role(1);
        assertEquals(role1.getRoleName(), "ROLE_ADMIN");
        Role role2 = new Role(2);
        assertEquals(role2.getRoleName(), "ROLE_USER");
    }

}
