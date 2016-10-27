package com.catalyst.teammateria.timeclock.functional_tests.pages;

public interface AdminSplashPage {

    /**
     * Returns true if on the admin splash page, else false
     * 
     * @return
     */
    public boolean adminSplashTitle();

    /**
     * Returns true if the account config link is present, else false
     * 
     * @return
     */
    public boolean accountConfigLinkPresent();

    /**
     * Returns true if the time management link is present, else false
     * 
     * @return
     */
    public boolean timeManagementLinkPresent();

    /**
     * Returns true if the reporting link is present, else false
     * 
     * @return
     */
    public boolean reportingLinkPresent();
}
