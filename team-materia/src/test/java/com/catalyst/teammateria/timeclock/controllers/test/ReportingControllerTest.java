package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.ReportingController;

@RunWith (MockitoJUnitRunner.class)
public class ReportingControllerTest {

    @InjectMocks
    ReportingController         target;
    private static final String REPORTING_VIEW_NAME = "reporting";

    @Test
    public void testReportingGET() {
        ModelAndView mv = target.reportingGET();
        assertEquals(mv.getViewName(), REPORTING_VIEW_NAME);
    }
}
