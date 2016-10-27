package com.catalyst.teammateria.timeclock.dao;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserInvalidLoginAttempts;

/**
 * The UserInvalidLoginAttemptsDao will implement the capacity to create read,
 * update and delete single rows based on current number of unsuccessful
 * attempts to login to the "joined" users
 *
 * @author dGrimes
 */
public interface UserInvalidLoginAttemptsDao {

    /**
     * This method looks to the database and returns the number of current
     * attempts to login to this user
     * 
     * @param user
     *            - the User object
     * @return int - the number of times unsuccessful logins
     */
    UserInvalidLoginAttempts getUserInvalidLoginAttemptsByUser(User user);

    /**
     * This method begins the life cycle of the invalid login attempt object
     * 
     * @param obj
     *            - the UserInvalidLoginAttempts object to add to the database
     */
    void addUserInvalidLoginAttempts(UserInvalidLoginAttempts obj);

    /**
     * This method merges the old UserInvalidLoginAttempts object with this
     * current iteration
     * 
     * @param obj
     *            - the UserInvalidLoginAttempts object to merge
     */
    void updateUserInvalidLoginAttempts(UserInvalidLoginAttempts obj);

    /**
     * This method removes the UserInvalidLoginAttempts object from the database
     * 
     * @param obj
     *            - the UserInvalidLoginAttempts object to delete
     */
    void deleteUserInvalidLoginAttempts(UserInvalidLoginAttempts obj);
}
