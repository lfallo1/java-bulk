package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.LoginController;

@RunWith (MockitoJUnitRunner.class)
public class LoginControllerTest {

    private static final String REDIRECT_TO_HOME_CTRL = "redirect:/";

    private LoginController target = new LoginController();

    @Mock
    HttpServletRequest request;

    @Mock
    HttpSession session;

    // public MockHttpServletRequest request = new MockHttpServletRequest();
    @Test
    public void testLoginGET() {
        // If the request does not have a user, return login page
        ModelAndView mvLogin = target.loginGET(request);
        assertEquals(mvLogin.getViewName(), "login");
        // If the request does have a user, redirect to home
        Principal expectedPrincipal = mock(Principal.class);
        when(request.getUserPrincipal()).thenReturn(expectedPrincipal);
        ModelAndView mvRedirectHome = target.loginGET(request);
        assertEquals(mvRedirectHome.getViewName(), REDIRECT_TO_HOME_CTRL);
    }

    @Test
    public void testLoginPOST() {
        ModelAndView mv = target.loginPOST();
        assertEquals(mv.getViewName(), "login");
    }
}
