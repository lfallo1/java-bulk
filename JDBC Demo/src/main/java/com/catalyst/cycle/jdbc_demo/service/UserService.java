package com.catalyst.cycle.jdbc_demo.service;

import java.util.List;

import com.catalyst.cycle.jdbc_demo.model.User;
import com.catalyst.cycle.jdbc_demo.utils.CyclicStateException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidIdentifierException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidInputException;

public interface UserService {


    /**
     * Add's a new user to the database.
     * @param user
     * @throws InvalidInputException The object contained invalid information. A list of error codes can be accessed for more details.
     */
    void addUser(User user) throws InvalidInputException;
    
    /**
     * Update an existing user in the database. The userID field is used to determine which entry to update.
     * @param user
     * @throws CyclicStateException If we attempt to make a user their own manager.
     */
    void editUser(User user) throws CyclicStateException;
    
 
    
    /**
     * Gets the user with the given userID. 
     * @param userID
     * @return A single user with the userID given. 
     * @throws InvalidIdentifierException The user was not found.
     */
    User getUserByID(Integer userID) throws InvalidIdentifierException;
    
    /**
     * Returns a list of all users in the database
     * @return All users in the database
     */
    List<User> allUsers();
    
    /**
     * Gets all Users that are managed by the User with the given managerID.
     * @param managerID: The manager 
     * @return The Users managed by the given User
     */
    List<User> staff(Integer managerID);

    /**
     * Removes all users from the database
     * @param userList
     */
    void deleteAllUsers();
    
    
    
}
