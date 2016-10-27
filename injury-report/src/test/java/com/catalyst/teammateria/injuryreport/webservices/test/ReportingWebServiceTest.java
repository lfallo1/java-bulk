package com.catalyst.teammateria.injuryreport.webservices.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.service.ReportingService;
import com.catalyst.teammateria.injuryreport.webservices.ReportingWebService;

@RunWith(MockitoJUnitRunner.class)
public class ReportingWebServiceTest {
	@InjectMocks
	private ReportingWebService target;
	
	@Mock
	private ReportingService reportingService;
	
	@Test
	public void allEmployeesGetTest(){
		List<Employee> employeeListExpected = createEmployeeList();
		Mockito.when(reportingService.getAllEmployees()).thenReturn(employeeListExpected);
		List<Employee> employeeListActual = target.allEmployeesGET();
		Assert.assertEquals(employeeListExpected, employeeListActual);
	}
	
	@Test
	public void allReportsGetTest(){
		List<Report> reportListExpected = createReportList();
		Mockito.when(reportingService.getAllInjuryReports()).thenReturn(reportListExpected);
		List<Report> reportListActual = target.allInjuryReportsGET();
		Assert.assertEquals(reportListExpected, reportListActual);
	}	

	@Ignore
	private List<Employee> createEmployeeList() {
		Employee emp1 = new Employee();
		Employee emp2 = new Employee();
		return new ArrayList<Employee>(Arrays.asList(emp1, emp2));
	}
	
	@Ignore
	private List<Report> createReportList() {
		Report rpt1 = new Report();
		Report rpt2 = new Report();
		return new ArrayList<Report>(Arrays.asList(rpt1, rpt2));
	}
}
