package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.TimeManagementController;

@RunWith (MockitoJUnitRunner.class)
public class TimeManagementControllerTest {
    private static final String TIME_MANAGEMENT_TITLE = "timemanagement";
    @InjectMocks
    TimeManagementController target;
    
    @Test
    public void testTimeTrackingGET(){
        ModelAndView mv = target.timeManagementGET();
        assertEquals(mv.getViewName(), TIME_MANAGEMENT_TITLE);
    }
}
