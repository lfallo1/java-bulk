package com.catalyst.teammateria.injuryreport.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.UserBoolean;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.UserRowMapper;
import com.catalyst.teammateria.injuryreport.model.User;

/**
 * User Dao implementation for JDBC
 * 
 * @author dGrimes
 */
@Component
public class UserDaoJdbc implements UserDao {

    private static final String INSERT_USER = "INSERT INTO user (`user_name`,`user_pass`,`role_id`,`employee_id`,`active`) VALUES (?,?,?,?,?)";

    private static final String GET_USER_BY_ID = "SELECT u.user_id, u.user_name, u.role_id, u.employee_id, u.active FROM user AS u WHERE u.user_id = ?";

    private static final String GET_USER_BY_USERNAME = "SELECT u.user_id, u.user_name, u.role_id, u.employee_id, u.active FROM user AS u WHERE u.user_name = ?";

    private static final String UPDATE_USER = "UPDATE user AS u SET u.user_name = ?, u.user_pass = ?, u.role_id = ?, u.employee_id = ?, u.active = ? WHERE u.user_id = ?";

    private static final String REMOVE_USER = "DELETE FROM user WHERE user_id = ?";

    private static final String GET_ALL_USERS = "SELECT u.user_id, u.user_name, u.role_id, u.employee_id, u.active FROM user AS u";

    private static final String GET_USER_BY_EMPLOYEE_ID = "SELECT u.user_id, u.user_name, u.role_id, u.employee_id, u.active FROM user AS u WHERE u.employee_id = ?";

    private static final String IS_ALREADY_A_USER = "SELECT count(u.user_id) FROM user AS u WHERE u.employee_id = ?";

    private static final String USER_NAME_TAKEN = "SELECT count(u.user_id) FROM user AS u WHERE u.user_name = ?";

    private static final String GET_EMPLOYEE_ID_BY_USERNAME = "SELECT u.employee_id FROM user AS u WHERE u.user_name = ?";

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
    public void addUser(User newObject) {
        this.jdbcTemplate.update(INSERT_USER, newObject.getUserName(),
                newObject.getUserPass(), newObject.getRole().getRoleId(),
                newObject.getEmployee().getEmployeeId(), newObject.isActive());
    }

    @Override
    public User getUserById(Integer key) {
        return this.jdbcTemplate.queryForObject(GET_USER_BY_ID,
                new Object[] {key}, new UserRowMapper());
    }

    @Override
    public User getUserByUsername(String username) {
        return this.jdbcTemplate.queryForObject(GET_USER_BY_USERNAME,
                new Object[] {username}, new UserRowMapper());
    }

    @Override
    public void updateUser(User newObject) {
        this.jdbcTemplate.update(UPDATE_USER, newObject.getUserName(),
                newObject.getUserPass(), newObject.getRole().getRoleId(),
                newObject.getEmployee().getEmployeeId(), newObject.isActive());
    }

    @Override
    public void removeUser(User newObject) {
        this.jdbcTemplate.update(REMOVE_USER, newObject.getUserId());
    }

    @Override
    public List<User> getAll() {
        return this.jdbcTemplate.query(GET_ALL_USERS, new UserRowMapper());
    }

    @Override
    public User getUserByEmployeeId(int id) {
        return this.jdbcTemplate.queryForObject(GET_USER_BY_EMPLOYEE_ID,
                new Object[] {id}, new UserRowMapper());
    }

    @Override
    public boolean isAlreadyAUser(int id) {
        if (this.jdbcTemplate.queryForObject(IS_ALREADY_A_USER,
                new Object[] {id}, new UserBoolean())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean userNameTaken(String username) {
        if (this.jdbcTemplate.queryForObject(USER_NAME_TAKEN,
                new Object[] {username}, new UserBoolean())) {
            return true;
        }
        return false;
    }

    @Override
    public Integer getEmployeeIdByUsername(String userName) {
        return this.jdbcTemplate.queryForObject(GET_EMPLOYEE_ID_BY_USERNAME,
                Integer.class, new Object[] {userName});
    }
}
