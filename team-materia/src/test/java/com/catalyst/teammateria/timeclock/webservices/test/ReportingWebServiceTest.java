package com.catalyst.teammateria.timeclock.webservices.test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;
import com.catalyst.teammateria.timeclock.services.ReportingService;
import com.catalyst.teammateria.timeclock.webservices.ReportingWebService;

@RunWith (MockitoJUnitRunner.class)
public class ReportingWebServiceTest {

    @InjectMocks
    ReportingWebService target = new ReportingWebService();
    @Mock
    ReportingService    reportingService;
    @Mock
    UserTimeDao         userTimeDao;
    @Mock
    User                user;
    @Mock
    UserTimeMillis      userTimeMillis;

    @Test
    public void setReportingServiceTest() {
        target.setReportingService(reportingService);
    }

    @Test
    public void setUserTimeDaoTest() {
        target.setUserTimeDao(userTimeDao);
    }

    @Test
    public void getUserListTest() {
        List<User> temp = target.getUserList();
        assertNotNull(temp);
    }

    @Test
    public void weeksGETTest() {
        List<LocalDate> temp = target.weeksGET();
        assertNotNull(temp);
    }

    @Test
    public void totalHoursRptGETTest() {
        List<UserTimeMillis> temp = target.totalHoursRptGET(anyLong(),
                anyString());
        assertNotNull(temp);
    }

    @Test
    public void overtimeRptGETTest() {
        List<UserTimeMillis> temp = target.overtimeRptGET(anyLong());
        assertNotNull(temp);
    }

    @Test
    public void absentRptGETTest() {
        List<UserTimeMillis> temp = target.absentRptGET(anyLong(), anyString());
        assertNotNull(temp);
    }
}
