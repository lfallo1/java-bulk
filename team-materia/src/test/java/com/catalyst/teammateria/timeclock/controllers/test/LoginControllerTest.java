package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.controllers.LoginController;

@RunWith (MockitoJUnitRunner.class)
public class LoginControllerTest {

    private static final String CURRENT_USER_VIEW_NAME = "currentUser";
    
    private LoginController target = new LoginController();
    
    private static final String LOGIN_VIEW = "login";

    private static final String REDIRECT_NAME = "redirect:/";        

    @Mock
    HttpSession session;

    @Test
    public void testLoginGET() {
        ModelAndView mv = target.loginGET(session);
        assertEquals(mv.getViewName(), LOGIN_VIEW);
    }

    @Test
    public void testLoginGET2() {
        User user = new User();
        Mockito.when(session.getAttribute(CURRENT_USER_VIEW_NAME)).thenReturn(
                user);
        ModelAndView mv = target.loginGET(session);
        assertEquals(mv.getViewName(), REDIRECT_NAME);
    }

    @Test
    public void testLoginSuccessGET() {
        ModelAndView mv = target.loginSuccessGET();
        assertEquals(mv.getViewName(), REDIRECT_NAME);
    }
}
