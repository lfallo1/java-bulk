package com.catalyst.teammateria.injuryreport.dao;

import java.util.List;

import com.catalyst.teammateria.injuryreport.model.Role;

/**
 * Role Dao Interface
 * 
 * @author dGrimes
 */
public interface RoleDao {

    /**
     * create new object in database
     * 
     * @param new Role object
     */
    void addRole(Role newObject);

    /**
     * Read a Role object from database accessed by ID
     * 
     * @param key
     * @return Role
     */
    Role getRoleById(Integer key);

    /**
     * Save changes made to a Role object
     * 
     * @param Role
     *            object
     */
    void updateRole(Role newObject);

    /**
     * Delete an Role from database
     * 
     * @param Role
     *            object
     */
    void removeRole(Role newObject);

    /**
     * Return a list of all Roles
     */
    List<Role> getAll();
}
