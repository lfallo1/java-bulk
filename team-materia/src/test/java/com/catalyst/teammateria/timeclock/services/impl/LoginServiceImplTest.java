package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;

import javax.persistence.NoResultException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserInvalidLoginAttempts;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserInvalidLoginAttemptsDao;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;
import com.catalyst.teammateria.timeclock.dao.UserRoleDao;
import com.catalyst.teammateria.timeclock.formbeans.LoginForm;
import com.catalyst.teammateria.timeclock.services.EmailService;
import com.catalyst.teammateria.timeclock.servicesimpl.LoginServiceImpl;

@RunWith (MockitoJUnitRunner.class)
public class LoginServiceImplTest {

    @InjectMocks
    private LoginServiceImpl target;

    @Mock
    LoginForm form;

    @Mock
    UserPasswordDao userPasswordDao;

    @Mock
    UserInvalidLoginAttemptsDao userInvalidLoginAttemptsDao;

    @Mock
    UserInvalidLoginAttempts attempt;

    @Mock
    UserDao userDao;

    @Mock
    EmailService emailService;

    @Mock
    UserRoleDao userRoleDao;

    @Mock
    User user;
    
    private static final int MAX_LOGIN_FAILURES = 3;

    private static final String PASSWORD_EMPTY = "";

    private static final String USERNAME_EMPTY = "";

    private static final String USERNAME_VALID = "test1";

    private static final String PASSWORD_VALID = "Password1?";

