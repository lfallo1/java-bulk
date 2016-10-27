package com.catalyst.teammateria.injuryreport.webservices;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.service.InjuredEmployeeService;

/**
 * Web service for the Injured Employee (employee search) page
 * 
 * @author dGrimes
 */
@RestController
public class InjuredEmployeeWebService {

    private InjuredEmployeeService injuredEmployeeService;

    @Autowired
    public void setInjuredEmployeeService(
            InjuredEmployeeService injuredEmployeeService) {
        this.injuredEmployeeService = injuredEmployeeService;
    }

    /**
     * GET request to get the list of employees with filter possibilities (if
     * empty) it will return a list of all employees
     * 
     * @param firstName
     *            - first name to filter by
     * @param lastName
     *            - last name to filter by
     * @param id
     *            - id number to filter by
     * @return a list of employees that fit the filters
     */
    @RequestMapping (value = "/injuredemployee/list", method = RequestMethod.GET)
    public List<Employee> getEmployeeList(String firstName, String lastName,
            Integer id, HttpServletRequest request) {
        return injuredEmployeeService.getEmployeesWithFilters(firstName,
                lastName, id, request.getUserPrincipal().getName());
    }
}
