package com.catalyst.teammateria.timeclock.functional_tests.pages;

/**
 * Defines basic functionality of the Login page for lockout testing
 * 
 * @author nPoloway
 */
public interface LoginLockoutPage {

    /**
     * Sets the username for the login page
     * 
     * @param username
     */
    void setUsername(String username);

    /**
     * Empties the username textbox for the login page
     */
    void clearFields();

    /**
     * Sets the password from the login page
     * 
     * @param password
     */
    void setUserPassword(String password);

    /**
     * Submits username and password as login information to be validated
     */
    void submitLogin();

    /**
     * Returns true if on the login page, else false
     * 
     * @return
     */
    boolean loginTitle();
}
