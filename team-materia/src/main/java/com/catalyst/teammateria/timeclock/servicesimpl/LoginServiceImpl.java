package com.catalyst.teammateria.timeclock.servicesimpl;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserInvalidLoginAttempts;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserInvalidLoginAttemptsDao;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;
import com.catalyst.teammateria.timeclock.formbeans.LoginForm;
import com.catalyst.teammateria.timeclock.services.EmailService;
import com.catalyst.teammateria.timeclock.services.LoginService;
import com.catalyst.teammateria.timeclock.util.PasswordUtil;

/**
 * The implementation of LoginService
 * 
 * @author aDietrich
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static final String EMAIL_SUBJECT_LOCKED_OUT = " locked out";
	private static final String EMAIL_BODY_ACCOUNT_LOCKED = " was locked out of the system due to multiple unsuccessful login attempts";
	private LoginForm loginForm;
	private UserPasswordDao userPasswordDao;
	private UserDao userDao;
	private UserInvalidLoginAttemptsDao userInvalidLoginAttemptsDao;
	private EmailService emailService;

	/**
	 * Sets LoginForm for current login attempt
	 * 
	 * @param loginForm
	 *            LoginForm to be assigned
	 */
	@Autowired
	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
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
	 * Sets EmailService for current login attempt
	 * 
	 * @param emailService
	 *            EmailService to be assigned
	 */
	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * Sets data access object for interaction with UserInvalidLoginAttempts
	 * table
	 * 
	 * @param userInvalidLoginAttemptsDao
	 *            UserInvalidLoginAttemptsDao to be assigned
	 */
	@Autowired
	public void setUserInvalidLoginAttemptsDao(
			UserInvalidLoginAttemptsDao userInvalidLoginAttemptsDao) {
		this.userInvalidLoginAttemptsDao = userInvalidLoginAttemptsDao;
	}

	@Override
	public LoginForm validateUser(String username, String password) {
		this.loginForm = new LoginForm();
		this.loginForm.setValid(false);
		this.loginForm.setAccountLocked(false);
		String hash = PasswordUtil.encryptPassword(password, username);
		User user = null;
		try {
			//get the user object associated with that username (throw error if not found)
			user = userDao.getUserByUsername(username);
			//If the user is not active, set the form bean properties to show that account is locked
			if (!user.getActive()) {
				this.loginForm.setAccountLocked(true);
				this.loginForm.setValid(false);
			} else {
				//If account is not locked, check if password if valid.
				this.loginForm.setValid(userPasswordDao.userPasswordMatch(
						user.getUserId(), hash));
				//if we got here, the user/pass was valid. so delete
				//the invalid attempts record from the database
				deleteInvalidLoginAttemptsObj(user);
			}
		} catch (NoResultException ex) {
			this.loginForm.setValid(false);
			if (user != null) {
				incrementInvalidAttempts(user);
			}
		}
		return this.loginForm;
	}

	/**
	 * Get the number of (if any) consecutive failed login attempts
	 * this user has, and increment it by one. If the new number of
	 * invalid login attempts after incrementing is greater than 2,
	 * lock the account, email all admins, and delete the invalidLoginAttempts
	 * record from the db 
	 * 
	 * @param username
	 */
	private void incrementInvalidAttempts(User user) {
		UserInvalidLoginAttempts userInvalidAttempts = createAndReturnInvalidLoginAttemptsObj(user);
		Integer invalidAttempts = userInvalidAttempts.getAttempts();
		userInvalidAttempts.setAttempts(++invalidAttempts);
		userInvalidLoginAttemptsDao
				.updateUserInvalidLoginAttempts(userInvalidAttempts);
		if (invalidAttempts > 2) {
			deleteInvalidLoginAttemptsObj(user);
			user.setActive(false);
			userDao.updateUser(user);
			this.loginForm.setAccountLocked(true);
			emailService.sendMail(
					user.getUsername() + EMAIL_SUBJECT_LOCKED_OUT,
					user.getUsername() + EMAIL_BODY_ACCOUNT_LOCKED);
		}
	}

	/**
	 * delete userInvalidLoginAttempts if exists
	 * 
	 * @param user
	 */
	private void deleteInvalidLoginAttemptsObj(User user) {
		try {
			UserInvalidLoginAttempts userInvalidLoginAttempts = userInvalidLoginAttemptsDao
					.getUserInvalidLoginAttemptsByUser(user);
			userInvalidLoginAttemptsDao
					.deleteUserInvalidLoginAttempts(userInvalidLoginAttempts);
		} catch (NoResultException ex) {
			// TODO no entity exists to delete
		}
	}

	/**
	 * create and return a UserInvalidLoginAttempts object. If an entry
	 * for this user already exits in the database, return that object.
	 * if not, create a new one.
	 * 
	 * @param user
	 * @return
	 */
	private UserInvalidLoginAttempts createAndReturnInvalidLoginAttemptsObj(
			User user) {
		UserInvalidLoginAttempts userInvalidAttempts = null;
		try {
			userInvalidAttempts = userInvalidLoginAttemptsDao
					.getUserInvalidLoginAttemptsByUser(user);
		} catch (NoResultException ex) {
			userInvalidAttempts = new UserInvalidLoginAttempts();
			userInvalidAttempts.setUserId(user.getUserId());
			userInvalidAttempts.setAttempts(0);
			userInvalidLoginAttemptsDao
					.addUserInvalidLoginAttempts(userInvalidAttempts);
		}
		return userInvalidAttempts;
	}
}
