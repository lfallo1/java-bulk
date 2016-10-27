package com.catalyst.teammateria.timeclock.formbeans;

import org.springframework.stereotype.Service;

/**
 * Object that tracks how many consecutive invalid login attempts have occurred,
 * and whether or not a given account is locked
 * 
 * @author lfallon
 */
@Service
public class LoginForm {

    private boolean accountLocked;
    private boolean isValid;

    /**
     * Default constructor creates empty LoginForm Object
     */
    public LoginForm() {
    }

    /**
     * Gets whether account for specified User has been locked from invalid
     * login attempts
     * 
     * @return Boolean locked account or open
     */
    public boolean isAccountLocked() {
        return accountLocked;
    }

    /**
     * Sets whether account for specified User has been locked from invalid
     * login attempts
     * 
     * @param accountLocked
     *            Boolean locked account or open
     */
    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    /**
     * Gets whether the login information for specified User is an existing
     * record
     * 
     * @return Boolean whether login information is accurate
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Sets whether the login informatio0n for specified User is an existing
     * record
     * 
     * @param isValid
     *            Boolean valid info ir invalid
     */
    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }
}
