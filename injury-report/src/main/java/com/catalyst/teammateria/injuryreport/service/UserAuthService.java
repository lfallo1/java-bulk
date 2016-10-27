package com.catalyst.teammateria.injuryreport.service;

/**
 * Interface for the user authorization service
 * 
 * @author dGrimes
 */
public interface UserAuthService {

    /**
     * returns whether the logged in user matches the employee id or not
     * 
     * @param loggedInUsername
     *            - username
     * @param id
     *            - employee id
     * @return true if matches
     */
    boolean userHasId(String loggedInUsername, Integer id);
}
