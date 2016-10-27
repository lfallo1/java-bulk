package com.catalyst.teammateria.timeclock.webservices;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserTimeDao;
import com.catalyst.teammateria.timeclock.formbeans.UserTimeMillis;
import com.catalyst.teammateria.timeclock.services.ReportingService;

/**
 * 
 * Web service for handling the report page's ajax requests
 * 
 * @author aDietrich
 *
 */
@RestController
public class ReportingWebService {

	private UserTimeDao userTimeDao;
	
	private ReportingService reportingService;

	@Autowired
	public void setUserTimeDao(UserTimeDao userTimeDao) {
		this.userTimeDao = userTimeDao;
	}
	
	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}
	
	/**
	 * Gets a list of users for the reporting page
	 * @return
	 */
    @RequestMapping (value = "/reporting/users", method = RequestMethod.GET)
    public List<User> getUserList() {
        return reportingService.getUserListOrderByUsername();
    }
    
	/**
	 * Gets a list of dates for the reporting page
	 * @return
	 */
	@RequestMapping(value = "/reporting/weeks", method = RequestMethod.GET)
	public List<LocalDate> weeksGET() {
		return userTimeDao.getActiveWeeks();
	}

	/**
	 * Gets a list containing information for the Total Hours Report
	 * @param date
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/reporting/totalhours", method = RequestMethod.GET)
	public List<UserTimeMillis> totalHoursRptGET(long date, String username) {
		return reportingService.getTotalHoursReport(new LocalDate(date), username);
	}
	
	/**
	 * Gets a list containing information for the Overtime Report
	 * @param date
	 * @return
	 */
	@RequestMapping(value = "/reporting/overtime", method = RequestMethod.GET)
	public List<UserTimeMillis> overtimeRptGET(long date) {
		return reportingService.getOvertimeReport(new LocalDate(date));
	}
	
	/**
	 * Gets a list containing information for the Absence Report
	 * @param date
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/reporting/absent", method = RequestMethod.GET)
	public List<UserTimeMillis> absentRptGET(long date, String username) {
		return reportingService.getAbsentReport(new LocalDate(date), username);
	}		
}
