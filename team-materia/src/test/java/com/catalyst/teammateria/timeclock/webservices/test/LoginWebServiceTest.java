package com.catalyst.teammateria.timeclock.webservices.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.formbeans.LoginForm;
import com.catalyst.teammateria.timeclock.services.LoginService;
import com.catalyst.teammateria.timeclock.webservices.LoginWebService;

@RunWith (MockitoJUnitRunner.class)
public class LoginWebServiceTest {

    @InjectMocks
    LoginWebService target = new LoginWebService();

    @Mock
    LoginService loginService;

    @Mock
    UserDao userDao;

    @Mock
    User user;

    @Mock
    MockHttpSession session;

    @Test
    public void setLoginServiceTest() {
        target.setLoginService(loginService);
    }

    @Test
    public void setUserDaoTest() {
        target.setUserDao(userDao);
    }

    @Test
    public void loginPostValidTest() {
        LoginForm loginForm = new LoginForm();
        loginForm.setValid(true);
        when(loginService.validateUser(anyString(), anyString())).thenReturn(
                loginForm);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        target.loginPost(session, anyString(), anyString());
        verify(session).setAttribute(eq("currentUser"), eq(user));
    }

    @Test
    public void loginPostInvalidTest() {
        LoginForm loginForm = new LoginForm();
        loginForm.setValid(false);
        when(loginService.validateUser(anyString(), anyString())).thenReturn(
                loginForm);
        when(userDao.getUserByUsername(anyString())).thenReturn(user);
        target.loginPost(session, anyString(), anyString());
        verify(session, never()).setAttribute(eq("currentUser"), eq(user));
    }
}
