package com.catalyst.teammateria.injuryreport.functional_tests.pages;

/**
 * Defines basic functionality to logout of admin splash page, review page, and
 * report page
 * 
 * @author nPoloway
 */
public interface AdminLogouts {

    /**
     * Clicks the logout link on any admin page
     */
    public void clickLogout();

    /**
     * Clicks review link
     */
    public void clickReview();

    /**
     * Clicks report link
     */
    public void clickReport();
}
