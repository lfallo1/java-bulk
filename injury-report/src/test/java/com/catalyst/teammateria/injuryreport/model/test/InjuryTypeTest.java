package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.InjuryType;

public class InjuryTypeTest {

    InjuryType                  target      = new InjuryType();
    private static final String TEST_STRING = "";
    private static final int    TEST_INT    = 1;

    @Test
    public void testId() {
        target.setTypeId(TEST_INT);
        assertEquals(TEST_INT, target.getTypeId());
    }

    @Test
    public void testName() {
        target.setTypeName(TEST_STRING);
        assertEquals(TEST_STRING, target.getTypeName());
    }

    @Test
    public void testEquals() {
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        assertFalse(target.equals(TEST_STRING));
        InjuryType injuryType1 = new InjuryType();
        target.setTypeId(TEST_INT);
        injuryType1.setTypeId(target.getTypeId());
        assertTrue(target.equals(injuryType1));
    }

    @Test
    public void hashTest() {
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeId(2);
        target.setTypeId(1);
        assertNotEquals(injuryType.hashCode(), target.hashCode());
    }
}
