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

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.ReportRowMapper;
import com.catalyst.teammateria.injuryreport.model.Report;

public class ReportRowMapperTest {

    private static final int REPORT_FIELD = 1;

    private static final int EXPECTED_REPORT_ID = 1;

    private static final int EMPLOYEE_FIELD = 2;

    private static final int EXPECTED_EMPLOYEE_ID = 2;

    private static final int REPORTER_FIELD = 3;

    private static final int EXPECTED_REPORTER_USER_ID = 3;

    private static final int WEATHER_FIELD = 4;

    private static final int EXPECTED_WEATHER_ID = 4;

    private static final int BODYPART_FIELD = 5;

    private static final int EXPECTED_BODYPART_ID = 5;

    private static final int INJURY_FIELD = 6;

    private static final int EXPECTED_INJURY_ID = 6;

    private static final int APPROVER_FIELD = 7;

    private static final int EXPECTED_APPROVER_ID = 7;

    private static final int DATE_OF_REPORT_FIELD = 8;

    private static final int DATE_REPORTED_TO_MANAGEMENT_FIELD = 9;

    private static final int TIME_OF_INJURY_FIELD = 10;

    private static final int DESCRIPTION_FIELD = 11;

    private static final String EXPECTED_DESCRIPTION = "foo";

    private static final int APPROVER_COMMENTS_FIELD = 12;

    private static final String EXPECTED_COMMENTS = "bar";

    private static final int DATE_UPDATED_FIELD = 13;

    private static final int ENABLED_FIELD = 14;

    private ReportRowMapper target;

    private ResultSet rs;

    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new ReportRowMapper();
    }

    @Test
    public void rowMapTest() {
        Report report = new Report();
        Date expectedDateOfReport = new Date(0);
        Date expectedDateReportedToManagement = new Date(1);
        Time expectedTimeOfInjury = new Time(0);
        Date expectedDateUpdated = new Date(2);
        try {
            when(rs.getInt(REPORT_FIELD)).thenReturn(EXPECTED_REPORT_ID);
            when(rs.getInt(EMPLOYEE_FIELD)).thenReturn(EXPECTED_EMPLOYEE_ID);
            when(rs.getInt(REPORTER_FIELD)).thenReturn(
                    EXPECTED_REPORTER_USER_ID);
            when(rs.getInt(WEATHER_FIELD)).thenReturn(EXPECTED_WEATHER_ID);
            when(rs.getInt(BODYPART_FIELD)).thenReturn(EXPECTED_BODYPART_ID);
            when(rs.getInt(INJURY_FIELD)).thenReturn(EXPECTED_INJURY_ID);
            when(rs.getInt(APPROVER_FIELD)).thenReturn(EXPECTED_APPROVER_ID);
            // Date fields
            when(rs.getDate(DATE_OF_REPORT_FIELD)).thenReturn(
                    expectedDateOfReport);
            when(rs.getDate(DATE_REPORTED_TO_MANAGEMENT_FIELD)).thenReturn(
                    expectedDateReportedToManagement);
            when(rs.getTime(TIME_OF_INJURY_FIELD)).thenReturn(
                    expectedTimeOfInjury);
            when(rs.getString(DESCRIPTION_FIELD)).thenReturn(
                    EXPECTED_DESCRIPTION);
            when(rs.getString(APPROVER_COMMENTS_FIELD)).thenReturn(
                    EXPECTED_COMMENTS);
            when(rs.getDate(DATE_UPDATED_FIELD))
                    .thenReturn(expectedDateUpdated);
            when(rs.getBoolean(ENABLED_FIELD)).thenReturn(true);
            // Update report for assertions
            report = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(report.getReportId() == EXPECTED_REPORT_ID);
        assertTrue(report.getEmployee().getEmployeeId() == EXPECTED_EMPLOYEE_ID);
        assertTrue(report.getReporter().getUserId() == EXPECTED_REPORTER_USER_ID);
        assertTrue(report.getWeather().getWeatherId() == EXPECTED_WEATHER_ID);
        assertTrue(report.getBodyPart().getBodyPartId() == EXPECTED_BODYPART_ID);
        assertTrue(report.getInjuryType().getTypeId() == EXPECTED_INJURY_ID);
        assertTrue(report.getApprover().getUserId() == EXPECTED_APPROVER_ID);
        assertEquals(report.getDateOfReport(), expectedDateOfReport);
        assertEquals(report.getDateReportedToManagement(),
                expectedDateReportedToManagement);
        assertEquals(report.getTimeOfInjury(), expectedTimeOfInjury);
        assertEquals(report.getDescription(), EXPECTED_DESCRIPTION);
        assertEquals(report.getApproverComments(), EXPECTED_COMMENTS);
        assertEquals(report.getDateUpdated(), expectedDateUpdated);
        assertTrue(report.getEnabled());
    }
}
