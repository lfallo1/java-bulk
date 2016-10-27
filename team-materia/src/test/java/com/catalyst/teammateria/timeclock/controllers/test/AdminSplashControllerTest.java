package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.AdminSplashController;

@RunWith (MockitoJUnitRunner.class)
public class AdminSplashControllerTest {

    @InjectMocks
    AdminSplashController target;

    private static final String ADMINSPLASH_VIEW_NAME = "adminsplash";

    @Test
    public void testAdminSplashGET() {
        ModelAndView mv = target.adminSplashGET();
        assertEquals(mv.getViewName(), ADMINSPLASH_VIEW_NAME);
    }
}
