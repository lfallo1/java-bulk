package com.catalyst.teammateria.injuryreport.service;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;

/**
 * Reporting service interface
 * @author lfallon
 *
 */
public interface ReportingService {
	/**
	 * Return list of of all injury injury reports
	 * @return
	 */
	List<Report> getAllInjuryReports();
	
	/**
	 * Return list of all employees
	 * @return
	 */
	List<Employee> getAllEmployees();
}
