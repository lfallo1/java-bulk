package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.LogoutController;

@RunWith (MockitoJUnitRunner.class)
public class LogoutControllerTest {
    private static final String REDIRECT_TITLE = "redirect:/";

    LogoutController target = new LogoutController();   
    
    @Mock
    HttpSession session;
    
    @Test
    public void testLogoutGET(){
        ModelAndView mv = target.logoutGET(session);
        assertEquals(REDIRECT_TITLE, mv.getViewName());
    }
}
