package com.catalyst.teammateria.timeclock.services;

import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;

/**
 * This class should be implemented by the register web service for validation,
 * addition of users, confirmation of errors
 * 
 * @author dGrimes
 */
public interface RegisterService {

    /**
     * This will attempt to add the user and return the errors or "true"
     * 
     * @param form
     *            - form pulled in from the registration page
     * @return String of errors or a simple "true"
     */
    String add(RegistrationForm form);

    /**
     * This will run all the pertinent fields of the form through validation
     * 
     * @param form
     *            - Registration form
     * @return a new line separated string of errors
     */
    StringBuilder validate(RegistrationForm form);

    /**
     * This will check username against the database to see if it is available
     * 
     * @param username
     * @return boolean of success
     */
    boolean checkUsername(String username);

    /**
     * This will check email against the database to see if it is available
     * 
     * @param email
     * @return boolean of success
     */
    boolean checkEmail(String email);
}
