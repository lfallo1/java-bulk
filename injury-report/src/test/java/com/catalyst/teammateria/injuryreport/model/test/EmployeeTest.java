package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Position;

public class EmployeeTest {

    Employee target = new Employee();

    private static final String TEST_STRING = "";

    private static final Integer TEST_INT = 1;

    @Test
    public void testId() {
        target.setEmployeeId(TEST_INT);
        assertEquals(target.getEmployeeId(), TEST_INT);
    }

    @Test
    public void testFirstName() {
        target.setFirstName(TEST_STRING);
        assertEquals(target.getFirstName(), TEST_STRING);
    }

    @Test
    public void testLastName() {
        target.setLastName(TEST_STRING);
        assertEquals(target.getLastName(), TEST_STRING);
    }

    @Test
    public void testPosition() {
        Position position = new Position();
        target.setPosition(position);
        assertEquals(target.getPosition(), position);
    }

    @Test
    public void testHashCode() {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(TEST_INT);
        target.setEmployeeId(2);
        assertFalse(target.hashCode() == employee1.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        assertFalse(target.equals(TEST_STRING));
        Employee employee1 = new Employee();
        target.setEmployeeId(TEST_INT);
        employee1.setEmployeeId(target.getEmployeeId());
        assertTrue(employee1.equals(target));
    }
}