    // validate method with a non-existant user
    @Test
    public void testValidateInvalidUser() {
        // Throw a no result exception when looking for the requested user
        Mockito.when(userDao.getUserByUsername(Mockito.anyString())).thenThrow(
                new NoResultException());
        // Assert that the returned form is invalid
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    // validate method for failed login with an existing user (less than 3 login
    // attempts)
    @Test
    public void testValidateInvalidExistingUserLessThanThreeFailedLogins() {
        // Throw a no result exception when looking for the requested user
        User user = createUser(true);
        UserInvalidLoginAttempts userInvalidLoginAttempts = createUserInvalidLoginAttempts(1);
        // When called return a user object
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(user);
        // when userPasswordMatch called, throw exception
        Mockito.when(
                userPasswordDao.userPasswordMatch(Mockito.anyInt(),
                        Mockito.anyString()))
                .thenThrow(new NoResultException());
        // return invalidLoginAttempts obj
        Mockito.when(
                userInvalidLoginAttemptsDao
                        .getUserInvalidLoginAttemptsByUser(user)).thenReturn(
                userInvalidLoginAttempts);
        // Mockito.when(userInvalidLoginAttempts.getAttempts()).thenReturn(1);
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    // validate method for failed login with an existing user (less than 3 login
    // attempts)
    @Test
    public void testValidateInvalidExistingUserMoreThanThreeFailedLogins() {
        // Throw a no result exception when looking for the requested user
        User user = createUser(true);
        UserInvalidLoginAttempts userInvalidLoginAttempts = createUserInvalidLoginAttempts(3);
        // When called return a user object
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(user);
        // when userPasswordMatch called, throw exception
        Mockito.when(
                userPasswordDao.userPasswordMatch(Mockito.anyInt(),
                        Mockito.anyString()))
                .thenThrow(new NoResultException());
        // return invalidLoginAttempts obj
        Mockito.when(
                userInvalidLoginAttemptsDao
                        .getUserInvalidLoginAttemptsByUser(user)).thenReturn(
                userInvalidLoginAttempts);
        // Mockito.when(userInvalidLoginAttempts.getAttempts()).thenReturn(1);
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    // validate method for failed login with an existing user (less than 3 login
    // attempts)
    @Test
    public void testValidateInvalidExistingUserMoreThanThreeFailedLoginsDeleteException() {
        // Throw a no result exception when looking for the requested user
        User user = createUser(true);
        UserInvalidLoginAttempts userInvalidLoginAttempts = createUserInvalidLoginAttempts(3);
        // When called return a user object
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(user);
        // when userPasswordMatch called, throw exception
        Mockito.when(
                userPasswordDao.userPasswordMatch(Mockito.anyInt(),
                        Mockito.anyString()))
                .thenThrow(new NoResultException());
        // return invalidLoginAttempts obj
        Mockito.when(
                userInvalidLoginAttemptsDao
                        .getUserInvalidLoginAttemptsByUser(user)).thenReturn(
                userInvalidLoginAttempts);
        Mockito.doThrow(new NoResultException())
                .when(userInvalidLoginAttemptsDao)
                .deleteUserInvalidLoginAttempts(userInvalidLoginAttempts);
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    @Test
    public void testValidateInvalidExistingUserNoResultException() {
        // Throw a no result exception when looking for the requested user
        User user = createUser(true);
        UserInvalidLoginAttempts userInvalidLoginAttempts = createUserInvalidLoginAttempts(3);
        // When called return a user object
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(user);
        // when userPasswordMatch called, throw exception
        Mockito.when(
                userPasswordDao.userPasswordMatch(Mockito.anyInt(),
                        Mockito.anyString()))
                .thenThrow(new NoResultException());
        // return invalidLoginAttempts obj
        Mockito.when(
                userInvalidLoginAttemptsDao
                        .getUserInvalidLoginAttemptsByUser(user)).thenThrow(
                new NoResultException());
        // Mockito.when(userInvalidLoginAttempts.getAttempts()).thenReturn(1);
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    // validate method for an inactive user
    @Test
    public void testValidateInactiveUser() {
        // Return an inactive user when requested from dataabase
        User inactiveUser = new User();
        inactiveUser.setActive(false);
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(inactiveUser);
        // Assert that the returned form is not valid
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    // validate method for a valid user
    @Test
    public void testValidateValidUser() {
        User validUser = new User();
        validUser.setActive(true);
        UserInvalidLoginAttempts expectedAttempt = new UserInvalidLoginAttempts();
        Mockito.when(userDao.getUserByUsername(Mockito.anyString()))
                .thenReturn(validUser);
        Mockito.when(
                userInvalidLoginAttemptsDao
                        .getUserInvalidLoginAttemptsByUser(validUser))
                .thenReturn(expectedAttempt);
        target.validateUser(USERNAME_VALID, PASSWORD_VALID);
        Mockito.verify(userInvalidLoginAttemptsDao, times(1))
                .deleteUserInvalidLoginAttempts(expectedAttempt);
        // TODO assert
    }

    @Test
    public void testActiveUser() {
        // Return an active user when requested from dataabase
        User activeUser = new User();
        activeUser.setActive(true);
        Mockito.when(userDao.getUserByUsername(USERNAME_VALID)).thenReturn(
                activeUser);
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        assertFalse(returnedForm.isValid());
    }

    @Test
    public void testNoResult() {
        // Throw a no result exception when looking for the user
        Mockito.when(userDao.getUserByUsername(USERNAME_VALID)).thenThrow(
                new NoResultException());
        LoginForm returnedForm = target.validateUser(USERNAME_VALID,
                PASSWORD_VALID);
        // Assert that the valid is false
        assertFalse(returnedForm.isValid());
    }

    @Test
    public void testIncrementationMax() {
        Mockito.when(userDao.getUserByUsername(USERNAME_VALID)).thenThrow(
                new NoResultException()); // Return the maximum number of
                                          // invalid attempts
        // Assert lockout occured with an invalid user LoginForm
        // returnedForm = target.validateUser(USERNAME_VALID, PASSWORD_VALID);
        // assertFalse(returnedForm.isValid());
    }

    @Test
    public void testIncrementationMin() {
        /*
         * Mockito.when(form.getUserInvalidAttempts()).thenReturn(USERNAME_VALID)
         * ; Mockito.when(userDao.getUserByUsername(USERNAME_VALID)).thenThrow(
         * new NoResultException()); // Return the maximum number of invalid
         * attempts Mockito.when(form.getInvalidAttempts()).thenReturn(0); //
         * Assert lockout occured with an invalid user LoginForm returnedForm =
         * target.validateUser(USERNAME_VALID, PASSWORD_VALID);
         * assertFalse(returnedForm.isValid());
         */
    }

    @Test
    public void testFormWithoutEmailFailure() {
        /*
         * // Without throwing an error on the database...
         * Mockito.when(form.getUserInvalidAttempts
         * ()).thenReturn(USERNAME_VALID);
         * Mockito.when(form.getInvalidAttempts()
         * ).thenReturn(MAX_LOGIN_FAILURES); // Test with active user User user
         * = new User(); user.setActive(true);
         * Mockito.when(userDao.getUserByUsername(USERNAME_VALID))
         * .thenReturn(user); LoginForm returnedForm =
         * target.validateUser(USERNAME_VALID, PASSWORD_EMPTY);
         * assertFalse(returnedForm.isValid()); // Test with inactive user
         * user.setActive(false); returnedForm =
         * target.validateUser(USERNAME_VALID, PASSWORD_EMPTY);
         * assertFalse(returnedForm.isValid());
         */
    }

    @Test
    public void testCreateAndReturnInvalidLoginAttemptsObj() {
    }

    @Ignore
    private static User createUser(boolean isActive) {
        User user = new User();
        user.setUsername(USERNAME_VALID);
        user.setUserId(1);
        user.setActive(isActive);
        return user;
    }

    @Ignore
    private static UserInvalidLoginAttempts createUserInvalidLoginAttempts(
            Integer invalidAttempts) {
        UserInvalidLoginAttempts userInvalidLoginAttempts = new UserInvalidLoginAttempts();
        userInvalidLoginAttempts.setUserId(2);
        userInvalidLoginAttempts.setAttempts(invalidAttempts);
        return userInvalidLoginAttempts;
    }
}
