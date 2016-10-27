package com.catalyst.teammateria.injuryreport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.service.InjuredEmployeeService;

/**
 * Service implementation for the Injured Employee Service
 * 
 * @author dGrimes
 */
@Service
public class InjuredEmployeeServiceImpl implements InjuredEmployeeService {

    private EmployeeDao employeeDao;

    private UserDao userDao;

    @Autowired
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<Employee> getEmployeesWithFilters(String firstName,
            String lastName, Integer id, String userName) {
        return employeeDao.getEmployeesForInjuredEmployee(makeSafe(firstName),
                makeSafe(lastName), makeSafe(id), userDao
                        .getEmployeeIdByUsername(userName).toString());
    }

    @Override
    public String makeSafe(Integer i) {
        if (null == i) {
            return "%";
        }
        return i.toString();
    }

    @Override
    public String makeSafe(String str) {
        if (null == str) {
            return "%";
        }
        return '%' + str.replaceAll("\\W", "") + '%';
    }
}
