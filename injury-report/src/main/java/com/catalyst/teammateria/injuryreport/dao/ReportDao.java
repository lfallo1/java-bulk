package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.Report;

/**
 * Report Dao Interface
 * 
 * @author dGrimes
 */
public interface ReportDao {

    /**
     * create new object in database
     * 
     * @param new Report object
     */
    void addReport(Report newObject);

    /**
     * Read a Report object from database accessed by ID
     * 
     * @param key
     * @return Report
     */
    Report getReportById(Integer key);

    /**
     * Save changes made to a Report object
     * 
     * @param Report
     *            object
     */
    void updateReport(Report newObject);

    /**
     * Delete an Report from database
     * 
     * @param Report
     *            object
     */
    void removeReport(Report newObject);

    /**
     * Return a list of all Reports
     */
    List<Report> getAll();

    /**
     * Basic report add function
     * 
     * @param injuryReportBean
     *            - the report to add
     * @param confirmationNumber
     *            - the confirmation number
     */
    Integer addReport(InjuryReportBean injuryReportBean);

    /**
     * Get all injury reports with the employee object included
     * 
     * @return
     */
    List<Report> getAllWithEmployee();

    /**
     * Get a filtered list of reports.
     * 
     * @param reportId
     * @param firstName
     * @param lastName
     * @param date
     * @param status
     * @return filtered list
     */
    List<Report> getFilterableReports(String reportId, String firstName,
            String lastName, String date, boolean status);

    /**
     * Gets the whole host of information called for within the report proofing
     * page.
     * 
     * @param id
     *            - report id
     * @return detailed report
     */
    Report getFullReportById(int id);

    /**
     * Method to update data for a report
     * 
     * @param id
     *            - the reportId to update
     * @param adminProofingBean
     *            - the information to update
     * @param userId
     *            - the approver
     */
    void updateReport(int id, AdminProofingBean adminProofingBean, int userId);
}
