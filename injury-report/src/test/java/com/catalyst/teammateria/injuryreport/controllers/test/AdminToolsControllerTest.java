package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.AdminToolsController;

public class AdminToolsControllerTest {

    private AdminToolsController target;

    @Before
    public void setup() {
        target = new AdminToolsController();
    }

    @Test
    public void testAdminToolsGET() {
        ModelAndView mv = target.adminToolsGET();
        assertEquals("admintools", mv.getViewName());
    }
}
