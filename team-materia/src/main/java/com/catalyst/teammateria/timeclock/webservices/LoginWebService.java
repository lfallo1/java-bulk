package com.catalyst.teammateria.timeclock.webservices;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.formbeans.LoginForm;
import com.catalyst.teammateria.timeclock.services.LoginService;

/**
 * LoginWebService Handle requests related to login
 * 
 * @author lfallon
 */
@RestController
public class LoginWebService {

    private LoginService loginService;
    private UserDao      userDao;

    /**
     * Sets LoginService for current login attempt
     * 
     * @param loginService
     *            LoginService to be assigned
     */
    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Sets data access object for interaction with the User table
     * 
     * @param userDao
     *            UserDao to be assigned
     */
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Given a username and password, validate the information using the
     * LoginForm object. if the login is valid, add user to session. <br/>
     * Always return the loginform object which contains information about
     * whether login was successful, if user is locked out, and how many invalid
     * attempts have occurred for the given user
     * 
     * @param session
     * @param username
     * @param password
     * @return LoginForm
     */
    @RequestMapping (value = "/login", method = RequestMethod.POST)
    public LoginForm loginPost(HttpSession session, String username,
            String password) {
        LoginForm loginForm = loginService.validateUser(username, password);
        if (loginForm.isValid()) {
            session.setAttribute("currentUser",
                    userDao.getUserByUsername(username));
        }
        return loginForm;
    }
}
