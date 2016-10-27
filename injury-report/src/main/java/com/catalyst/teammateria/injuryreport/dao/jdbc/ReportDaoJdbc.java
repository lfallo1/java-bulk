package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.FilteredReportRowMapper;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.FullReportRowMapper;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.ReportEmployeeRowMapper;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.ReportRowMapper;
import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.Report;

/**
 * JDBC implementation of the ReportDao
 * 
 * @author dGrimes
 */
@Component
public class ReportDaoJdbc implements ReportDao {

    private static final String INSERT_REPORT = "INSERT INTO report (`employee`,`reporter`,`weather`,`bodypart`,`injury_type`,`approver`,`date_of_report`,`date_reported_to_management`,`time_of_injury`,`description`,`approver_comments`,`date_updated`,`enabled`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String GET_REPORT_BY_ID = "SELECT r.report_id, r.employee, r.reporter, r.weather, r.bodypart, r.injury_type, r.approver, r.date_of_report, r.date_reported_to_management, r.time_of_injury, r.description, r.approver_comments, r.date_updated, r.enabled FROM report AS r WHERE r.report_id = ?";

    private static final String UPDATE_REPORT = "UPDATE report AS r SET r.employee = ?, r.reporter = ?, r.weather = ?, r.bodypart = ?, r.injury_type = ?, r.approver = ?, r.date_of_report = ?, r.date_reported_to_management = ?, r.time_of_injury = ?, r.description = ?, r.approver_comments = ?, r.date_updated = ?, r.enabled = ? WHERE r.report_id = ?";

    private static final String UPDATE_REPORT_FROM_BEAN = "UPDATE report AS r SET r.weather = ?, r.bodypart = ?, r.injury_type = ?, r.approver = ?, r.date_of_report = ?, r.date_reported_to_management = ?, r.time_of_injury = ?, r.description = ?, r.approver_comments = ?, r.date_updated = ? WHERE r.report_id = ?";

    private static final String REMOVE_REPORT = "DELETE FROM report WHERE report_id = ?";

    private static final String GET_ALL_REPORTS = "SELECT r.report_id, r.employee, r.reporter, r.weather, r.bodypart, r.injury_type, r.approver, r.date_of_report, r.date_reported_to_management, r.time_of_injury, r.description, r.approver_comments, r.date_updated, r.enabled FROM report AS r";

    private static final String GET_ALL_REPORTS_WITH_EMP_ID = "SELECT r.report_id, r.employee, r.reporter, r.weather, r.bodypart, r.injury_type, r.approver, r.date_of_report, r.date_reported_to_management, r.time_of_injury, r.description, r.approver_comments, r.date_updated, r.enabled, e.first_name, e.last_name, e.position_id FROM report r INNER JOIN employee e on r.employee = e.employee_id";

    private static final String GET_FILTERED_REPORTS = "SELECT r.report_id, e.first_name, e.last_name, r.date_of_report, r.enabled FROM report AS r JOIN employee AS e ON e.employee_id = r.employee WHERE r.report_id LIKE ? AND e.first_name LIKE ? AND e.last_name LIKE ? AND r.date_of_report LIKE ? AND r.enabled = ?";

    private static final String GET_FULL_REPORT_BY_ID = "SELECT r.report_id, r.employee, e.first_name, e.last_name, user.first_name, user.last_name, u.user_id, r.date_of_report, r.date_reported_to_management, r.weather, r.injury_type, r.bodypart, r.time_of_injury, r.description, r.approver_comments FROM injury.report AS r LEFT JOIN injury.employee AS e ON r.employee = e.employee_id LEFT JOIN injury.user AS u ON r.reporter = u.user_id LEFT JOIN injury.employee AS user ON u.employee_id = user.employee_id WHERE r.report_id = ?";

    private JdbcTemplate jdbcTemplate;

    private KeyHolder keyHolder = new GeneratedKeyHolder();

    /**
     * Sets the JdbcTemplate using the provided dataSource.
     * 
     * @param dataSource
     */
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Set the value of the keyHolder
     * 
     * @param keyHolder
     */
    public void setKeyHolder(KeyHolder keyHolder) {
        this.keyHolder = keyHolder;
    }

    /**
     * For adding a JdbcTemplate object directly.
     * 
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addReport(Report newObject) {
        this.jdbcTemplate.update(INSERT_REPORT, newObject.getEmployee()
                .getEmployeeId(), newObject.getReporter().getUserId(),
                newObject.getWeather().getWeatherId(), newObject.getBodyPart()
                        .getBodyPartId(),
                newObject.getInjuryType().getTypeId(), newObject.getApprover()
                        .getUserId(), newObject.getDateOfReport(), newObject
                        .getDateReportedToManagement(), newObject
                        .getTimeOfInjury(), newObject.getDescription(),
                newObject.getApproverComments(), newObject.getDateUpdated(),
                newObject.getEnabled());
    }

    @Override
    public Integer addReport(InjuryReportBean injuryReportBean) {
        PreparedStatementCreator psc = new PreparedStatementCreatorInsertReport(
                injuryReportBean, INSERT_REPORT);
        jdbcTemplate.update(psc, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Report getReportById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_REPORT_BY_ID,
                new Object[] {key}, new ReportRowMapper());
    }

    @Override
    public void updateReport(Report newObject) {
        this.jdbcTemplate.update(UPDATE_REPORT, newObject.getEmployee()
                .getEmployeeId(), newObject.getReporter().getUserId(),
                newObject.getWeather().getWeatherId(), newObject.getBodyPart()
                        .getBodyPartId(),
                newObject.getInjuryType().getTypeId(), newObject.getApprover()
                        .getUserId(), newObject.getDateOfReport(), newObject
                        .getDateReportedToManagement(), newObject
                        .getTimeOfInjury(), newObject.getDescription(),
                newObject.getApproverComments(), newObject.getDateUpdated(),
                newObject.getEnabled(), newObject.getReportId());
    }

    @Override
    public void removeReport(Report newObject) {
        this.jdbcTemplate.update(REMOVE_REPORT, newObject.getReportId());
    }

    @Override
    public List<Report> getAll() {
        return this.jdbcTemplate.query(GET_ALL_REPORTS, new ReportRowMapper());
    }

    @Override
    public List<Report> getAllWithEmployee() {
        return this.jdbcTemplate.query(GET_ALL_REPORTS_WITH_EMP_ID,
                new ReportEmployeeRowMapper());
    }

    @Override
    public List<Report> getFilterableReports(String reportId, String firstName,
            String lastName, String date, boolean status) {
        return this.jdbcTemplate.query(GET_FILTERED_REPORTS, new Object[] {
                reportId, firstName, lastName, date, status},
                new FilteredReportRowMapper());
    }

    @Override
    public Report getFullReportById(int id) {
        return this.jdbcTemplate.queryForObject(GET_FULL_REPORT_BY_ID,
                new Object[] {id}, new FullReportRowMapper());
    }

    @Override
    public void updateReport(int id, AdminProofingBean adminProofingBean,
            int userId) {
        this.jdbcTemplate.update(
                UPDATE_REPORT_FROM_BEAN,
                new Object[] {adminProofingBean.getWeatherId(),
                        adminProofingBean.getBodyPartId(),
                        adminProofingBean.getTypeId(), userId,
                        adminProofingBean.getDateOfReport(),
                        adminProofingBean.getDateReportedToManagement(),
                        adminProofingBean.getTimeOfInjury(),
                        adminProofingBean.getDescription(),
                        adminProofingBean.getApproverComments(),
                        new Date(System.currentTimeMillis()), id});
    }
}
