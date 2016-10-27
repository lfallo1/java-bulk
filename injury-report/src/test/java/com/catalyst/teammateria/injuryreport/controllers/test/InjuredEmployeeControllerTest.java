package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.InjuredEmployeeController;

public class InjuredEmployeeControllerTest {

    private InjuredEmployeeController target;

    @Before
    public void setup() {
        target = new InjuredEmployeeController();
    }
    
    @Test
    public void testInjuredEmployeeGET(){
        ModelAndView mv = target.injuredEmployeeGET();
        assertEquals(mv.getViewName(), "injuredemployee");
    }
}
