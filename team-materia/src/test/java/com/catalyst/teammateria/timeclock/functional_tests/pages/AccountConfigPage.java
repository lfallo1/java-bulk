package com.catalyst.teammateria.timeclock.functional_tests.pages;

public interface AccountConfigPage {

    /**
     * Returns the value of the user drop down box
     * 
     * @return
     */
    public String getUser();

    /**
     * Sets the value of the user drop down box
     * 
     * @param user
     */
    public void setUser(String user);

    /**
     * Returns the value of the first name input field
     * 
     * @return
     */
    public String getFirstName();

    /**
     * Sets the value of the first name input field
     * 
     * @param firstName
     */
    public void setFirstName(String firstName);

    /**
     * Returns the value of the last name input field
     * 
     * @return
     */
    public String getLastName();

    /**
     * Sets the value of the last name input field
     * 
     * @param lastName
     */
    public void setLastName(String lastName);

    /**
     * Return the value of the username input field
     * 
     * @return
     */
    public String getUsername();

    /**
     * Set the value of the username input field
     * 
     * @param username
     */
    public void setUserName(String username);

    /**
     * Return the value of the email input field
     * 
     * @return
     */
    public String getEmail();

    /**
     * Set the value of the email input field
     * 
     * @param email
     */
    public void setEmail(String email);

    /**
     * Return the value of the password input field
     * 
     * @return
     */
    public String getPassword();

    /**
     * Set the value of the password input field
     * 
     * @param password
     */
    public void setPassword(String password);

    /**
     * Returns true if the active radio button is selected, else false
     * 
     * @return
     */
    public boolean getActive();

    /**
     * Toggles status of the active radio button
     */
    public void selectActive();

    /**
     * Return true if inactive radio button is selected, else false
     * 
     * @return
     */
    public boolean getInactive();

    /**
     * Toggles status of inactive radio button
     */
    public void selectInactive();

    /**
     * Return the visible text for the selected role in the role dropdown input
     * field
     * 
     * @return
     */
    public String getRole();

    /**
     * Set the value of the role dropdown input field
     * 
     * @param role
     */
    public void setRole(String role);

    /**
     * Click the inactive button
     */
    public void clickInactiveButton();

    /**
     * Returns the inner text of the inactive button
     */
    public String getInactiveButtonValue();

    /**
     * Click the update button
     */
    public void clickUpdate();

    /**
     * Click the cancel button
     */
    public void clickCancel();

    /**
     * Click the add user button
     */
    public void clickAddUser();

    /**
     * Returns true if on teh account configuration page, else false
     */
    public boolean accountConfigTitle();
    
    /**
     * Click OK on alert box
     */
    public void confirmAlert();   
    
    /**
     * Return the visible text on the update button
     * @return
     */
    public String getUpdateButtonVisibleText();
    
    /**
     * Return true if the user dropdown box is active, else false
     * @return
     */
    public boolean userDropdownVisible();
    
    /**
     * Return true if the showInactiveButton is visible, else false 
     * @return
     */
    public boolean showInactiveButtonVisible();
    
    /**
     * Return true if the role dropdown box is visible, else false
     * @return
     */
    public boolean roleDropdownVisible();
    
    /**
     * Returns true if the active radio buttons are visible, else false
     * @return
     */
    public boolean activeRadioButtonsVisible();
    
    /**
     * Returns true if the user is present in the user dropdown box, else false
     * @param userVisibleText - the visible text representing the user
     * @return
     */
    public boolean userPresent(String userVisibleText); 
}
