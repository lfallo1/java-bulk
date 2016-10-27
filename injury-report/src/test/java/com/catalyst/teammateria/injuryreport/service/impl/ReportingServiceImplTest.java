package com.catalyst.teammateria.injuryreport.service.impl;

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

import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceImplTest {
	@InjectMocks
	ReportingServiceImpl target;
	
	@Mock
	private ReportDao reportDao;
	
	@Mock
	private EmployeeDao employeeDao;
	
	@Test
	public void getAllEmployeesTest(){
		List<Employee> employeeListExpected = createEmployeeList();
		Mockito.when(employeeDao.getAll()).thenReturn(employeeListExpected);
		List<Employee> employeeListActual = target.getAllEmployees();
		Assert.assertEquals(employeeListExpected, employeeListActual);
	}
	
	@Test
	public void getAllInjuryReportsTest(){
		List<Report> reportListExpected = createReportList();
		Mockito.when(reportDao.getAllWithEmployee()).thenReturn(reportListExpected);
		List<Report> reportListActual = target.getAllInjuryReports();
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
