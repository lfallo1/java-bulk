package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.Employee;

/**
 * Employee Dao Interface
 * 
 * @author dGrimes
 */
public interface EmployeeDao {

    /**
     * create new object in database
     * 
     * @param new Employee object
     */
    void addEmployee(Employee newObject);

    /**
     * Read a Employee object from database accessed by ID
     * 
     * @param key
     * @return Employee
     */
    Employee getEmployeeById(Integer key);

    /**
     * Save changes made to a Employee object
     * 
     * @param Employee
     *            object
     */
    void updateEmployee(Employee newObject);

    /**
     * Delete an Employee from database
     * 
     * @param Employee
     *            object
     */
    void removeEmployee(Employee newObject);

    /**
     * Return a list of all Employees
     */
    List<Employee> getAll();

    /**
     * Gather a list of all employees who fit the filtered parameters (the
     * service should have made the strings safe and there should be no problem
     * passing them directly into the query
     * 
     * @param firstName
     * @param lastName
     * @param id
     * @param employeeId
     *            (the one to avoid)
     * @return List&lt;Employee&gt;
     */
    List<Employee> getEmployeesForInjuredEmployee(String firstName,
            String lastName, String id, String employeeId);

    /**
     * Gather a list of all employees who fit the filtered parameters (the
     * service should have made the strings safe and there should be no problem
     * passing them directly into the query
     * 
     * @param firstName
     * @param lastName
     * @param employeeId
     * @param positionId
     * @return filtered List&lt;Employee&gt;
     */
    List<Employee> getAdminToolsEmployeeListWithFilters(String firstName,
            String lastName, String employeeId, String positionId);
}
