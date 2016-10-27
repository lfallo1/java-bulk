package com.catalyst.teammateria.injuryreport.webservices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.service.AdminReviewService;

/**
 * Admin review webservice for use with the Admin review page
 * 
 * @author dGrimes
 */
@RestController
public class AdminReviewWebService {

    private AdminReviewService adminReviewService;

    @Autowired
    public void setAdminReviewService(AdminReviewService adminReviewService) {
        this.adminReviewService = adminReviewService;
    }

    /**
     * The GET request to snag up reports based on filters
     * 
     * @param reportId
     * @param firstName
     * @param lastName
     * @param date
     * @param status
     * @return List of reports appropriately filtered
     */
    @RequestMapping (value = "/adminreview/reports", method = RequestMethod.GET)
    public List<Report> getReports(String reportId, String firstName,
            String lastName, String date, boolean status) {
        return adminReviewService.getReports(reportId, firstName, lastName,
                date, status);
    }

    /**
     * The GET request to get a detailed report for "admin proofing"
     * 
     * @param id
     * @return
     */
    @RequestMapping (value = "/adminreview/{id}", method = RequestMethod.GET)
    public Report getReportForProofing(@PathVariable int id) {
        return adminReviewService.getFullReport(id);
    }

    /**
     * PUT request to enable or disable a report
     * 
     * @param id
     *            - the report Id of the report to disable
     */
    @RequestMapping (value = "/adminreview/enable/{id}", method = RequestMethod.PUT)
    public void enableOrDisable(@PathVariable int id) {
        adminReviewService.enableDisable(id);
    }

    /**
     * PUT request to update a report
     * 
     * @param id
     *            - the report to update
     */
    @RequestMapping (value = "/adminreview/report/{id}", method = RequestMethod.PUT)
    public void putReport(@PathVariable int id,
            AdminProofingBean adminProofingBean, HttpServletRequest request) {
        adminReviewService.updateReport(id, adminProofingBean, request
                .getUserPrincipal().getName());
    }
}
