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

import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.ReportEmployeeRowMapper;
import com.catalyst.teammateria.injuryreport.model.Report;


public class ReportEmployeeRowMapperTest {
    private static final int REPORT_FIELD = 1;

    private static final int EMPLOYEE_FIELD = 2;

    private static final int REPORTER_FIELD = 3;

    private static final int WEATHER_FIELD = 4;

    private static final int BODYPART_FIELD = 5;

    private static final int INJURY_FIELD = 6;

    private static final int APPROVER_FIELD = 7;

    private static final int DATE_OF_REPORT_FIELD = 8;

    private static final int DATE_REPORTED_TO_MANAGEMENT_FIELD = 9;

    private static final int TIME_OF_INJURY_FIELD = 10;

    private static final int DESCRIPTION_FIELD = 11;

    private static final int APPROVER_COMMENTS_FIELD = 12;

    private static final int DATE_UPDATED_FIELD = 13;

    private static final int ENABLED_FIELD = 14;
    
    private static final int EMPLOYEE_FIRSTNAME_FIELD = 15;
    
    private static final int EMPLOYEE_LASTNAME_FIELD = 16;
    
    private static final int EMPLOYEE_POSITION_ID_FIELD = 17;
    
    private ReportEmployeeRowMapper target;
    
    private ResultSet rs;
    
    @Before
    public void setup() {
        rs = mock(ResultSet.class);
        target = new ReportEmployeeRowMapper();
    }
    
    @Test
    public void rowMapTest() {
        Report report = new Report();
        Date expectedDateOfReport = new Date(0);
        Date expectedDateReportedToManagement = new Date(1);
        Time expectedTimeOfInjury = new Time(0);
        Date expectedDateUpdated = new Date(2);
        try {
            when(rs.getInt(REPORT_FIELD)).thenReturn(1);
            when(rs.getInt(EMPLOYEE_FIELD)).thenReturn(2);
            when(rs.getInt(REPORTER_FIELD)).thenReturn(
                    5);
            when(rs.getInt(WEATHER_FIELD)).thenReturn(3);
            when(rs.getInt(BODYPART_FIELD)).thenReturn(2);
            when(rs.getInt(INJURY_FIELD)).thenReturn(3);
            when(rs.getInt(APPROVER_FIELD)).thenReturn(1);
            // Date fields
            when(rs.getDate(DATE_OF_REPORT_FIELD)).thenReturn(
                    expectedDateOfReport);
            when(rs.getDate(DATE_REPORTED_TO_MANAGEMENT_FIELD)).thenReturn(
                    expectedDateReportedToManagement);
            when(rs.getTime(TIME_OF_INJURY_FIELD)).thenReturn(
                    expectedTimeOfInjury);
            when(rs.getString(DESCRIPTION_FIELD)).thenReturn(
                    "test");
            when(rs.getString(APPROVER_COMMENTS_FIELD)).thenReturn(
                    "test comments");
            when(rs.getDate(DATE_UPDATED_FIELD))
                    .thenReturn(expectedDateUpdated);
            when(rs.getBoolean(ENABLED_FIELD)).thenReturn(true);
            when(rs.getString(EMPLOYEE_FIRSTNAME_FIELD)).thenReturn("Alex");
            when(rs.getString(EMPLOYEE_LASTNAME_FIELD)).thenReturn("Test");
            when(rs.getInt(EMPLOYEE_POSITION_ID_FIELD)).thenReturn(2);
            // Update report for assertions
            report = target.mapRow(rs, 1);
        } catch (SQLException e) {
            assert false;
        }
        assertTrue(report.getReportId() == 1);
        assertTrue(report.getEmployee().getEmployeeId() == 2);
        assertTrue(report.getReporter().getUserId() == 5);
        assertTrue(report.getWeather().getWeatherId() == 3);
        assertTrue(report.getBodyPart().getBodyPartId() == 2);
        assertTrue(report.getInjuryType().getTypeId() == 3);
        assertTrue(report.getApprover().getUserId() == 1);
        assertEquals(report.getDateOfReport(), expectedDateOfReport);
        assertEquals(report.getDateReportedToManagement(),
                expectedDateReportedToManagement);
        assertEquals(report.getTimeOfInjury(), expectedTimeOfInjury);
        assertEquals(report.getDescription(), "test");
        assertEquals(report.getApproverComments(), "test comments");
        assertEquals(report.getDateUpdated(), expectedDateUpdated);
        assertTrue(report.getEnabled());
        assertEquals(report.getEmployee().getFirstName() , "Alex");
        assertEquals(report.getEmployee().getLastName() , "Test");
        assertTrue(report.getEmployee().getPosition().getPositionId() == 2);
    }
}
