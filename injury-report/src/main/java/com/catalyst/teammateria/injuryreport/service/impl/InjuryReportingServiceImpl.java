package com.catalyst.teammateria.injuryreport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.injuryreport.dao.BodyPartDao;
import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.InjuryTypeDao;
import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.dao.WeatherDao;
import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.InjuryReportingService;
import com.catalyst.teammateria.injuryreport.utils.DateUtil;

/**
 * The implemenation for the injury reporting service
 * 
 * @author dGrimes
 */
@Service
public class InjuryReportingServiceImpl implements InjuryReportingService {

    private WeatherDao weatherDao;

    private InjuryTypeDao injuryTypeDao;

    private BodyPartDao bodyPartDao;

    private UserDao userDao;

    private EmployeeDao employeeDao;

    private ReportDao reportDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setWeatherDao(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }

    @Autowired
    public void setInjuryTypeDao(InjuryTypeDao injuryTypeDao) {
        this.injuryTypeDao = injuryTypeDao;
    }

    @Autowired
    public void setBodyPartDao(BodyPartDao bodyPartDao) {
        this.bodyPartDao = bodyPartDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setReportDao(ReportDao reportDao) {
        this.reportDao = reportDao;
    }

    @Override
    public List<Weather> getWeatherForSelect() {
        return weatherDao.getAll();
    }

    @Override
    public List<InjuryType> getInjuryTypeForSelect() {
        return injuryTypeDao.getAll();
    }

    @Override
    public List<BodyPart> getBodyPartForSelect() {
        return bodyPartDao.getAll();
    }

    /**
     * Given a username, return that user
     */
    @Override
    public User getUserDetails(String username) {
        User user = userDao.getUserByUsername(username);
        user.setEmployee(employeeDao.getEmployeeById(user.getEmployee()
                .getEmployeeId()));
        return user;
    }

    /**
     * Given an injuryReportBean, add report to the database
     */
    @Override
    public Integer addInjuryReport(InjuryReportBean injuryReportBean) {
        injuryReportBean.setReportDate(DateUtil
                .convertDateFormat(injuryReportBean.getReportDate()));
        injuryReportBean.setReportManagementDate(DateUtil
                .convertDateFormat(injuryReportBean.getReportManagementDate()));
        
        return reportDao.addReport(injuryReportBean);
    }
}
