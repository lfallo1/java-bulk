package com.catalyst.teammateria.timeclock.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;
import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;
import com.catalyst.teammateria.timeclock.services.RegisterService;
import com.catalyst.teammateria.timeclock.util.PasswordUtil;
import com.catalyst.teammateria.timeclock.util.RegistrationUtil;
import com.catalyst.teammateria.timeclock.util.ValidationUtil;

/**
 * The implementation of RegisterService
 * 
 * @author aDietrich
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    private static final String FIRST_NAME_ERROR       = "Errors on first name";
    private static final String LAST_NAME_ERROR        = "Errors on last name";
    private static final String USERNAME_ERROR         = "Errors on username";
    private static final String USERNAME_TAKEN_ERROR   = "The username you have entered is already registered";
    private static final String EMAIL_ERROR            = "Errors on email";
    private static final String EMAIL_TAKEN_ERROR      = "The email you have entered is already registered";
    private static final String PASSWORD_ERROR         = "Errors on password";
    private static final String PASSWORD_CONFIRM_ERROR = "Passwords do not match";
    private static final String NEW_LINE               = "<br>";
    private UserDao             userDao;

    /**
     * Sets data access object for interaction with User table
     * 
     * @param userDao
     *            UserDao to be assigned
     */
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserPasswordDao userPasswordDao;

    /**
     * Sets data access object for interaction with UserPassword table
     * 
     * @param userPasswordDao
     *            UserPasswordDao to be assigned
     */
    @Autowired
    public void setUserPasswordDao(UserPasswordDao userPasswordDao) {
        this.userPasswordDao = userPasswordDao;
    }

    @Override
    @Transactional
    public String add(RegistrationForm form) {
        StringBuilder errors = validate(form);
        // Create a new user with the data from the form
        User user = RegistrationUtil.separateUser(form);
        //
        if ( !checkEmail(user.getEmail())) {
            errors.append(EMAIL_TAKEN_ERROR + NEW_LINE);
        }
        if ( !checkUsername(user.getUsername())) {
            errors.append(USERNAME_TAKEN_ERROR + NEW_LINE);
        }
        errors.trimToSize();
        if (errors.capacity() > 0) {
            return errors.toString();
        }
        // add the user
        userDao.addUser(user);
        // pass in the password
        userPasswordDao.addUserPassword(new UserPassword(user.getUserId(),
                PasswordUtil.encryptPassword(form.getPassword(),
                        form.getUsername())));
        return "true";
    }

    @Override
    public StringBuilder validate(RegistrationForm form) {
        StringBuilder errors = new StringBuilder();
        if ( !ValidationUtil.isAlpha(form.getFirstName())) {
            errors.append(FIRST_NAME_ERROR + NEW_LINE);
        }
        if ( !ValidationUtil.isAlpha(form.getLastName())) {
            errors.append(LAST_NAME_ERROR + NEW_LINE);
        }
        if ( !ValidationUtil.isAlphaNumeric(form.getUsername())) {
            errors.append(USERNAME_ERROR + NEW_LINE);
        }
        if ( !ValidationUtil.isEmail(form.getEmail())) {
            errors.append(EMAIL_ERROR + NEW_LINE);
        }
        if ( !ValidationUtil.isValidPassword(form.getPassword())) {
            errors.append(PASSWORD_ERROR + NEW_LINE);
        }
        if ( !form.getPassword().equals(form.getPasswordConfirm())) {
            errors.append(PASSWORD_CONFIRM_ERROR + NEW_LINE);
        }
        return errors;
    }

    @Override
    public boolean checkEmail(String email) {
        return userDao.emailAvailable(email);
    }

    @Override
    public boolean checkUsername(String username) {
        return userDao.userNameAvailable(username);
    }
}
