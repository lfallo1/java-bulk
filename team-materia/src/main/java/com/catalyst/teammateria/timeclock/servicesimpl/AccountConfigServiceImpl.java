package com.catalyst.teammateria.timeclock.servicesimpl;

import static com.catalyst.teammateria.timeclock.util.ValidationUtil.isAlpha;
import static com.catalyst.teammateria.timeclock.util.ValidationUtil.isAlphaNumeric;
import static com.catalyst.teammateria.timeclock.util.ValidationUtil.isEmail;
import static com.catalyst.teammateria.timeclock.util.ValidationUtil.isValidPassword;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;
import com.catalyst.teammateria.timeclock.formbeans.AccountConfigForm;
import com.catalyst.teammateria.timeclock.services.AccountConfigService;
import com.catalyst.teammateria.timeclock.util.PasswordUtil;

/**
 * The specific implementation of the AccounfConfigService Interface
 * 
 * @author dGrimes
 */
@Service
public class AccountConfigServiceImpl implements AccountConfigService {

    private static final String FIRST_NAME_ERROR     = "Errors on first name";
    private static final String LAST_NAME_ERROR      = "Errors on last name";
    private static final String USERNAME_ERROR       = "Errors on username";
    private static final String USERNAME_TAKEN_ERROR = "The username you have entered is already registered";
    private static final String EMAIL_ERROR          = "Errors on email";
    private static final String EMAIL_TAKEN_ERROR    = "The email you have entered is already taken";
    private static final String PASSWORD_ERROR       = "Errors on password";
    private static final String NEW_LINE             = "<br>";
    private UserDao             userDao;
    private UserPasswordDao     userPasswordDao;
    private StringBuilder       errors               = new StringBuilder();

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
    public List<User> getUserList(boolean withInactive) {
        return userDao.getUsersForSelect(withInactive);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public String updateUser(AccountConfigForm accountConfigForm) {
        String errorsFinal = validate(accountConfigForm);
        if (errorsFinal.length() == 0) {
            userDao.updateUser(accountConfigForm.getUser());
            if (accountConfigForm.getPassword().length() != 0) {
                UserPassword userPassword = new UserPassword();
                userPassword.setUserId(accountConfigForm.getUser().getUserId());
                userPassword.setUserHash(PasswordUtil.encryptPassword(
                        accountConfigForm.getPassword(), accountConfigForm
                                .getUser().getUsername()));
                userPasswordDao.updateUserPassword(userPassword);
            }
            return "true";
        }
        return errorsFinal;
    }

    @Override
    public String validate(AccountConfigForm accountConfigForm) {
        validateFirstName(accountConfigForm.getUser().getFirstName());
        validateLastName(accountConfigForm.getUser().getLastName());
        validateUsername(accountConfigForm.getUser().getUsername());
        validateUsernameAvailable(accountConfigForm.getUser().getUserId(),
                accountConfigForm.getUser().getUsername());
        validateEmail(accountConfigForm.getUser().getEmail());
        validateEmailAvailable(accountConfigForm.getUser().getUserId(),
                accountConfigForm.getUser().getEmail());
        validatePassword(accountConfigForm.getPassword());
        errors.trimToSize();
        return errors.toString();
    }

    @Override
    public void validateFirstName(String firstName) {
        if ( !isAlpha(firstName)) {
            errors.append(FIRST_NAME_ERROR + NEW_LINE);
        }
    }

    @Override
    public void validateLastName(String lastName) {
        if ( !isAlpha(lastName)) {
            errors.append(LAST_NAME_ERROR + NEW_LINE);
        }
    }

    @Override
    public void validateUsername(String username) {
        if ( !isAlphaNumeric(username)) {
            errors.append(USERNAME_ERROR + NEW_LINE);
        }
    }

    @Override
    public void validateUsernameAvailable(Integer userId, String username) {
        if ( !userDao.usernameRemainedTheSame(userId, username)
                && !userDao.userNameAvailable(username)) {
            errors.append(USERNAME_TAKEN_ERROR + NEW_LINE);
        }
    }

    @Override
    public void validateEmail(String email) {
        if ( !isEmail(email)) {
            errors.append(EMAIL_ERROR + NEW_LINE);
        }
    }

    @Override
    public void validateEmailAvailable(Integer userId, String email) {
        if ( !userDao.emailRemainedTheSame(userId, email)
                && !userDao.emailAvailable(email)) {
            errors.append(EMAIL_TAKEN_ERROR + NEW_LINE);
        }
    }

    @Override
    public void validatePassword(String password) {
        if ( !"".equals(password) && !isValidPassword(password)) {
            errors.append(PASSWORD_ERROR);
        }
    }

    @Override
    public boolean checkEmail(int userId, String email) {
        if ( !userDao.emailAvailable(email)
                && !userDao.emailRemainedTheSame(userId, email)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean checkUsername(int userId, String username) {
        if ( !userDao.userNameAvailable(username)
                && !userDao.usernameRemainedTheSame(userId, username)) {
            return false;
        }
        return true;
    }
}
