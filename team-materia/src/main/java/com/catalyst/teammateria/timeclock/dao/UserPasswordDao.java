package com.catalyst.teammateria.timeclock.dao;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;

/**
 * Defines basic database operations for UserPassword DAO Create (persist) new
 * UserPassword object in database Reads (Find) from database an instance of
 * UserPassword object T Updates an existing UserPassword object and merges it back to
 * database
 * 
 * @author aDietrich
 */
public interface UserPasswordDao {

    /**
     * Create new UserPassword in database
     * 
     * @param userPassword
     */
    void addUserPassword(UserPassword userPassword);

    /**
     * Save changes made to a UserPassword object
     * 
     * @param userPassword
     * @return
     */
    void updateUserPassword(UserPassword userPassword);

    /**
     * Returns true if userId and userHash match else false
     * 
     * @param userId
     * @param userHash
     * @return
     */
    boolean userPasswordMatch(Integer userId, String userHash);
}
