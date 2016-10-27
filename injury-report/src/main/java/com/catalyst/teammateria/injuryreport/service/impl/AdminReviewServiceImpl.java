package com.catalyst.teammateria.injuryreport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.service.AdminReviewService;
import com.catalyst.teammateria.injuryreport.utils.DateUtil;

/**
 * Implementation of the Admin Review Service for the admin review page
 * 
 * @author dGrimes
 */
@Service
public class AdminReviewServiceImpl implements AdminReviewService {

    private ReportDao reportDao;

    private UserDao userDao;

    @Autowired
    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Report> getReports(String reportId, String firstName,
            String lastName, String date, boolean status) {
        return reportDao.getFilterableReports(makeNumberSafe(reportId),
                makeNameSafe(firstName), makeNameSafe(lastName),
                makeDateSafe(date), status);
    }

    @Override
    public String makeNumberSafe(String number) {
        String newNumber = number.replaceAll("[^0-9]", "");
        return newNumber.equals("") ? "%" : '%' + newNumber + '%';
    }

    @Override
    public String makeNameSafe(String name) {
        String newName = name.replaceAll("[^A-Za-z]", "");
        return newName.equals("") ? "%" : '%' + newName + '%';
    }

    @Override
    public String makeDateSafe(String date) {
        if (date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return DateUtil.convertDateFormat(date);
        } else {
            return "%";
        }
    }

    @Override
    public void enableDisable(int id) {
        Report report = reportDao.getReportById(id);
        report.setEnabled( ! (report.getEnabled()));
        reportDao.updateReport(report);
    }

    @Override
    public Report getFullReport(int id) {
        return reportDao.getFullReportById(id);
    }

    @Override
    public void updateReport(int id, AdminProofingBean adminProofingBean,
            String username) {
        adminProofingBean.setDateOfReport(DateUtil
                .convertDateFormat(adminProofingBean.getDateOfReport()));
        adminProofingBean.setDateReportedToManagement(DateUtil
                .convertDateFormat(adminProofingBean
                        .getDateReportedToManagement()));
        adminProofingBean.setApproverComments(stripChars(adminProofingBean
                .getApproverComments()));
        adminProofingBean.setDescription(stripChars(adminProofingBean
                .getDescription()));
        reportDao.updateReport(id, adminProofingBean, userDao
                .getUserByUsername(username).getUserId());
    }

    @Override
    public String stripChars(String string) {
        return string.replaceAll("[^ A-Za-z0-9()/,.:]", "");
    }
}
