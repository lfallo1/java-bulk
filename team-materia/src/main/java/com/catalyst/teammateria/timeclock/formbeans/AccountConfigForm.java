package com.catalyst.teammateria.timeclock.formbeans;

import java.io.Serializable;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;

/**
 * This form is used to combine a user and their password off of the
 * AccountConfig page
 * 
 * @author dGrimes
 */
public class AccountConfigForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private User              user;
    private String            password;

    /**
     * Gets User Object associated with specified AccountConfigForm Object
     * 
     * @return User Object
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets User Object associated with specified AccountConfigForm Object
     * 
     * @param user
     *            User to be associated
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets password associated with specified AccountConfigForm Object
     * 
     * @return password String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password associated with specified AccountConfigForm Object
     * 
     * @param password
     *            password String to be associated
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
