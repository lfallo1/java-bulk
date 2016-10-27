package com.catalyst.teammateria.injuryreport.functional_tests.pages;

import java.util.List;

import org.openqa.selenium.WebElement;

/**
 * Defines basic functionality for the admin review page
 * 
 * @author nPoloway
 */
public interface AdminReviewPage {

    /**
     * Gets value in reportId textbox
     * 
     * @return unique identifier to search for
     */
    String getId();

    /**
     * Sets value in reportId textbox
     * 
     * @param id
     *            unique identifier to search for
     */
    void setId(String id);

    /**
     * Gets value in firstName textbox
     * 
     * @return first name to search for
     */
    String getFirstName();

    /**
     * Sets value in firstName textbox
     * 
     * @param firstName
     *            first name to search for
     */
    void setFirstName(String firstName);

    /**
     * Gets value in lastName textbox
     * 
     * @return last name to search for
     */
    String getLastName();

    /**
     * Sets value in lastName textbox
     * 
     * @param lastName
     *            last name to search for
     */
    void setLastName(String lastName);

    /**
     * Gets value in date textbox
     * 
     * @return date to search for
     */
    String getDate();

    /**
     * Sets value in date textbox
     * 
     * @param date
     *            date to search for
     */
    void setDate(String date);

    /**
     * Gets selected value from active dropdown
     * 
     * @return true if searching active
     */
    Boolean getStatus();

    /**
     * Sets selected value from active dropdown
     * 
     * @param isEnabled
     *            Boolean value to search for
     */
    void setStatus(Boolean isEnabled);

    /**
     * Clicks the search button to populate searchResults list
     */
    void clickSearch();

    /**
     * Gets a list of all rows in the searchResults list
     * 
     * @return WebElements in searchResults
     */
    List<WebElement> getSearchResults();

    /**
     * Clicks a specific row of the searchResults list
     * 
     * @param rowNumber
     *            row to click
     */
    void clickReport(int rowNumber);

    /**
     * Clicks a specific disable button in the searchResults list
     * 
     * @param rowNumber
     *            button to click
     */
    void clickDisable(int rowNumber);

    /**
     * Clicks logout button
     */
    void clickLogout();

    /**
     * Tells whether the individual report overlay is displayed
     * 
     * @return true if overlay is displayed
     */
    Boolean overlayIsDisplayed();

    /**
     * Clicks update button on individual report page
     */
    void clickUpdate();

    /**
     * Clicks cancel button on individual report page
     */
    void clickCancel();

    /**
     * Sets approver comment field to specified string
     * 
     * @param comments
     *            Comments to be set in the approver comments field
     */
    void setApproverComments(String comments);

    /**
     * Gets approver comments from individual report page
     * 
     * @return comments
     */
    String getApproverComments();
}
