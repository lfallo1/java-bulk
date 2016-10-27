package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.AdminReviewController;

public class AdminReviewControllerTest {

    private AdminReviewController target;

    @Before
    public void setup() {
        target = new AdminReviewController();
    }
    
    @Test
    public void getAdminReviewTest(){
        ModelAndView mv = target.getAdminReview();
        assertEquals("adminreview", mv.getViewName());
    }
}
