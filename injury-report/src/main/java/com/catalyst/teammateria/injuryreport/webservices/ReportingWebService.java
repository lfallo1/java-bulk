package com.catalyst.teammateria.injuryreport.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.service.ReportingService;

/**
 * Reporting WebService
 * @author lfallon
 *
 */
@RestController
public class ReportingWebService {

	private ReportingService reportingService;
	
	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/**
	 * Return all reports
	 * @return
	 */
	@RequestMapping(value="/reporting/allreports", method=RequestMethod.GET)
	public List<Report> allInjuryReportsGET(){
		return reportingService.getAllInjuryReports();
	}
	
	/**
	 * Return all employees
	 * @return
	 */
	@RequestMapping(value="/reporting/allemployees", method=RequestMethod.GET)
	public List<Employee> allEmployeesGET(){
		return reportingService.getAllEmployees();
	}	
}
