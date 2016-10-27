package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.User;

/**
 * Interface for the UserDao
 * 
 * @author dGrimes
 */
public interface UserDao {

    /**
     * create new object in database
     * 
     * @param new User object
     */
    void addUser(User newObject);

    /**
     * Read a User object from database accessed by ID
     * 
     * @param id
     * @return User
     */
    User getUserById(Integer key);

    /**
     * Read a User object from database accessed by username
     * 
     * @param username
     * @return User
     */
    User getUserByUsername(String username);

    /**
     * Save changes made to a user object
     * 
     * @param User
     *            object
     */
    void updateUser(User newObject);

    /**
     * Delete a user from database
     * 
     * @param User
     *            object
     */
    void removeUser(User newObject);

    /**
     * Return a list of all users
     * 
     * @return List of users
     */
    List<User> getAll();

    /**
     * Special User return that is determined by the employee ID
     * 
     * @return user based on the employee id
     */
    User getUserByEmployeeId(int id);

    /**
     * Check to the database that returns true if there is a user with this
     * employee id
     * 
     * @param id
     *            - employee id
     * @return true if already exists
     */
    boolean isAlreadyAUser(int id);

    /**
     * Determines whether this username corresponds with a user already
     * 
     * @param username
     *            - the username to check for
     * @return true if username is taken
     */
    boolean userNameTaken(String username);

    /**
     * Returns an integer representation of the employeeId
     * 
     * @param userName
     *            - username to use
     * @return employeeId
     */
    Integer getEmployeeIdByUsername(String userName);
}
