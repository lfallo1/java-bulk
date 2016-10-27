package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.EmployeeRowMapperWithPositionName;
import com.catalyst.teammateria.injuryreport.model.Employee;

public class EmployeeRowMapperWithPositionNameTest {

    private EmployeeRowMapperWithPositionName target;

    private ResultSet rs;

    private static final int EMPLOYEEID_FIELD = 1;

    private static final int EXPECTED_EMPLOYEEID = 1;

    private static final int EMPLOYEE_FIRSTNAME_FIELD = 2;

    private static final String EXPECTED_EMPLOYEE_FIRSTNAME = "foo";

    private static final int EMPLOYEE_LASTNAME_FIELD = 3;

    private static final String EXPECTED_EMPLOYEE_LASTNAME = "bar";

    private static final int EMPLOYEE_POSITION_NAME_FIELD = 4;

    private static final String EXPECTED_EMPLOYEE_POSITION_NAME = "manager";

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new EmployeeRowMapperWithPositionName();
    }

    @Test
    public void mapRowTest() {
        try {
            // Return expected values from the result set
            when(rs.getInt(EMPLOYEEID_FIELD)).thenReturn(EXPECTED_EMPLOYEEID);
            when(rs.getString(EMPLOYEE_FIRSTNAME_FIELD)).thenReturn(
                    EXPECTED_EMPLOYEE_FIRSTNAME);
            when(rs.getString(EMPLOYEE_LASTNAME_FIELD)).thenReturn(
                    EXPECTED_EMPLOYEE_LASTNAME);
            when(rs.getString(EMPLOYEE_POSITION_NAME_FIELD)).thenReturn(
                    EXPECTED_EMPLOYEE_POSITION_NAME);
            // Assert that the returned employee has the expected values
            Employee returnedEmployee = target.mapRow(rs, 1);
            assertTrue(returnedEmployee.getEmployeeId() == EXPECTED_EMPLOYEEID);
            assertEquals(returnedEmployee.getFirstName(),
                    EXPECTED_EMPLOYEE_FIRSTNAME);
            assertEquals(returnedEmployee.getLastName(),
                    EXPECTED_EMPLOYEE_LASTNAME);
            assertEquals(returnedEmployee.getPosition().getPositionName(),
                    EXPECTED_EMPLOYEE_POSITION_NAME);
        } catch (SQLException e) {
            assert false;
        }
    }
}
