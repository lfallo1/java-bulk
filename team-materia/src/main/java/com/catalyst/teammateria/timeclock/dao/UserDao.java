package com.catalyst.teammateria.timeclock.dao;

import java.util.List;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;

/**
 * Defines basic database operations for User DAO Create (persist) new User
 * object in database Reads (Find) from database an instance of User object T
 * Updates an existing User object and merges it back to database
 *
 * @author aDietrich
 */
public interface UserDao {

    /**
     * Create new user in database
     * 
     * @param user
     */
    void addUser(User user);

    /**
     * Read a user from database accessed by username
     * 
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * Save changes made to a user
     * 
     * @param user
     * @return
     */
    void updateUser(User user);

    /**
     * Return a list of all records
     * 
     * @return
     */
    List<User> getAll();

    /**
     * Return whether or not the given username is available, or if it's already
     * in use
     * 
     * @param username
     * @return
     */
    boolean userNameAvailable(String username);

    /**
     * Return whether a given email is available, or if it's already in use
     * 
     * @param email
     * @return
     */
    boolean emailAvailable(String email);

    /**
     * Query database for a list of users by role
     * 
     * @param role
     *            UserRole to list
     * @return list of Users with specified UserRole
     */
    List<User> getUsersByRole(UserRole role);

    /**
     * Query the database for a list of user parts for constructing a Selective
     * list (firstName, lastName, Id)
     * 
     * @param withInactive
     *            - if true return all users - if false return active users
     * @return List&lt;User&gt; - (firstName, lastName, Id) only
     */
    List<User> getUsersForSelect(boolean withInactive);

    /**
     * Query the database for a user using their id as the identifying trait
     * 
     * @param id
     *            - userId
     * @return User pertaining to that id
     */
    User getUserById(int id);

    /**
     * A check to see if this username is attached to this userId
     * 
     * @param userId
     *            - the user
     * @param username
     *            - maybe their username?
     * @return true if they match
     */
    boolean usernameRemainedTheSame(Integer userId, String username);

    /**
     * A check to see if this email is attached to this userId
     * 
     * @param userId
     *            - the user
     * @param email
     *            - maybe their username?
     * @return true if they match
     */
    boolean emailRemainedTheSame(Integer userId, String email);

    /**
     * Get all users sorted by username
     * @return
     */
	List<User> getAllSortByUsername();
}