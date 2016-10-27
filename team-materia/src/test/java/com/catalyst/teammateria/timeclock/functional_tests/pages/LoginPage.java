package com.catalyst.teammateria.timeclock.functional_tests.pages;

/**
 * Defines basic functionality of the Login page.
 * 
 * @author nPoloway
 */
public interface LoginPage {

    /**
     * Gets username from the login page
     * 
     * @return
     */
    String getUserName();

    /**
     * Gets password from the login page
     * 
     * @return
     */
    String getUserPassword();

    /**
     * Gets error message upon invalid data submission
     * 
     * @return
     */
    String getErrorMessage();

    /**
     * Sets the username for the login page
     * 
     * @param username
     */
    void setUsername(String username);

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

    /**
     * Returns true if on the admin splash page, else false
     * 
     * @return
     */
    boolean adminSplashPageTitle();

    /**
     * Returns true if on the time tracking page, else false
     * 
     * @return
     */
    boolean timeTrackingTitle();
}
