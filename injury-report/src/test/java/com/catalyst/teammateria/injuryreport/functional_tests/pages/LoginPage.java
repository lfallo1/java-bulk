package com.catalyst.teammateria.injuryreport.functional_tests.pages;

/**
 * Defines basic functionality of the login page
 * 
 * @author nPoloway
 */
public interface LoginPage {

    /**
     * Gets username from #inputUsername textbox
     * 
     * @return username
     */
    String getUsername();

    /**
     * Gets password from inputPassword textbox
     * 
     * @return password
     */
    String getPassword();

    /**
     * Gets error message if errorMessage div contains text, empty String if not
     * 
     * @return error message
     */
    String getErrorMessage();

    /**
     * Sets username in #inputUsername textbox
     * 
     * @param username
     *            username to be sent
     */
    void setUsername(String username);

    /**
     * Sets password in #inputPassword textbox
     * 
     * @param password
     *            password to be sent
     */
    void setPassword(String password);

    /**
     * Clicks "_spring_security_remember_me" checkbox
     */
    void toggleRememberMe();

    /**
     * Checks "_spring_security_remember_me" checkbox status
     * 
     * @return true if box is checked
     */
    Boolean isRemembered();

    /**
     * Clicks "submit" button
     */
    void submitLogin();

    /**
     * Checks page title
     * 
     * @return true if title is "Login Page"
     */
    Boolean loginTitle();
}
