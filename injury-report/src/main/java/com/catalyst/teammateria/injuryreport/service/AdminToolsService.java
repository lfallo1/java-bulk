package com.catalyst.teammateria.injuryreport.service;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

/**
 * Interface for the admin tools service
 * 
 * @author dGrimes
 */
public interface AdminToolsService {

    /**
     * Simple return of all database records for weather conditions
     * 
     * @return a list of all weather conditions
     */
    List<Weather> getAllWeather();

    /**
     * Simple return of all database records for positions
     * 
     * @return a list of all Positions
     */
    List<Position> getAllPositions();

    /**
     * Simple return of all database records for body parts
     * 
     * @return a list of all Body parts
     */
    List<BodyPart> getAllBodyParts();

    /**
     * Simple return of all database records for injury types
     * 
     * @return a list of all injury types
     */
    List<InjuryType> getAllInjuryTypes();

    /**
     * Simple return of all database records for employees
     * 
     * @return a list of all employees
     */
    List<Employee> getAllEmployees();

    /**
     * Simple return of an employee based upon his/her id
     * 
     * @param id
     *            - employee id
     * @return employee corresponding to the employee id
     */
    Employee getEmployeeById(int id);

    /**
     * Simple return of a user based upon his/her employee id
     * 
     * @param id
     *            - employee id
     * @return user pertaining to the employee ids
     */
    User getUserByEmployeeId(int id);

    /**
     * Creation of a new weather type
     * 
     * @param name
     *            - what to name the weather
     */
    void postWeather(String name);

    /**
     * Creation of a new Position
     * 
     * @param name
     *            - what to name the position
     */
    void postPosition(String name);

    /**
     * Creation of a new body part
     * 
     * @param name
     *            - what to name the body part
     */
    void postBodyPart(String name);

    /**
     * Creation of a new injury type
     * 
     * @param name
     *            - what to name the injury type
     */
    void postInjuryType(String name);

    /**
     * Creation of a new Employee
     * 
     * @param firstName
     *            - employee first name
     * @param lastName
     *            - employee last name
     * @param positionId
     *            - employee position
     */
    void postEmployee(String firstName, String lastName, int positionId);

    /**
     * Used to either create or update user based on whether there is an
     * existing user record for the given employee
     * 
     * @param employeeId
     *            - employee id number
     * @param username
     *            - username
     * @param password
     *            - plain-text password (DON'T FORGET TO ENCODE BEFORE SEND TO
     *            DAO)
     * @param roleId
     *            - roleId
     */
    void postUser(int employeeId, String username, String password, int roleId);

    /**
     * Used to update the employee at the employee Id number
     * 
     * @param employeeId
     *            - employee to update
     * @param firstName
     *            - new first name
     * @param lastName
     *            - new last name
     * @param positionId
     *            - new positionId
     */
    void putEmployee(int employeeId, String firstName, String lastName,
            int positionId);

    /**
     * Delete the weather signified by the id
     * 
     * @param id
     *            - the weather to delete
     */
    void deleteWeather(int id);

    /**
     * Delete the position signified by the id
     * 
     * @param id
     *            - the position to delete
     */
    void deletePosition(int id);

    /**
     * Delete the body part signified by the id
     * 
     * @param id
     *            - the body part to delete
     */
    void deleteBodyPart(int id);

    /**
     * Delete the injury type signified by the id
     * 
     * @param id
     *            - the injury type to delete
     */
    void deleteInjuryType(int id);

    /**
     * Turn the string into an SQL safe variation
     */
    String makeSafe(String str);

    /**
     * A check to see if the username is available
     * 
     * @param id
     *            - employee id
     * @param username
     *            - username
     * @return true if username is available
     */
    boolean userNameAvailable(int id, String username);

    /**
     * This method will return a list of employees filtered by the lists
     * 
     * @param filtFirstName
     * @param filtLastName
     * @param filtEmployeeId
     * @param filtPositionId
     * @return a filtered list of employees.
     */
    List<Employee> getFilteredEmployeeList(String filtFirstName,
            String filtLastName, Integer filtEmployeeId, Integer filtPositionId);

    /**
     * Turn the string number into a searchable SQL safe string
     * 
     * @param number
     *            - String number to make safe
     * @return safe string '%' + "[0-9]+" + '%' or '%'
     */
    String makeNumberSafe(String number);

    /**
     * Turn the string name into a searchable SQL safe string
     * 
     * @param name
     *            - String name to make safe
     * @return safe string '%' + "[A-Za-z]+" + '%' or '%'
     */
    String makeNameSafe(String name);
}
