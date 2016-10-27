package com.catalyst.teammateria.timeclock.services;

import java.util.List;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.formbeans.AccountConfigForm;

/**
 * A service built for the needs of an admin-role user while accessing the
 * account config page
 * 
 * @author dGrimes
 */
public interface AccountConfigService {

    /**
     * This method should return a list of either active users or all users
     * 
     * @param withInactive
     *            - whether or not to include active users
     * @return the list of requested users
     */
    List<User> getUserList(boolean withInactive);

    /**
     * return a user based on the id
     * 
     * @param id
     *            the id
     * @return the user
     */
    User getUserById(int id);

    /**
     * Method to update a user
     * 
     * @param accountConfigForm
     *            - from the page (includes user and their password)
     */
    String updateUser(AccountConfigForm accountConfigForm);

    /**
     * Method to validate the account config form object
     * 
     * @param accountConfigForm
     *            - the form
     * @return a string of errors (or an empty string if all fields are valid)
     */
    String validate(AccountConfigForm accountConfigForm);
    
    /**
     * Validates First Name
     * @param firstName
     */
    void validateFirstName(String firstName);

    /**
     * Validates Last Name
     * @param lastName
     */
    void validateLastName(String lastName);

    /**
     * Validates username
     * @param username
     */
    void validateUsername(String username);

    /**
     * Validate if username is already in use
     * @param userId
     * @param username
     */
    void validateUsernameAvailable(Integer userId, String username);

    /**
     * Validate Email
     * @param email
     */
    void validateEmail(String email);

    /**
     * Validate if email is already in use
     * @param userId
     * @param email
     */
    void validateEmailAvailable(Integer userId, String email);

    /**
     * Validates password
     * @param password
     */
    void validatePassword(String password);

    /**
     * AJAX call from the page to determine whether or not this username belongs
     * to this user and if not whether it belongs to anyone
     * 
     * @param userId
     *            - the user to check
     * @param username
     *            - the username to check
     * @return true if username is useable for this user
     */
    boolean checkUsername(int userId, String username);

    /**
     * AJAX call from the page to determine whether or not this email belongs to
     * this user and if not whether it belongs to anyone
     * 
     * @param userId
     *            - the user to check
     * @param email
     *            - the email to check
     * @return true if email is useable for this user
     */
    boolean checkEmail(int userId, String email);
}
