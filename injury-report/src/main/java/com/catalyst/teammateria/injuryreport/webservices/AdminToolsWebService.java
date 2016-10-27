package com.catalyst.teammateria.injuryreport.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.AdminToolsService;

/**
 * Admin tools webservice for use with the Admin tools page
 * 
 * @author dGrimes
 */
@RestController
public class AdminToolsWebService {

    private static final String ADMINTOOLS_EMPLOYEE = "/admintools/employee";

    private static final String ADMINTOOLS_INJURY = "/admintools/injury";

    private static final String ADMINTOOLS_BODY = "/admintools/body";

    private static final String ADMINTOOLS_POSITION = "/admintools/position";

    private static final String ADMINTOOLS_WEATHER = "/admintools/weather";

    private AdminToolsService adminToolsService;

    @Autowired
    public void setAdminToolsService(AdminToolsService adminToolsService) {
        this.adminToolsService = adminToolsService;
    }

    /**
     * GET request for all weather objects
     * 
     * @return all weather objects
     */
    @RequestMapping (value = ADMINTOOLS_WEATHER, method = RequestMethod.GET)
    public List<Weather> getAllWeather() {
        return adminToolsService.getAllWeather();
    }

    /**
     * GET request for all position objects
     * 
     * @return all position objects
     */
    @RequestMapping (value = ADMINTOOLS_POSITION, method = RequestMethod.GET)
    public List<Position> getAllPositions() {
        return adminToolsService.getAllPositions();
    }

    /**
     * GET request for all body part objects
     * 
     * @return all body part objects
     */
    @RequestMapping (value = ADMINTOOLS_BODY, method = RequestMethod.GET)
    public List<BodyPart> getAllBodyParts() {
        return adminToolsService.getAllBodyParts();
    }

    /**
     * GET request for all injury type objects
     * 
     * @return all injury type objects
     */
    @RequestMapping (value = ADMINTOOLS_INJURY, method = RequestMethod.GET)
    public List<InjuryType> getAllInjuryTypes() {
        return adminToolsService.getAllInjuryTypes();
    }

    /**
     * GET request for all employee objects
     * 
     * @return all employee objects
     */
    @RequestMapping (value = ADMINTOOLS_EMPLOYEE, method = RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        return adminToolsService.getAllEmployees();
    }

    /**
     * GET request for a single employee based on their employeeId
     * 
     * @param id
     *            - employee id
     * @return the employee corresponding to the id
     */
    @RequestMapping (value = "/admintools/employee/{id}", method = RequestMethod.GET)
    public Employee getEmployeeById(@PathVariable int id) {
        return adminToolsService.getEmployeeById(id);
    }

    /**
     * GET request for a single user based on their employeeId (NOT USER ID!)
     * 
     * @param id
     *            - employeeId
     * @return the user corresponding to the id
     */
    @RequestMapping (value = "/admintools/user/{id}", method = RequestMethod.GET)
    public User getUserByEmployeeId(@PathVariable int id) {
        return adminToolsService.getUserByEmployeeId(id);
    }

    /**
     * GET request to check the availability of a user name (which includes on
     * checking against ones own username to avoid redundancy)
     * 
     * @param id
     *            - employeeId (NOT USER ID)
     * @param username
     *            - username to be checked
     * @return true if available
     */
    @RequestMapping (value = "/admintools/username/{id}", method = RequestMethod.GET)
    public boolean getUserNameAvailability(@PathVariable int id, String username) {
        return adminToolsService.userNameAvailable(id, username);
    }

    /**
     * POST request to set a new type of weather
     * 
     * @param name
     *            - name of weather
     */
    @RequestMapping (value = ADMINTOOLS_WEATHER, method = RequestMethod.POST)
    public void postAllWeather(String name) {
        adminToolsService.postWeather(name);
    }

    /**
     * POST request to create a new position
     * 
     * @param name
     *            - name of position
     */
    @RequestMapping (value = ADMINTOOLS_POSITION, method = RequestMethod.POST)
    public void postAllPositions(String name) {
        adminToolsService.postPosition(name);
    }

