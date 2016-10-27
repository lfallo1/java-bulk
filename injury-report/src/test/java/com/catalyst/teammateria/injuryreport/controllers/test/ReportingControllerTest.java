package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.ReportingController;

public class ReportingControllerTest {

    private ReportingController target;
    
    @Before
    public void setup(){
        target = new ReportingController();
    }
    
    @Test
    public void reportingGETTest(){
        ModelAndView mv = target.reportingGET();
        assertEquals("reporting", mv.getViewName());
    }
    
}
