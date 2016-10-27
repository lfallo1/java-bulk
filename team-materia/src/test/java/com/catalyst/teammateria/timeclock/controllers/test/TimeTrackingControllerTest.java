package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.TimeTrackingController;

@RunWith (MockitoJUnitRunner.class)
public class TimeTrackingControllerTest {
    
    private static final String TIMETRACKING_TITLE = "timetracking";
    @InjectMocks
    TimeTrackingController target;
    
    @Test
    public void testTimeTrackingGET(){
        ModelAndView mv = target.timeTrackingGET();
        assertEquals(mv.getViewName(), TIMETRACKING_TITLE);
    }
}
