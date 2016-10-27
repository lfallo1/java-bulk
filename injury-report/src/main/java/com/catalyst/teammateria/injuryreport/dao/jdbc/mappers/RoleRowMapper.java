package com.catalyst.teammateria.injuryreport.dao.jdbc.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalyst.teammateria.injuryreport.model.Role;

/**
 * Maps the Role class to the rows of the role table in the database
 * @author gPorter
 *
 */
public class RoleRowMapper implements RowMapper<Role> {

    private static final int ROLE_ID_FIELD = 1;

    private static final int ROLE_NAME_FIELD = 2;

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getInt(ROLE_ID_FIELD));
        role.setRoleName(rs.getString(ROLE_NAME_FIELD));
        return role;
    }
}
