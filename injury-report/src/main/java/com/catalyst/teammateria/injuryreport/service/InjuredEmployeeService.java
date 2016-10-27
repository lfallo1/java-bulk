package com.catalyst.teammateria.injuryreport.service;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.Employee;

/**
 * Interface for the Injured Employee Service
 * 
 * @author dGrimes
 */
public interface InjuredEmployeeService {

    /**
     * This method is used to get back a list of all users (potentially all of
     * them or none of them) using the passed in filters
     * 
     * @param firstName
     *            - String the filter to use for first name
     * @param lastName
     *            - String the filter to use for last name
     * @param id
     *            - Integer the filter to use for id
     * @param userName
     *            - the session held username to filter out
     * @return a list of employees that match the parameters
     */
    List<Employee> getEmployeesWithFilters(String firstName, String lastName,
            Integer id, String userName);

    /**
     * Turns an integer into a filter string (maintained Make Safe name to fit
     * with String version)
     * 
     * @param i
     *            int to stringify
     * @return filter safe string
     */
    String makeSafe(Integer i);

    /**
     * Turns an unchecked string into a filter string (with some replacements
     * and safety checks to avoid sql injection)
     * 
     * @param str
     *            - string to safetify
     * @return filter safe string
     */
    String makeSafe(String str);
}
