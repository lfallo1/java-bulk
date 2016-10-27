package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.EmployeeRowMapper;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.EmployeeRowMapperWithPositionName;
import com.catalyst.teammateria.injuryreport.model.Employee;

/**
 * JDBC implementation of the Dao for employees
 * 
 * @author dGrimes
 */
@Component
public class EmployeeDaoJdbc implements EmployeeDao {

    private static final String INSERT_EMPLOYEE = "INSERT INTO employee (`first_name`,`last_name`,`position_id`) VALUES (?,?,?)";

    private static final String GET_EMPLOYEE_BY_ID = "SELECT e.employee_id, e.first_name, e.last_name, e.position_id FROM employee AS e WHERE e.employee_id = ?";

    private static final String UPDATE_EMPLOYEE = "UPDATE employee AS e SET e.first_name = ?, e.last_name = ?, e.position_id = ? WHERE e.employee_id = ?";

    private static final String REMOVE_EMPLOYEE = "DELETE FROM employee WHERE employee_id = ?";

    private static final String GET_ALL_EMPLOYEES = "SELECT e.employee_id, e.first_name, e.last_name, e.position_id FROM employee AS e";

    private static final String GET_EMPLOYEES_FOR_INJURED_EMPLOYEE = "SELECT e.employee_id, e.first_name, e.last_name, p.position_name FROM employee AS e JOIN position AS p ON e.position_id = p.position_id WHERE e.first_name LIKE ? AND e.last_name LIKE ? AND e.employee_id LIKE ? AND e.employee_id != ?";

    private static final String GET_EMPLOYEES_WITH_FILTERS_FOR_ADMIN_TOOLS = "SELECT e.employee_id, e.first_name, e.last_name, e.position_id FROM employee AS e WHERE e.first_name LIKE ? AND e.last_name LIKE ? AND e.employee_id LIKE ? AND e.position_id LIKE ?";

    private JdbcTemplate jdbcTemplate;

    /**
     * Sets the JdbcTemplate using the provided dataSource.
     * 
     * @param dataSource
     */
    @Autowired
    public void setJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * For adding a JdbcTemplate object directly.
     * 
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addEmployee(Employee newObject) {
        this.jdbcTemplate.update(INSERT_EMPLOYEE, newObject.getFirstName(),
                newObject.getLastName(), newObject.getPosition()
                        .getPositionId());
    }

    @Override
    public Employee getEmployeeById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_EMPLOYEE_BY_ID,
                new Object[] {key}, new EmployeeRowMapper());
    }

    @Override
    public void updateEmployee(Employee newObject) {
        this.jdbcTemplate.update(UPDATE_EMPLOYEE, newObject.getFirstName(),
                newObject.getLastName(), newObject.getPosition()
                        .getPositionId(), newObject.getEmployeeId());
    }

    @Override
    public void removeEmployee(Employee newObject) {
        this.jdbcTemplate.update(REMOVE_EMPLOYEE, newObject.getEmployeeId());
    }

    @Override
    public List<Employee> getAll() {
        return this.jdbcTemplate.query(GET_ALL_EMPLOYEES,
                new EmployeeRowMapper());
    }

    @Override
    public List<Employee> getEmployeesForInjuredEmployee(String firstName,
            String lastName, String id, String employeeId) {
        return this.jdbcTemplate.query(GET_EMPLOYEES_FOR_INJURED_EMPLOYEE,
                new Object[] {firstName, lastName, id, employeeId},
                new EmployeeRowMapperWithPositionName());
    }

    @Override
    public List<Employee> getAdminToolsEmployeeListWithFilters(
            String firstName, String lastName, String employeeId,
            String positionId) {
        return this.jdbcTemplate.query(
                GET_EMPLOYEES_WITH_FILTERS_FOR_ADMIN_TOOLS, new Object[] {
                        firstName, lastName, employeeId, positionId},
                new EmployeeRowMapper());
    }
}
