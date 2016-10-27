package com.catalyst.teammateria.timeclock.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.formbeans.AccountConfigForm;
import com.catalyst.teammateria.timeclock.services.AccountConfigService;

/**
 * AccountConfigWebService Handles requests related to Account Config page
 * 
 * @author aDietrich
 */
@RestController
public class AccountConfigWebService {

    private AccountConfigService accountConfigService;

    /**
     * Sets AccountConfigService for current account configuration
     * 
     * @param accountConfigService
     *            AccountConfigService to be assigned
     */
    @Autowired
    public void setAccountConfigService(
            AccountConfigService accountConfigService) {
        this.accountConfigService = accountConfigService;
    }

    /**
     * This method is used to pull the users from the database for the user box
     * at the top of the form
     * 
     * @param withInactive
     *            - if true include inactive
     * @return a list of users for the select box
     */
    @RequestMapping (value = "/accountconfig/users", method = RequestMethod.GET)
    public List<User> getUserList(boolean withInactive) {
        return accountConfigService.getUserList(withInactive);
    }

    /**
     * Pulls one user from the database selected by ID
     * 
     * @param id
     *            user to return
     * @return a user with corresponding ID
     */
    @RequestMapping (value = "/accountconfig/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id) {
        return accountConfigService.getUserById(id);
    }

    /**
     * Applies values from an account configuration form to a user
     * 
     * @param accountConfigForm
     *            specific values to apply to a user
     * @return updated user
     */
    @RequestMapping (value = "/accountconfig", method = RequestMethod.PUT)
    public String updateUser(AccountConfigForm accountConfigForm) {
        return accountConfigService.updateUser(accountConfigForm);
    }

    /**
     * Checks for the presence of a username in a previously created record
     * 
     * @param userId
     *            ID of the user to take new username
     * @param username
     *            new username to be checked for validity / availability
     * @return whether desired username is valid and available
     */
    @RequestMapping (value = "/accountconfig/username", method = RequestMethod.GET)
    public boolean checkUsername(int userId, String username) {
        return accountConfigService.checkUsername(userId, username);
    }

    /**
     * Checks for the presence of an email address in a previously created
     * record
     * 
     * @param userId
     *            ID of the user to take new email address
     * @param email
     *            new email address to be checked for validity / availability
     * @return whether desired email is valid and available
     */
    @RequestMapping (value = "/accountconfig/email", method = RequestMethod.GET)
    public boolean checkEmail(int userId, String email) {
        return accountConfigService.checkEmail(userId, email);
    }
}
