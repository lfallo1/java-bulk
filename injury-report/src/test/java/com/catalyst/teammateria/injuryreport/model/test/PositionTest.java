package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.Position;

public class PositionTest {

    Position                     target      = new Position();
    private static final String  TEST_STRING = "";
    private static final Integer TEST_INT    = 1;

    @Test
    public void testID() {
        target.setPositionId(TEST_INT);
        assertEquals(TEST_INT, target.getPositionId());
    }

    @Test
    public void testName() {
        target.setPositionName(TEST_STRING);
        assertEquals(TEST_STRING, target.getPositionName());
    }

    @Test
    public void testHash() {
        Position position1 = new Position();
        position1.setPositionId(1);
        target.setPositionId(2);
        assertFalse(position1.hashCode() == target.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        assertFalse(target.equals(TEST_STRING));
        Position position1 = new Position();
        target.setPositionName(TEST_STRING);
        position1.setPositionName(target.getPositionName());
        assertTrue(target.equals(position1));
    }
}
