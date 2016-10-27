package com.catalyst.teammateria.timeclock.formbeans;

import java.io.Serializable;

/**
 * Form taken in from the Registration page
 * 
 * @author dGrimes
 */
public class RegistrationForm implements Serializable {

    private static final long serialVersionUID = 1L;
    private String            firstName;
    private String            lastName;
    private String            username;
    private String            email;
    private String            password;
    private String            passwordConfirm;

    /**
     * Gets firstName associated with specified RegistrationForm Object
     * 
     * @return associated firstName String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets firstName for specified RegistrationForm Object
     * 
     * @param firstName
     *            String firstName to be assigned
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets lastName associated with specified RegistrationForm Object
     * 
     * @return associated lastName String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets lastName for specified RegistrationForm Object
     * 
     * @param lastName
     *            String lastName to be assigned
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets username associated with specified RegistrationForm Object
     * 
     * @return associated username String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username for specified RegistrationForm Object
     * 
     * @param username
     *            String username to be assigned
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets email address associated with specified RegistrationForm Object
     * 
     * @return associated email String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email address for specified RegistrationForm Object
     * 
     * @param email
     *            String email address to be assigned
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets password associated with specified RegistrationForm Object
     * 
     * @return associated password String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password for specified RegistrationForm Object
     * 
     * @param password
     *            String password to be assigned
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets password confirmation associated with specified RegistrationForm
     * Object
     * 
     * @return associated confirmation password String
     */
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    /**
     * Sets confirmation password for specified RegistrationForm Object
     * 
     * @param passwordConfirm
     *            String confirmation password to be assigned
     */
    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
}
