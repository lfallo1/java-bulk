package com.catalyst.teammateria.injuryreport.functional_tests.pages;

/**
 * Defines basic functionality for report submission review
 * 
 * @author nPoloway
 */
public interface ConfirmationPage {

    /**
     * Clicks the submit button
     */
    void clickSubmit();

    /**
     * Checks for the prompt to record confirmation number
     * 
     * @return true if prompt is present
     */
    Boolean promptIsPresent();

    /**
     * Checks for the presence of a confirmation number
     * 
     * @return true if confirmation number is present
     */
    Boolean idIsPresent();

    /**
     * Clicks home link
     */
    void clickHome();

    /**
     * Checks whether driver is currently on the confirmation page
     * 
     * @return true if on confirmation page
     */
    Boolean confirmationTitle();

    /**
     * Checks whether driver is currently on the login page
     * 
     * @return true if on login page
     */
    Boolean loginTitle();
}
