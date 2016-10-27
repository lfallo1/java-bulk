package com.catalyst.cycle.jdbc_demo.webservices;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.cycle.jdbc_demo.model.User;
import com.catalyst.cycle.jdbc_demo.service.UserService;
import com.catalyst.cycle.jdbc_demo.utils.CyclicStateException;
import com.catalyst.cycle.jdbc_demo.utils.ErrorMessageDecoder;
import com.catalyst.cycle.jdbc_demo.utils.InvalidIdentifierException;
import com.catalyst.cycle.jdbc_demo.utils.InvalidInputException;

/**
 * Web Service calls related to User objects in the database. Objects returned
 * are serialized into JSON Objects.
 * 
 * The RestController annotation is the same as having the Controller and
 * ResponseBody annotations.
 * 
 * @author egover
 *
 */
@RestController
public class UserWebService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets a list of all the users currently in the database.
     * 
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> allUsersGET() {
        return userService.allUsers();
    }

    /**
     * Gets a single user given an ID
     * 
     * @param userID
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User userByIDGET(@PathVariable("id") Integer userID,
            HttpServletResponse response) throws IOException {
        logger.trace("Level = TRACE: Entered userByID_GET method");
        logger.debug("Level = DEBUG: Entered userByID_GET method");
        logger.error("Level = ERROR: Entered userByID_GET method");
        logger.fatal("Level = FATAL: Entered userByID_GET method");
        User user = null;
        try {
            user = this.userService.getUserByID(userID);
        } catch (InvalidIdentifierException nRE) {

            logger.warn("Invalid userID in path.");
            // Invalid userID.

            response.sendError(400, "Invalid userID "+nRE.getKey()+" in path.");

        }
        return user;
    }

    /**
     * Gets all users that have a manager with the given userID
     * 
     * @param userID
     * @return
     */
    @RequestMapping(value = "/users/{id}/staff", method = RequestMethod.GET)
    public List<User> getStaff(@PathVariable("id") Integer userID,
            HttpServletResponse response) {

        return this.userService.staff(userID);

    }

    /**
     * Creates a user
     * 
     * @param user
     * @throws IOException 
     */
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public void addUser(User user,  HttpServletResponse response) throws IOException {
  
        try {
            userService.addUser(user);
        } catch (InvalidInputException e) { 
            //In reality, we dont need to send THESE error messages to the user.
            //Front end validation would have prevented them and we could just return 
            //the error status (400). If we were preforming more complex validation 
            //that the frontend cannot preform, then we would want to pass back an error message
            //describing the issue.
            response.sendError(400, ErrorMessageDecoder.decodeMessages( e.getErrorCodes()));
        }
    }

    /**
     * Updates a user in the database. The id of the user determines which row
     * to update. If the id is null or does not match a row in the database, the
     * user is created instead.
     * 
     * @param user
     * @throws IOException 
     */
    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public void updateUser(User user, HttpServletResponse response) throws IOException {
         
        try {
            this.userService.editUser(user);
        } catch (CyclicStateException e) { 
            response.sendError(400, "User with id="+e.getUser().getUserID()+" cannot be their own manager."); 
        }
    }

    /**
     * Deletes all users in the database. Very little reason to have something
     * like this.
     * 
     * @param response
     * @return
     */
    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    public String deleteAllUsers(HttpServletResponse response) {
        Integer count = this.userService.allUsers().size();

        this.userService.deleteAllUsers();

        return "Success: " + count + " rows deleted";

    }

}
