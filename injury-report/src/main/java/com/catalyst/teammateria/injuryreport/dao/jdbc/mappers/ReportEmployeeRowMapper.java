package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Row mapper for returning a newReport with full newEmployee object
 * 
 * @author lfallon
 */
public class ReportEmployeeRowMapper implements RowMapper<Report> {

    private static final int REPORT = 1;

    private static final int EMPLOYEE = 2;

    private static final int REPORTER = 3;

    private static final int WEATHER = 4;

    private static final int BODYPART = 5;

    private static final int INJURY = 6;

    private static final int APPROVER = 7;

    private static final int DATE_OF_REPORT = 8;

    private static final int DATE_REPORTED_TO_MANAGEMENT = 9;

    private static final int TIME_OF_INJURY = 10;

    private static final int DESCRIPTION = 11;

    private static final int APPROVER_COMMENTS = 12;

    private static final int DATE_UPDATED = 13;

    private static final int ENABLED = 14;

    private static final int EMPLOYEE_FIRSTNAME = 15;

    private static final int EMPLOYEE_LASTNAME = 16;

    private static final int EMPLOYEE_POSITION_ID = 17;

    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report newReport = new Report();
        newReport.setReportId(rs.getInt(REPORT));
        Employee newEmployee = new Employee();
        newEmployee.setEmployeeId(rs.getInt(EMPLOYEE));
        newEmployee.setFirstName(rs.getString(EMPLOYEE_FIRSTNAME));
        newEmployee.setLastName(rs.getString(EMPLOYEE_LASTNAME));
        Position newPosition = new Position();
        newPosition.setPositionId(rs.getInt(EMPLOYEE_POSITION_ID));
        newEmployee.setPosition(newPosition);
        newReport.setEmployee(newEmployee);
        User newReporter = new User();
        newReporter.setUserId(rs.getInt(REPORTER));
        newReport.setReporter(newReporter);
        Weather newWeather = new Weather();
        newWeather.setWeatherId(rs.getInt(WEATHER));
        newReport.setWeather(newWeather);
        BodyPart newBodyPart = new BodyPart();
        newBodyPart.setBodyPartId(rs.getInt(BODYPART));
        newReport.setBodyPart(newBodyPart);
        InjuryType newInjuryType = new InjuryType();
        newInjuryType.setTypeId(rs.getInt(INJURY));
        newReport.setInjuryType(newInjuryType);
        User newApprover = new User();
        newApprover.setUserId(rs.getInt(APPROVER));
        newReport.setApprover(newApprover);
        newReport.setDateOfReport(rs.getDate(DATE_OF_REPORT));
        newReport.setDateReportedToManagement(rs
                .getDate(DATE_REPORTED_TO_MANAGEMENT));
        newReport.setTimeOfInjury(rs.getTime(TIME_OF_INJURY));
        newReport.setDescription(rs.getString(DESCRIPTION));
        newReport.setApproverComments(rs.getString(APPROVER_COMMENTS));
        newReport.setDateUpdated(rs.getDate(DATE_UPDATED));
        newReport.setEnabled(rs.getBoolean(ENABLED));
        return newReport;
    }
}
