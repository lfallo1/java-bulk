package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Role;
import com.catalyst.teammateria.injuryreport.model.User;

/**
 * Single Row Mapping for User
 * 
 * @author dGrimes
 */
public class UserRowMapper implements RowMapper<User> {

    private static final int USER_ID_FIELD = 1;

    private static final int USER_NAME_FIELD = 2;

    private static final int ROLE_ID_FIELD = 3;

    private static final int EMPLOYEE_ID_FIELD = 4;

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(USER_ID_FIELD));
        user.setUserName(rs.getString(USER_NAME_FIELD));
        Role role = new Role();
        role.setRoleId(rs.getInt(ROLE_ID_FIELD));
        user.setRole(role);
        Employee employee = new Employee();
        employee.setEmployeeId(rs.getInt(EMPLOYEE_ID_FIELD));
        user.setEmployee(employee);
        return user;
    }
}
