package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;

/**
 * Special row mapper used with the admin review service
 * 
 * @author dGrimes
 */
public class FilteredReportRowMapper implements RowMapper<Report> {

    private static final int REPORT_ID_FIELD = 1;

    private static final int FIRST_NAME_FIELD = 2;

    private static final int LAST_NAME_FIELD = 3;

    private static final int DATE_OF_REPORT_FIELD = 4;

    private static final int ENABLED_FIELD = 5;

    @Override
    public Report mapRow(ResultSet rs, int rowNum) throws SQLException {
        Report report = new Report();
        Employee employee = new Employee();
        employee.setFirstName(rs.getString(FIRST_NAME_FIELD));
        employee.setLastName(rs.getString(LAST_NAME_FIELD));
        report.setEmployee(employee);
        report.setReportId(rs.getInt(REPORT_ID_FIELD));
        report.setDateOfReport(rs.getDate(DATE_OF_REPORT_FIELD));
        report.setEnabled(rs.getBoolean(ENABLED_FIELD));
        return report;
    }
}
