package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementCreator;

import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;

/**
 * PreparedStatementCreatorInsertReport
 * -Implementation of PrepareStatementCreator
 * @author lfallon
 *
 */
public class PreparedStatementCreatorInsertReport implements PreparedStatementCreator {
    private static final Integer EMPLOYEE_ID_COLUMN = 1;
    private static final Integer REPORTER_ID_COLUMN = 2;
    private static final Integer WEATHER_ID_COLUMN = 3;
    private static final Integer BODYPART_ID_COLUMN = 4;
    private static final Integer INJURY_TYPE_ID_COLUMN = 5;
    private static final Integer APPROVER_ID_COLUMN = 6;
    private static final Integer REPORT_DATE_COLUMN = 7;
    private static final Integer REPORT_MANAGEMENT_DATE_COLUMN = 8;
    private static final Integer TIME_OF_INJURY_COLUMN = 9;
    private static final Integer DESCRIPTION_COLUMN = 10;
    private static final Integer APPROVER_COMMENTS_COLUMN = 11;
    private static final Integer DATE_UPDATED_COLUMN = 12;
    private static final Integer ENABLED_COLUMN = 13;	
	private final InjuryReportBean injuryReport;
	private final String sql;

	/**
	 * Create Prepared Statement for inserting new injury report into db
	 * @param injuryReport
	 * @param sql
	 */
	public PreparedStatementCreatorInsertReport(InjuryReportBean injuryReport, String sql) {
		this.injuryReport = injuryReport;
		this.sql = sql;
	}

	/**
	 * Create Prepared Statement for inserting new injury report into db
	 */
	public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	    PreparedStatement ps =
	        connection.prepareStatement(sql, new String[] {"report_id"});
	    ps.setInt(EMPLOYEE_ID_COLUMN, injuryReport.getEmployeeId());
	    ps.setInt(REPORTER_ID_COLUMN, injuryReport.getReporterId());
	    ps.setInt(WEATHER_ID_COLUMN, injuryReport.getWeatherId());
	    ps.setInt(BODYPART_ID_COLUMN, injuryReport.getBodyPartId());
	    ps.setInt(INJURY_TYPE_ID_COLUMN, injuryReport.getInjuryTypeId());
	    ps.setString(APPROVER_ID_COLUMN, null);
	    ps.setString(REPORT_DATE_COLUMN, injuryReport.getReportDate());
	    ps.setString(REPORT_MANAGEMENT_DATE_COLUMN, injuryReport.getReportManagementDate());
	    ps.setString(TIME_OF_INJURY_COLUMN, injuryReport.getTimeOfInjury());
	    ps.setString(DESCRIPTION_COLUMN, injuryReport.getDescription());
	    ps.setString(APPROVER_COMMENTS_COLUMN, null);
	    ps.setString(DATE_UPDATED_COLUMN, null);
	    ps.setBoolean(ENABLED_COLUMN, false);
	    return ps;
	}
}

