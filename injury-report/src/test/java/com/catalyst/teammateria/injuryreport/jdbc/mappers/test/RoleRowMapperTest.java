package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.RoleRowMapper;
import com.catalyst.teammateria.injuryreport.model.Role;

public class RoleRowMapperTest {
    
    private static final int ROLE_ID_FIELD = 1;
    
    private static final int EXPECTED_ROLE_ID = 1;

    private static final int ROLE_NAME_FIELD = 2;
    
    private static final String EXPECTED_ROLE_NAME = "foo";
    
    private RoleRowMapper target;
    
    private ResultSet rs;
    
    @Before
    public void setup(){
        rs = mock(ResultSet.class);
        target = new RoleRowMapper();
    }
    
    @Test
    public void mapRowTest(){
        Role returnedRole = new Role();
        try {
            when(rs.getInt(ROLE_ID_FIELD)).thenReturn(EXPECTED_ROLE_ID);
            when(rs.getString(ROLE_NAME_FIELD)).thenReturn(EXPECTED_ROLE_NAME);
            returnedRole = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(returnedRole.getRoleId() == EXPECTED_ROLE_ID);
        assertEquals(returnedRole.getRoleName(), EXPECTED_ROLE_NAME);
    }
}
