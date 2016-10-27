package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.EmployeeRowMapper;
import com.catalyst.teammateria.injuryreport.model.Employee;

public class EmployeeRowMapperTest {
    
    private static final int EMPLOYEEID_FIELD = 1;
    
    private static final int EXPECTED_EMPLOYEE_ID = 1;

    private static final int EMPLOYEE_FIRSTNAME_FIELD = 2;
    
    private static final String EXPECTED_FIRST_NAME = "foo";

    private static final int EMPLOYEE_LASTNAME_FIELD = 3;
    
    private static final String EXPECTED_LAST_NAME = "bar";

    private static final int EMPLOYEE_POSITION_ID_FIELD = 4;
    
    private static final int EXPECTED_POSITION_ID = 4;

    private EmployeeRowMapper target;

    private ResultSet rs;
    
    @Before 
    public void setup(){
        rs = mock(ResultSet.class);
        target = new EmployeeRowMapper();
    }
    
    @Test
    public void mapRowTest(){
        Employee returnedEmployee = new Employee();
        try {
            when(rs.getInt(EMPLOYEEID_FIELD)).thenReturn(EXPECTED_EMPLOYEE_ID);
            when(rs.getString(EMPLOYEE_FIRSTNAME_FIELD)).thenReturn(EXPECTED_FIRST_NAME);
            when(rs.getString(EMPLOYEE_LASTNAME_FIELD)).thenReturn(EXPECTED_LAST_NAME);
            when(rs.getInt(EMPLOYEE_POSITION_ID_FIELD)).thenReturn(EXPECTED_POSITION_ID);
            returnedEmployee = target.mapRow(rs, 1);
        } catch (SQLException e) {
           assert false;
        }
        assertTrue(returnedEmployee.getEmployeeId() == EXPECTED_EMPLOYEE_ID);
        assertEquals(returnedEmployee.getFirstName(), EXPECTED_FIRST_NAME);
        assertEquals(returnedEmployee.getLastName(), EXPECTED_LAST_NAME);
        assertTrue(returnedEmployee.getPosition().getPositionId() == EXPECTED_POSITION_ID);
    }
}
