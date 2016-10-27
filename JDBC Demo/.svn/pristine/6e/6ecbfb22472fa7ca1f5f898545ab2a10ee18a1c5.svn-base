package com.catalyst.cycle.jdbc_demo.dao;

import java.util.List;

import com.catalyst.cycle.jdbc_demo.model.User;
import com.catalyst.cycle.jdbc_demo.utils.CyclicStateException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidIdentifierException;
 
 
/**
 * DAO operations for a User entity.
 * @author egover
 *
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
	 * @throws InvalidIdentifierException if the user was not found using the supplied key.
	 */ 
	User getUserById(Integer key) throws InvalidIdentifierException;

	/**
	 * Save changes made to a user object
	 * 
	 * @param User
	 *            object
	 * @throws CyclicStateException If we attempt to make a user their own manager.
	 * 
	 */
	void updateUser(User newObject) throws CyclicStateException;

	/**
	 * Delete a user from database
	 * 
	 * @param User
	 *            object
	 * 
	 */
	void removeUser(User newObject);

	/**
	 * Return a list of all records
	 */
	List<User> getAll();

	/**
	 * Gets all users that are managed by a user with the given UserID
	 * @param managerID
	 * @return
	 */
    List<User> getStaffOfUser(Integer managerID);
}
