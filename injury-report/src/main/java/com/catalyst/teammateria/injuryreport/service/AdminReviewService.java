package com.catalyst.teammateria.injuryreport.service;

import java.util.List;

import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.model.Report;

/**
 * Admin review service interface
 * 
 * @author dGrimes
 */
public interface AdminReviewService {

    /**
     * Gets a list of reports with potential filters to be made safe before
     * sending to the DAO
     * 
     * @param reportId
     * @param firstName
     * @param lastName
     * @param date
     * @param status
     * @return List of reports that conform to the filters
     */
    List<Report> getReports(String reportId, String firstName, String lastName,
            String date, boolean status);

    /**
     * Turn the string number into a searchable SQL safe string
     * 
     * @param number
     *            - String number to make safe
     * @return safe string '%' + "[0-9]+" + '%' or '%'
     */
    String makeNumberSafe(String number);

    /**
     * Turn the string name into a searchable SQL safe string
     * 
     * @param name
     *            - String name to make safe
     * @return safe string '%' + "[A-Za-z]+" + '%' or '%'
     */
    String makeNameSafe(String name);

    /**
     * Make the date safe
     * 
     * @param date
     *            - String containing the date
     * @return Full date search string ("r.date_of_report = " + date) or empty
     *         string
     */
    String makeDateSafe(String date);

    /**
     * A simple method that flips the "enabled" boolean associated with the id
     * 
     * @param id
     */
    void enableDisable(int id);

    /**
     * Used for proofing, returns the whole wealth of information for the report
     * 
     * @param id
     *            - the reportId
     * @return the report corresponding to the id
     */
    Report getFullReport(int id);

    /**
     * This is used to update the report object
     * 
     * @param id
     *            - reportId of the report to update
     * @param adminProofingBean
     *            - the information to update
     * @param username
     *            - of the user to place in the approver position
     */
    void updateReport(int id, AdminProofingBean adminProofingBean,
            String username);

    /**
     * Protective method to defend against external attacks
     * 
     * @param string
     *            - string to make safe
     * @return a safe string
     */
    String stripChars(String string);
}
