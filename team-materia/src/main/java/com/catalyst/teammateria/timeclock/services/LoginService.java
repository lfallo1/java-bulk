package com.catalyst.teammateria.timeclock.services;

import com.catalyst.teammateria.timeclock.formbeans.LoginForm;

/**
 * This class should be implemented by the login web service for validation of users,
 * and logging invalid login attempts
 * 
 * @author aDietrich
 *
 */
public interface LoginService {

    /**
     * Given a username and password, validate if the credentials are valid.
     * Additionally, using the incrementInvalidAttempts method, check if the
     * account is currently locked and track how many consecutive invalid
     * attempts occur, storing that information inside the LoginForm bean. <br/>
     * <br/>
     * The method <em>returns the LoginForm bean</em>, which stores whether or
     * not the previous login attempt was valid, the current username,
     * consecutive invalid login attempts, and whether or not the account is
     * locked
     * 
     * @param username
     * @param password
     * @return LoginForm
     */
    LoginForm validateUser(String username, String password);
}
