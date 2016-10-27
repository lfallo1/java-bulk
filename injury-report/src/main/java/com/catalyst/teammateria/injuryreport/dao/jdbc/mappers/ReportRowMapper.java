package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Base row mapper for the Report class
 * 
 * @author dGrimes
 */
public class ReportRowMapper implements RowMapper<Report> {

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

    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();
        report.setReportId(rs.getInt(REPORT_FIELD));
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(EMPLOYEE_FIELD));
        report.setEmployee(employee);
        User reporter = new User();
        reporter.setUserId(rs.getInt(REPORTER_FIELD));
        report.setReporter(reporter);
        Weather weather = new Weather();
        weather.setWeatherId(rs.getInt(WEATHER_FIELD));
        report.setWeather(weather);
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(rs.getInt(BODYPART_FIELD));
        report.setBodyPart(bodyPart);
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeId(rs.getInt(INJURY_FIELD));
        report.setInjuryType(injuryType);
        User approver = new User();
        approver.setUserId(rs.getInt(APPROVER_FIELD));
        report.setApprover(approver);
        report.setDateOfReport(rs.getDate(DATE_OF_REPORT_FIELD));
        report.setDateReportedToManagement(rs
                .getDate(DATE_REPORTED_TO_MANAGEMENT_FIELD));
        report.setTimeOfInjury(rs.getTime(TIME_OF_INJURY_FIELD));
        report.setDescription(rs.getString(DESCRIPTION_FIELD));
        report.setApproverComments(rs.getString(APPROVER_COMMENTS_FIELD));
        report.setDateUpdated(rs.getDate(DATE_UPDATED_FIELD));
        report.setEnabled(rs.getBoolean(ENABLED_FIELD));
        return report;
    }
}
