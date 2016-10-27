package com.catalyst.teammateria.timeclock.functional_tests.pages;

/**
 * Defines basic functionality of the Add User page.
 * 
 * @author nPoloway
 */
public interface AddUserPage {
    /**
     * Gets the value of the first name input field
     * @return
     */
    public String getFirstName();
    
    /**
     * Sets the value of the first name input field
     * @param firstName
     */
    public void setFirstName(String firstName);
    
    /**
     * Get the value of the last name input field
     * @return
     */
    public String getLastName();
    
    /**
     * Set the value of the last name input field
     * @param lastName
     */
    public void setLastName(String lastName);
    
    /**
     * Get the value of the username input field
     * @return
     */
    public String getUserName();
    
    /**
     * Set the value of the username field
     * @param userName
     */
    public void setUserName(String userName);
    
    /**
     * Get the value of the email input field
     * @return
     */
    public String getEmail();
    
    /**
     * Set the value of the email input field
     * @param email
     */
    public void setEmail(String email);
    
    /**
     * Get the value of the password field
     * @return
     */
    public String getPassword();
    
    /**
     * Set the value of the password field
     * @param password
     */
    public void setPassword(String password);
    
    /**
     * Get the value of the confirm password field
     * @return
     */
    public String getConfirmPassword();
    
    /**
     * Set the value of the confirm password field
     * @return
     */
    public void setConfirmPassword(String confirmPassword);
    
    /**
     * Submit the register form 
     */
    public void submitRegisterForm();
    
    /**
     * Returns true if on the registration page, else false
     * @return
     */
    public boolean registerTitle();
    
    /**
     * Returns true if on the successful registration page, else false
     * @return
     */
    public boolean registrationSuccessTitle();
    
}
