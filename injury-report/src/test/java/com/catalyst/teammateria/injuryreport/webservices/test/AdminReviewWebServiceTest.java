package com.catalyst.teammateria.injuryreport.webservices.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.service.AdminReviewService;
import com.catalyst.teammateria.injuryreport.webservices.AdminReviewWebService;

@RunWith(MockitoJUnitRunner.class)
public class AdminReviewWebServiceTest {

	@InjectMocks
	private AdminReviewWebService target;

	@Mock
	private AdminReviewService adminReviewService;

	@Mock
	private Report report;

	@Test
	public void getReportsTest() {
		List<Report> expected = new ArrayList<Report>();
		expected.add(new Report());
		when(
				adminReviewService.getReports(anyString(), anyString(),
						anyString(), anyString(), anyBoolean())).thenReturn(
				expected);
		List<Report> actual = target.getReports(anyString(), anyString(),
				anyString(), anyString(), anyBoolean());
		assertEquals(expected, actual);
	}

	@Test
	public void getReportForProofingTest() {
		Report expectedReport = new Report();
		when(adminReviewService.getFullReport(anyInt())).thenReturn(
				expectedReport);
		Report actualReport = target.getReportForProofing(anyInt());
		assertEquals(expectedReport, actualReport);
	}

	@Test
	public void enableOrDisableTest() {
		target.enableOrDisable(anyInt());
		verify(adminReviewService, times(1)).enableDisable(anyInt());
	}

	@Test
	public void putReportTest() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		Principal principal = mock(Principal.class);
		when(request.getUserPrincipal()).thenReturn(principal);
		AdminProofingBean proofingBean = adminProofingBean();
		target.putReport(3, proofingBean, request);
		verify(adminReviewService, times(1)).updateReport(3, proofingBean,
				request.getUserPrincipal().getName());
	}

	@Ignore
	public AdminProofingBean adminProofingBean() {
		AdminProofingBean proofingBean = new AdminProofingBean();
		proofingBean.setTypeId(3);
		proofingBean.setBodyPartId(1);
		proofingBean.setWeatherId(2);
		proofingBean.setTimeOfInjury("10:00");
		proofingBean.setApproverComments("Test");
		proofingBean.setDescription("Test Desc");
		proofingBean.setDateOfReport("2014-11-26");
		proofingBean.setDateReportedToManagement("2014-11-26");
		return proofingBean;
	}
}
