package com.catalyst.teammateria.injuryreport.functional_tests.pages;

/**
 * Defines basic functionality for the employee search page
 * 
 * @author nPoloway
 */
public interface EmployeeSearchPage {

    /**
     * Set the value of the first name input field
     * 
     * @param firstName
     */
    void setFirstName(String firstName);

    /**
     * Set the value of the last name input field
     * 
     * @param lastName
     */
    void setlastName(String lastName);

    /**
     * Set the value of the employee id input field
     * 
     * @param Id
     */
    void setId(String Id);

    /**
     * Click the lookup button
     */
    void clickLookup();

    /**
     * Returns true if user1 was returned by the search, else false
     * 
     * @return
     */
    Boolean employee1Visible();

    /**
     * Returns true if user1 was returned by the search, else false
     * 
     * @return
     */
    Boolean employee2Visible();
    
    /**
     * 
     * @return
     */
    Boolean employee3Visible();

    /**
     * Click on employee1's injury reporting link
     */
    void clickEmployee1();

    /**
     * Click on employee2's injury reporting link
     */
    void clickEmployee2();

    /**
     * Returns true if on the injury reporting page, else false
     */
    Boolean onInjuryReportingTitle();

    /**
     * Returns true if on the employee search page, else false
     * 
     * @return
     */
    Boolean onEmployeeSearchTitle();
    
    /**
     * Returns true if an error message is present, else false
     * @return
     */
    Boolean isErrorMessagePresent();
}