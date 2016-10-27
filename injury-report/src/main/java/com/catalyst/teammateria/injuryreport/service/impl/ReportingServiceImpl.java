package com.catalyst.teammateria.injuryreport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.service.ReportingService;

/**
 * Implementation of ReportingService
 * @author lfallon
 *
 */
@Service
public class ReportingServiceImpl implements ReportingService {

	private ReportDao reportDao;
	
	private EmployeeDao employeeDao;

	@Autowired
	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao){
		this.employeeDao = employeeDao;
	}

	/**
	 * Return all injury reports with full employee object
	 */
	@Override
	public List<Report> getAllInjuryReports() {
		return this.reportDao.getAllWithEmployee();
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeDao.getAll();
	}

}
