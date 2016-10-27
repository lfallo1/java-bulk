package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.RegisterController;

public class RegisterControllerTest {

    private RegisterController  target        = new RegisterController();
    private static final String REGISTER_VIEW = "register";

    @Test
    public void testRegisterGET() {
        String expectedViewName = REGISTER_VIEW;
        ModelAndView mv = target.registerGET();
        assertEquals(expectedViewName, mv.getViewName());
    }
}
