/**
 * 
 */
package com.catalyst.cycle.jdbc_demo.dao.jdbc;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.catalyst.cycle.jdbc_demo.dao.UserDao;
import com.catalyst.cycle.jdbc_demo.dao.jdbc.mappers.UserResultSetExtractor;
import com.catalyst.cycle.jdbc_demo.dao.jdbc.mappers.UserRowMapper;
import com.catalyst.cycle.jdbc_demo.model.User;
import com.catalyst.cycle.jdbc_demo.utils.CyclicStateException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidIdentifierException;

/**
 * @author egover
 *
 */
@Component
public class UserJdbcDao implements UserDao {

    private static final String SELECT_USERS_BY_MANAGER = "SELECT u.userID, u.firstname, u.lastname, u.manager, manager.firstname, manager.lastname  FROM User AS u LEFT JOIN User AS manager ON manager.userID = u.manager WHERE u.manager =?";
    private static final String SELECT_ALL_USERS = "SELECT u.userID, u.firstname, u.lastname, u.manager, manager.firstname, manager.lastname  FROM User AS u LEFT JOIN User AS manager ON manager.userID = u.manager";
    private static final String SELECT_USER_BY_USERID = "SELECT u.userID, u.firstname, u.lastname, u.manager, manager.firstname, manager.lastname  FROM User AS u LEFT JOIN User AS manager ON manager.userID = u.manager WHERE u.userID =?";
    private static final String INSERT_USER = "INSERT INTO USER (`firstName`, `lastName`, `manager`) VALUES (?,?,?)";
    private static final String UPDATE_USER = "UPDATE User as u SET u.firstname = ?, u.lastname = ?, u.manager = ? WHERE u.userID = ? ";
    private static final String DELETE_USER = "DELETE FROM User as u WHERE u.userID =?";

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
     * @param jdbcTemplate
     */
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.catalyst.cycle.jdbc_demo.dao.UserDao#addUser(com.catalyst.cycle.jdbc_demo
     * .model.User)
     */
    @Override
    public void addUser(User user) {

        //Get the manager ID for the SQL statement
        Integer managerID = null;
        if (user.getManager() != null) {
            managerID = user.getManager().getUserID();
        }

        this.jdbcTemplate.update(INSERT_USER, user.getFirstName(),
                user.getLastName(), managerID);

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.catalyst.cycle.jdbc_demo.dao.UserDao#getUserById(java.lang.Integer)
     */
    @Override
    public User getUserById(Integer key) throws InvalidIdentifierException { 
        try {
            //We use the RowMapper because we only want a single result.
            return this.jdbcTemplate.queryForObject(SELECT_USER_BY_USERID,
                    new Object[] { key }, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            //Custom exception thrown when no record could be returned for that object.
            throw new InvalidIdentifierException("User with a userID of " + key
                    + " not found.", e, key);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.catalyst.cycle.jdbc_demo.dao.UserDao#updateUser(com.catalyst.cycle
     * .jdbc_demo.model.User)
     */
    @Override
    public void updateUser(User user) throws CyclicStateException {

        //We need this manager ID for the SQL query
        Integer managerID = null;
        if (user.getManager() != null) {
            managerID = user.getManager().getUserID();
        }
        
        //We do not want the user to be their own manager. If the update would cause that state, 
        //then throw an exception
        if (user.equals(user.getManager())) {
            //We pass in a message and the user object responsible for the cyclic state.
            throw new CyclicStateException("User cannot be their own manager.",
                    user);
        }

        this.jdbcTemplate.update(UPDATE_USER, user.getFirstName(),
                user.getLastName(), managerID, user.getUserID());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.catalyst.cycle.jdbc_demo.dao.UserDao#removeUser(com.catalyst.cycle
     * .jdbc_demo.model.User)
     */
    @Override
    public void removeUser(User user) {

        this.jdbcTemplate.update(DELETE_USER, user.getUserID());

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.catalyst.cycle.jdbc_demo.dao.UserDao#getAll()
     */
    @Override
    public List<User> getAll() {
        //We use the ResultSetExtractor because we are retrieving multiple
        //records (Users) where each one has a dependency (the manager) that could 
        //be shared by multiple Users. Also, the dependency (the manager) is
        //also a user. If that user's information changes, the other users 
        //that have it as a manager should be able to see those changes.
        return this.jdbcTemplate.query(SELECT_ALL_USERS, new UserResultSetExtractor());

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.catalyst.cycle.jdbc_demo.dao.UserDao#getStaffOfUser(java.lang.Integer
     * )
     */
    @Override
    public List<User> getStaffOfUser(Integer managerID) {

        return this.jdbcTemplate.query(SELECT_USERS_BY_MANAGER,
                new Object[] { managerID }, new UserResultSetExtractor());

    }

}
