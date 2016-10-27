package com.catalyst.teammateria.timeclock.dao;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;

/**
 * Defines basic database operations for UserRole DAO. 
 * Reads (Find) from database an instance of UserRole object T
 * 
 * @author aDietrich
 *
 */
public interface UserRoleDao {
    
    /**
     * Read a UserRole from database accessed by ID
     * 
     * @param key
     * @return
     */
    UserRole getUserRoleById(Integer key);
}
