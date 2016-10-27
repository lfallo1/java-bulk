package com.catalyst.teammateria.injuryreport.model.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Time;

import org.junit.Test;

import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

public class ReportTest {

    Report target = new Report();

    private static final String TEST_STRING = "";

    private static final int TEST_INT = 1;

    private static final Boolean BOOL = false;

    Date DATE;

    Time TIME;

    @Test
    public void testId() {
        target.setReportId(TEST_INT);
        assertEquals(target.getReportId(), TEST_INT);
    }

    @Test
    public void testGetEmployee() {
        Employee employee = new Employee();
        target.setEmployee(employee);
        assertEquals(target.getEmployee(), employee);
    }

    @Test
    public void testReporter() {
        User reporter = new User();
        target.setReporter(reporter);
        assertEquals(target.getReporter(), reporter);
    }

    @Test
    public void testWeather() {
        Weather weather = new Weather();
        target.setWeather(weather);
        assertEquals(target.getWeather(), weather);
    }

    @Test
    public void testBodyPart() {
        BodyPart bodyPart = new BodyPart();
        target.setBodyPart(bodyPart);
        assertEquals(target.getBodyPart(), bodyPart);
    }

    @Test
    public void testInjuryType() {
        InjuryType injuryType = new InjuryType();
        target.setInjuryType(injuryType);
        assertEquals(target.getInjuryType(), injuryType);
    }

    @Test
    public void testApprover() {
        User approver = new User();
        target.setApprover(approver);
        assertEquals(target.getApprover(), approver);
    }

    @Test
    public void testDateOfReport() {
        target.setDateOfReport(DATE);
        assertEquals(target.getDateOfReport(), DATE);
    }

    @Test
    public void testDateReported() {
        target.setDateReportedToManagement(DATE);
        assertEquals(target.getDateReportedToManagement(), DATE);
    }

    @Test
    public void testTime() {
        target.setTimeOfInjury(TIME);
        assertEquals(target.getTimeOfInjury(), TIME);
    }

    @Test
    public void testDescription() {
        target.setDescription(TEST_STRING);
        assertEquals(target.getDescription(), TEST_STRING);
    }

    @Test
    public void testComments() {
        target.setApproverComments(TEST_STRING);
        assertEquals(target.getApproverComments(), TEST_STRING);
    }

    @Test
    public void testUpdateDate() {
        target.setDateUpdated(DATE);
        assertEquals(target.getDateUpdated(), DATE);
    }

    @Test
    public void testEnabled() {
        target.setEnabled(BOOL);
        assertEquals(target.getEnabled(), BOOL);
    }

    @Test
    public void testHash() {
        Report report1 = new Report();
        report1.setReportId(TEST_INT);
        target.setReportId(2);
        assertNotEquals(target.hashCode(), report1.hashCode());
    }

    @Test
    public void testEquals() {
        assertFalse(target.equals(null));
        assertTrue(target.equals(target));
        assertFalse(target.equals(TEST_STRING));
        Report report1 = new Report();
        target.setDescription(TEST_STRING);
        report1.setDescription(target.getDescription());
        assertTrue(target.equals(report1));
    }
}
