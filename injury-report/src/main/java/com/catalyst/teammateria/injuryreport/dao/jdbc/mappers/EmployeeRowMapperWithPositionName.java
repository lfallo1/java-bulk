package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Position;

/**
 * Similar to the base level mapper but requires the position name instead of
 * the position id
 * 
 * @author dGrimes
 */
public class EmployeeRowMapperWithPositionName implements RowMapper<Employee> {

    private static final int EMPLOYEEID_FIELD = 1;

    private static final int EMPLOYEE_FIRSTNAME_FIELD = 2;

    private static final int EMPLOYEE_LASTNAME_FIELD = 3;

    private static final int EMPLOYEE_POSITION_NAME_FIELD = 4;

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(EMPLOYEEID_FIELD));
        employee.setFirstName(rs.getString(EMPLOYEE_FIRSTNAME_FIELD));
        employee.setLastName(rs.getString(EMPLOYEE_LASTNAME_FIELD));
        Position position = new Position();
        position.setPositionName(rs.getString(EMPLOYEE_POSITION_NAME_FIELD));
        employee.setPosition(position);
        return employee;
    }
}
