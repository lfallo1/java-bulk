package com.catalyst.teammateria.injuryreport.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.service.AdminReviewService;

@RunWith (MockitoJUnitRunner.class)
public class AdminReviewServiceImplTest {

    @InjectMocks
    private AdminReviewServiceImpl target;

    @Mock
    private Report report;

    @Mock
    private ReportDao reportDao;

    @Mock
    private UserDao userDao;

    @Mock
    private AdminReviewService adminReviewService;

    @Mock
    private AdminReviewServiceImpl adminReviewServiceImpl;

    // @Mock
    // private AdminProofingBean adminProofingBean;
    @Test
    public void getReportsTest() {
        List<Report> expected = new ArrayList<Report>();
        Mockito.when(
                reportDao.getFilterableReports(anyString(), anyString(),
                        anyString(), anyString(), anyBoolean())).thenReturn(
                expected);
        List<Report> actual = target.getReports(anyString(), anyString(),
                anyString(), anyString(), anyBoolean());
        assertEquals(expected, actual);
    }

    @Test
    public void makeNumberSafeTest() {
        assertEquals("%9%", target.makeNumberSafe("9"));
    }

    @Test
    public void makeNameSafeTest() {
        assertEquals("%name%", target.makeNameSafe("name"));
    }

    @Test
    public void makeValidDateSafeTest() {
        assertEquals(target.makeDateSafe("00/00/0000"), "0000-00-00");
    }

    @Test
    public void makeInvalidDateSafeTest() {
        assertEquals(target.makeDateSafe("x"), "%");
    }

    @Test
    public void getFullReportTest() {
        Report expected = new Report();
        Mockito.when(reportDao.getReportById(anyInt())).thenReturn(expected);
        Report actual = new Report();
        Mockito.when(target.getFullReport(anyInt())).thenReturn(actual);
        assertEquals(expected, actual);
    }

    @Test
    public void stripCharsTest() {
        assertEquals(".,/a12", target.stripChars(".,/a12=+-"));
    }

    @Test
    public void updateReportTest() {
        Report report = new Report();
        report.setReportId(1);
        AdminProofingBean bean = new AdminProofingBean();
        bean.setDateOfReport("12/12/1222");
        bean.setDateReportedToManagement("12/12/1223");
        bean.setApproverComments("comments");
        bean.setDescription("description");
        User expectedUser = new User();
        Mockito.when(userDao.getUserByUsername(anyString())).thenReturn(
                expectedUser);
        target.updateReport(1, bean, expectedUser.getUserName());
        Mockito.verify(reportDao).updateReport(1, bean,
                expectedUser.getUserId());
    }

    @Test
    public void enableDisableEnabledTest() {
        Report expectedReport = new Report();
        Mockito.when(reportDao.getReportById(anyInt())).thenReturn(
                expectedReport);
        expectedReport.setEnabled(true);
        target.enableDisable(expectedReport.getReportId());
        Mockito.verify(reportDao).updateReport(expectedReport);
    }

    @Test
    public void enableDisableDisabledTest() {
        Report expectedReport = new Report();
        Mockito.when(reportDao.getReportById(anyInt())).thenReturn(
                expectedReport);
        expectedReport.setEnabled(false);
        target.enableDisable(expectedReport.getReportId());
        Mockito.verify(reportDao).updateReport(expectedReport);
    }
}
