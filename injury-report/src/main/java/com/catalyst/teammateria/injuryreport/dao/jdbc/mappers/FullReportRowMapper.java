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
 * Special row mapper used with the admin proofing page
 * @author aDietrich
 *
 */
public class FullReportRowMapper implements RowMapper<Report> {

    private static final int REPORT_ID = 1;

    private static final int EMPLOYEE_ID = 2;

    private static final int E_FIRST_NAME = 3;

    private static final int E_LAST_NAME = 4;

    private static final int U_FIRST_NAME = 5;

    private static final int U_LAST_NAME = 6;

    private static final int USER_ID = 7;

    private static final int REPORT_DATE = 8;

    private static final int REPORTED_TO_MANAGEMENT = 9;

    private static final int WEATHER_ID = 10;

    private static final int INJURY_TYPE_ID = 11;

    private static final int BODY_PART_ID = 12;

    private static final int TIME_OF_INJURY = 13;

    private static final int DESCRIPTION = 14;

    private static final int APPROVER_COMMENTS = 15;

    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();
        Employee employee = new Employee();
        Employee userEmployee = new Employee();
        User user = new User();
        Weather weather = new Weather();
        BodyPart bodyPart = new BodyPart();
        InjuryType injury = new InjuryType();
        employee.setEmployeeId(rs.getInt(EMPLOYEE_ID));
        employee.setFirstName(rs.getString(E_FIRST_NAME));
        employee.setLastName(rs.getString(E_LAST_NAME));
        userEmployee.setFirstName(rs.getString(U_FIRST_NAME));
        userEmployee.setLastName(rs.getString(U_LAST_NAME));
        user.setUserId(rs.getInt(USER_ID));
        user.setEmployee(userEmployee);
        weather.setWeatherId(rs.getInt(WEATHER_ID));
        bodyPart.setBodyPartId(rs.getInt(BODY_PART_ID));
        injury.setTypeId(rs.getInt(INJURY_TYPE_ID));
        report.setEmployee(employee);
        report.setReporter(user);
        report.setReportId(rs.getInt(REPORT_ID));
        report.setWeather(weather);
        report.setBodyPart(bodyPart);
        report.setInjuryType(injury);
        report.setDateOfReport(rs.getDate(REPORT_DATE));
        report.setDateReportedToManagement(rs.getDate(REPORTED_TO_MANAGEMENT));
        report.setTimeOfInjury(rs.getTime(TIME_OF_INJURY));
        report.setDescription(rs.getString(DESCRIPTION));
        report.setApproverComments(rs.getString(APPROVER_COMMENTS));
        return report;
    }
}
