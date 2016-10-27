package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.BodyPart;

public class BodyPartTest {
    
    BodyPart target; 
    
    @Before
    public void setup(){
        target = new BodyPart();
    }
    
    @Test
    public void getBodyPartIdTest(){
        target.setBodyPartId(1);
        assertEquals(1, target.getBodyPartId());
    }
    
    @Test
    public void getBodyPartNameTest(){
        target.setBodyPartName("foo");
        assertEquals("foo", target.getBodyPartName());
    }
    
    @Test
    public void equalsTest(){
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        String test = "Test";
        assertFalse(target.equals(test));
        BodyPart testUser = new BodyPart();
        target.setBodyPartId(1);
        testUser.setBodyPartId(target.getBodyPartId());
        assertEquals(target, testUser);
    }
    
    @Test
    public void hashCodeTest() {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(1);
        target.setBodyPartId(2);
        int user1Hash = bodyPart.hashCode();
        int targetHash = target.hashCode();
        assertFalse(targetHash == user1Hash);
    }
    
}
