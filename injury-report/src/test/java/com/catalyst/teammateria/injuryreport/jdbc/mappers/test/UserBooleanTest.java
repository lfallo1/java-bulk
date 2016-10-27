package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.UserBoolean;


public class UserBooleanTest {
    
    private UserBoolean target;
    
    private ResultSet rs;
    
    @Before
    public void setup(){
        rs = mock(ResultSet.class);
        target = new UserBoolean();
    }
    
    @Test 
    public void mapRowTest(){
        try {
            // Return true if there is only 1 result
            when(rs.getInt(1)).thenReturn(1);
            assertTrue(target.mapRow(rs, 1));
            // Return fals if there is more than 1 result
            when(rs.getInt(1)).thenReturn(2);
            assertFalse(target.mapRow(rs, 1));
        } catch (SQLException e) {
            assert false;
        }
    }
    
}
