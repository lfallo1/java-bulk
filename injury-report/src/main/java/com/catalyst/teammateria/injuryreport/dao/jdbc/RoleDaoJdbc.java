package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.RoleDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.RoleRowMapper;
import com.catalyst.teammateria.injuryreport.model.Role;

/**
 * Role Dao Implementation for JDBC
 * 
 * @author dGrimes
 */
@Component
public class RoleDaoJdbc implements RoleDao {

    private static final String INSERT_ROLE = "INSERT INTO role (`role_name`) VALUES (?)";

    private static final String GET_ROLE_BY_ID = "SELECT p.role_id, p.role_name FROM role AS p WHERE p.role_id = ?";

    private static final String UPDATE_ROLE = "UPDATE role AS p SET p.role_name = ? WHERE p.role_id = ?";

    private static final String REMOVE_ROLE = "DELETE FROM role WHERE role_id = ?";

    private static final String GET_ALL_ROLES = "SELECT p.role_id, p.role_name FROM role AS p";

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
    public void addRole(Role newObject) {
        this.jdbcTemplate.update(INSERT_ROLE, newObject.getRoleName());
    }

    @Override
    public Role getRoleById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_ROLE_BY_ID,
                new Object[] {key}, new RoleRowMapper());
    }

    @Override
    public void updateRole(Role newObject) {
        this.jdbcTemplate.update(UPDATE_ROLE, newObject.getRoleName(),
                newObject.getRoleId());
    }

    @Override
    public void removeRole(Role newObject) {
        this.jdbcTemplate.update(REMOVE_ROLE, newObject.getRoleId());
    }

    @Override
    public List<Role> getAll() {
        return this.jdbcTemplate
                .query(GET_ALL_ROLES, new RoleRowMapper());
    }
}
