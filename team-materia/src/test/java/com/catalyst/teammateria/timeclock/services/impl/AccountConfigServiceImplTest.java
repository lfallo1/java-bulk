package com.catalyst.teammateria.timeclock.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.dao.UserPasswordDao;
import com.catalyst.teammateria.timeclock.formbeans.AccountConfigForm;
import com.catalyst.teammateria.timeclock.servicesimpl.AccountConfigServiceImpl;

@RunWith (MockitoJUnitRunner.class)
public class AccountConfigServiceImplTest {

    private static final String HASH_VALID = "WL3LsY46yfJPecGatLRwtGMh6Cs8xNYkdxKwRBQk+7E=";

    private static final String PASSWORD_VALID = "password89";

    @InjectMocks
    private AccountConfigServiceImpl target;

    @Mock
    private UserDao userDao;

    @Mock
    private UserPasswordDao userPasswordDao;

    @Test
    public void getUserListTest() {
        // Return expectedListTrue when requesting list with inactive users
        ArrayList<User> expectedListTrue = new ArrayList<User>();
        when(userDao.getUsersForSelect(true)).thenReturn(expectedListTrue);
        assertEquals(expectedListTrue, target.getUserList(true));
        // Return expectedListFalse when requesting list with inactive users
        ArrayList<User> expectedListFalse = new ArrayList<User>();
        when(userDao.getUsersForSelect(false)).thenReturn(expectedListFalse);
        assertEquals(expectedListFalse, target.getUserList(false));
    }

    @Test
    public void getUserByIdTest() {
        User expectedUser = new User();
        when(userDao.getUserById(1)).thenReturn(expectedUser);
        assertEquals(expectedUser, target.getUserById(1));
        target.getUserById(1);
    }

    @Test
    public void updateUserTestInvalidUser() {
        AccountConfigForm accountConfigForm = new AccountConfigForm();
        accountConfigForm.setPassword("foo");
        User user = validUser();
        accountConfigForm.setUser(user);
        String returnedErrors = target.updateUser(accountConfigForm);
        assertTrue(returnedErrors.length() > 0);
    }

    @Test
    public void updateUserTestValidUser() {
        // Return valid usernames and emails from database
        when(userDao.userNameAvailable(Mockito.anyString())).thenReturn(true);
        when(
                userDao.usernameRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(true);
        when(userDao.emailAvailable(Mockito.anyString())).thenReturn(true);
        when(
                userDao.emailRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(true);
        // Init the AccountConfigForm
        AccountConfigForm accountConfigForm = new AccountConfigForm();
        UserPassword pass = new UserPassword();
        pass.setUserHash(HASH_VALID);
        pass.setUserId(1);
        accountConfigForm.setPassword(PASSWORD_VALID);
        accountConfigForm.setUser(validUser());
        //
        String valid = target.updateUser(accountConfigForm);
        assertEquals("true", valid);
        accountConfigForm.setPassword("");
        // Repeat test with empty password
        valid = target.updateUser(accountConfigForm);
        assertEquals("true", valid);
    }

    @Test
    public void validateValidTest() {
        when(userDao.userNameAvailable(Mockito.anyString())).thenReturn(true);
        when(
                userDao.usernameRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(true);
        when(userDao.emailAvailable(Mockito.anyString())).thenReturn(true);
        when(
                userDao.emailRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(true);
        AccountConfigForm accountConfigForm = new AccountConfigForm();
        User user = validUser();
        UserPassword pass = new UserPassword();
        pass.setUserHash(HASH_VALID);
        pass.setUserId(1);
        accountConfigForm.setPassword(PASSWORD_VALID);
        accountConfigForm.setUser(user);
        String valid = target.validate(accountConfigForm);
        assertTrue(valid.length() == 0);
    }

    @Test
    public void validateInvalidTest() {
        AccountConfigForm accountConfigForm = new AccountConfigForm();
        User user = invalidUser();
        UserPassword pass = new UserPassword();
        pass.setUserId(1);
        accountConfigForm.setPassword("pass");
        accountConfigForm.setUser(user);
        target.validate(accountConfigForm);
        accountConfigForm.setPassword("");
        String valid = target.validate(accountConfigForm);
        assertTrue(valid.length() > 0);
        ;
    }

    @Test
    public void validateUsernameAvailableTest() {
        Mockito.when(userDao.userNameAvailable(Mockito.anyString()))
                .thenReturn(false);
        Mockito.when(
                userDao.usernameRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(true);
        target.validateUsernameAvailable(1, "test");
        target.checkUsername(1, "test");
        when(userDao.userNameAvailable(Mockito.anyString())).thenReturn(false);
        when(
                userDao.usernameRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(false);
        target.validateUsernameAvailable(1, "test");
        target.checkUsername(1, "test");
        when(userDao.userNameAvailable(Mockito.anyString())).thenReturn(true);
        when(
                userDao.usernameRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(false);
        target.validateUsernameAvailable(1, "test");
        target.checkUsername(1, "test");
    }

    @Test
    public void validateEmailAvailableTest() {
        when(userDao.emailAvailable(Mockito.anyString())).thenReturn(false);
        when(
                userDao.emailRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(true);
        target.validateEmailAvailable(1, "test");
        target.checkEmail(1, "test");
        when(userDao.emailAvailable(Mockito.anyString())).thenReturn(false);
        when(
                userDao.emailRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(false);
        target.validateEmailAvailable(1, "test");
        target.checkEmail(1, "test");
        when(userDao.emailAvailable(Mockito.anyString())).thenReturn(true);
        when(
                userDao.emailRemainedTheSame(Mockito.anyInt(),
                        Mockito.anyString())).thenReturn(false);
        target.validateEmailAvailable(1, "test");
        target.checkEmail(1, "test");
    }

    /* Returns a valid user */
    private User validUser() {
        User user = new User();
        user.setFirstName("Alex");
        user.setLastName("Dietrich");
        user.setUserId(1);
        user.setUsername("adietrich");
        user.setEmail("adietrich@test.com");
        return user;
    }

    /* Returns an invalid user */
    private User invalidUser() {
        User user = new User();
        user.setFirstName("1");
        user.setLastName("1");
        user.setUserId(1);
        user.setUsername("1?");
        user.setEmail("adietrichattestcom");
        return user;
    }
}