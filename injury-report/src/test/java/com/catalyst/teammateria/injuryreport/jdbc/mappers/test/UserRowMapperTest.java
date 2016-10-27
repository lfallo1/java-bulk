package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.UserRowMapper;
import com.catalyst.teammateria.injuryreport.model.User;

public class UserRowMapperTest {

    private static final int USER_ID_FIELD = 1;

    private static final int EXPECTED_USER_ID = 1;

    private static final int USER_NAME_FIELD = 2;

    private static final String EXPECTED_USER_NAME = "foo";

    private static final int ROLE_ID_FIELD = 3;

    private static final int EXPECTED_ROLE_ID = 3;

    private static final int EMPLOYEE_ID_FIELD = 4;

    private static final int EXPECTED_EMPLOYEE_ID = 4;

    private UserRowMapper target;

    private ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new UserRowMapper();
    }

    @Test
    public void mapRowTest() {
        User returnedUser = new User();
        try {
            when(rs.getInt(USER_ID_FIELD)).thenReturn(EXPECTED_USER_ID);
            when(rs.getString(USER_NAME_FIELD)).thenReturn(EXPECTED_USER_NAME);
            when(rs.getInt(ROLE_ID_FIELD)).thenReturn(EXPECTED_ROLE_ID);
            when(rs.getInt(EMPLOYEE_ID_FIELD)).thenReturn(EXPECTED_EMPLOYEE_ID);
            returnedUser = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(returnedUser.getUserId() == EXPECTED_USER_ID);
        assertEquals(returnedUser.getUserName(), EXPECTED_USER_NAME);
        assertTrue(returnedUser.getRole().getRoleId() == EXPECTED_ROLE_ID);
        assertTrue(returnedUser.getEmployee().getEmployeeId() == EXPECTED_EMPLOYEE_ID);
    }
}
