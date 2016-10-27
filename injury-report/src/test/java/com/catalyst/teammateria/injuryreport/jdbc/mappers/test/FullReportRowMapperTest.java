package com.catalyst.teammateria.injuryreport.jdbc.mappers.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.FullReportRowMapper;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.User;

public class FullReportRowMapperTest {

    private static final int EMPLOYEE_ID = 2;

    private static final int EXPECTED_EMPLOYEE_ID = 2;

    private static final int E_FIRST_NAME = 3;

    private static final String EXPECTED_E_FIRST_NAME = "foo";

    private static final int E_LAST_NAME = 4;

    private static final String EXPECTED_E_LAST_NAME = "bar";

    private static final int U_FIRST_NAME = 5;

    private static final String EXPECTED_U_FIRST_NAME = "hello";

    private static final int U_LAST_NAME = 6;

    private static final String EXPECTED_U_LAST_NAME = "bar";

    private static final int USER_ID = 7;

    private static final int EXPECTED_USER_ID = 7;

    private static final int REPORT_DATE = 8;

    private static final Date EXPECTED_REPORT_DATE = new Date(8);

    private static final int REPORTED_TO_MANAGEMENT = 9;

    private static final Date EXPECTED_REPORTED_MANAGEMENT_DATE = new Date(9);

    private static final int WEATHER_ID = 10;

    private static final int EXPECTED_WEATHER_ID = 10;

    private static final int INJURY_TYPE_ID = 11;

    private static final int EXPECTED_INJURY_TYPE_ID = 11;

    private static final int BODY_PART_ID = 12;

    private static final int EXPECTED_BODY_PART_ID = 12;

    private static final int TIME_OF_INJURY = 13;

    private static final Time EXPECTED_TIME_OF_INJURY = new Time(13);

    private static final int DESCRIPTION = 14;

    private static final String EXPECTED_DESCRIPTION = "14";

    private static final int APPROVER_COMMENTS = 15;

    private static final String EXPECTED_APPROVER_COMMENTS = "15";

    private FullReportRowMapper target;

    private ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new FullReportRowMapper();
    }

    @Test
    public void mapRowTest() {
        Report returnedReport = null;
        try {
            // Return the expected employee
            when(rs.getInt(EMPLOYEE_ID)).thenReturn(EXPECTED_EMPLOYEE_ID);
            when(rs.getString(E_FIRST_NAME)).thenReturn(EXPECTED_E_FIRST_NAME);
            when(rs.getString(E_LAST_NAME)).thenReturn(EXPECTED_E_LAST_NAME);
            // Return the expected user employee
            when(rs.getString(U_FIRST_NAME)).thenReturn(EXPECTED_U_FIRST_NAME);
            when(rs.getString(U_LAST_NAME)).thenReturn(EXPECTED_U_LAST_NAME);
            when(rs.getInt(USER_ID)).thenReturn(EXPECTED_USER_ID);
            // Return the expected dates
            when(rs.getDate(REPORT_DATE)).thenReturn(EXPECTED_REPORT_DATE);
            when(rs.getDate(REPORTED_TO_MANAGEMENT)).thenReturn(
                    EXPECTED_REPORTED_MANAGEMENT_DATE);
            // Return the expected report details
            when(rs.getInt(WEATHER_ID)).thenReturn(EXPECTED_WEATHER_ID);
            when(rs.getInt(INJURY_TYPE_ID)).thenReturn(EXPECTED_INJURY_TYPE_ID);
            when(rs.getInt(BODY_PART_ID)).thenReturn(EXPECTED_BODY_PART_ID);
            when(rs.getTime(TIME_OF_INJURY))
                    .thenReturn(EXPECTED_TIME_OF_INJURY);
            when(rs.getString(DESCRIPTION)).thenReturn(EXPECTED_DESCRIPTION);
            when(rs.getString(APPROVER_COMMENTS)).thenReturn(
                    EXPECTED_APPROVER_COMMENTS);
            // Get the report with the mocked resultset
            returnedReport = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        // Assert that the correct employee was returned
        Employee returnedEmployee = returnedReport.getEmployee();
        assertTrue(returnedEmployee.getEmployeeId() == EXPECTED_EMPLOYEE_ID);
        assertEquals(returnedEmployee.getFirstName(), EXPECTED_E_FIRST_NAME);
        assertEquals(returnedEmployee.getLastName(), EXPECTED_E_LAST_NAME);
        // Assert that the expected user was returned
        User returnedUser = returnedReport.getReporter();
        assertEquals(returnedUser.getEmployee().getFirstName(),
                EXPECTED_U_FIRST_NAME);
        assertEquals(returnedUser.getEmployee().getLastName(),
                EXPECTED_U_LAST_NAME);
        assertTrue(returnedUser.getUserId() == EXPECTED_USER_ID);
        // Assert that the expected dates were returned
        assertEquals(returnedReport.getDateOfReport(), EXPECTED_REPORT_DATE);
        assertEquals(returnedReport.getDateReportedToManagement(),
                EXPECTED_REPORTED_MANAGEMENT_DATE);
        // Assert that the expected report details were returned
        assertTrue(returnedReport.getWeather().getWeatherId() == EXPECTED_WEATHER_ID);
        assertTrue(returnedReport.getInjuryType().getTypeId() == EXPECTED_INJURY_TYPE_ID);
        assertTrue(returnedReport.getBodyPart().getBodyPartId() == EXPECTED_BODY_PART_ID);
        assertEquals(returnedReport.getTimeOfInjury(), EXPECTED_TIME_OF_INJURY);
        assertEquals(returnedReport.getDescription(), EXPECTED_DESCRIPTION);
        assertEquals(returnedReport.getApproverComments(),
                EXPECTED_APPROVER_COMMENTS);
    }
}