    /**
     * POST request to set a new body part
     * 
     * @param name
     *            - name of body part
     */
    @RequestMapping (value = ADMINTOOLS_BODY, method = RequestMethod.POST)
    public void postAllBodyParts(String name) {
        adminToolsService.postBodyPart(name);
    }

    /**
     * POST request to create a new injury type
     * 
     * @param name
     *            - name of injury type
     */
    @RequestMapping (value = ADMINTOOLS_INJURY, method = RequestMethod.POST)
    public void postAllInjuryTypes(String name) {
        adminToolsService.postInjuryType(name);
    }

    /**
     * POST request to create a new employee
     * 
     * @param firstName
     *            - the employee's first name
     * @param lastName
     *            - the employee's last name
     * @param positionId
     *            - the employee's position.positionId
     */
    @RequestMapping (value = ADMINTOOLS_EMPLOYEE, method = RequestMethod.POST)
    public void postEmployee(String firstName, String lastName, int positionId) {
        adminToolsService.postEmployee(firstName, lastName, positionId);
    }

    /**
     * POST request to either create or update a user based upon their
     * employeeId (if there is already a user associated with that person it
     * gets updated, otherwise it is created -- like the old Hibernate method
     * merge)
     * 
     * @param employeeId
     *            - the employee for whom to create/update a user profile
     * @param username
     *            - the user's username
     * @param password
     *            - the user's password
     * @param roleId
     *            - the user's role.roleId
     */
    @RequestMapping (value = "/admintools/user/{employeeId}", method = RequestMethod.POST)
    public void postUser(@PathVariable int employeeId, String username,
            String password, int roleId) {
        adminToolsService.postUser(employeeId, username, password, roleId);
    }

    /**
     * PUT request to update an employee
     * 
     * @param employeeId
     *            - the employeeId to update
     * @param firstName
     *            - the employee's first name
     * @param lastName
     *            - the employee's last name
     * @param positionId
     *            - the employee's position.positionId
     */
    @RequestMapping (value = "/admintools/employee/{employeeId}", method = RequestMethod.PUT)
    public void putEmployee(@PathVariable int employeeId, String firstName,
            String lastName, int positionId) {
        adminToolsService.putEmployee(employeeId, firstName, lastName,
                positionId);
    }

    /**
     * DELETE request for a weather type
     * 
     * @param id
     *            - the id of the weather to delete
     */
    @RequestMapping (value = "/admintools/weather/{id}", method = RequestMethod.DELETE)
    public void deleteWeather(@PathVariable int id) {
        adminToolsService.deleteWeather(id);
    }

    /**
     * DELETE request for a position type
     * 
     * @param id
     *            - the id of the position to delete
     */
    @RequestMapping (value = "/admintools/position/{id}", method = RequestMethod.DELETE)
    public void deletePosition(@PathVariable int id) {
        adminToolsService.deletePosition(id);
    }

    /**
     * DELETE request for a body part type
     * 
     * @param id
     *            - the id of the body part to delete
     */
    @RequestMapping (value = "/admintools/body/{id}", method = RequestMethod.DELETE)
    public void deleteBodyPart(@PathVariable int id) {
        adminToolsService.deleteBodyPart(id);
    }

    /**
     * DELETE request for a injury type
     * 
     * @param id
     *            - the id of the injury type to delete
     */
    @RequestMapping (value = "/admintools/injury/{id}", method = RequestMethod.DELETE)
    public void deleteInjuryType(@PathVariable int id) {
        adminToolsService.deleteInjuryType(id);
    }

    /**
     * Filterable employee get
     * 
     * @param filtFirstName
     * @param filtLastName
     * @param filtEmployeeId
     * @param filtPositionId
     * @return list of employees filtered by the above parameters
     */
    @RequestMapping (value = "/admintools/employee/filter", method = RequestMethod.GET)
    public List<Employee> getFilteredEmployees(String filtFirstName,
            String filtLastName, Integer filtEmployeeId, Integer filtPositionId) {
        return adminToolsService.getFilteredEmployeeList(filtFirstName,
                filtLastName, filtEmployeeId, filtPositionId);
    }
}
