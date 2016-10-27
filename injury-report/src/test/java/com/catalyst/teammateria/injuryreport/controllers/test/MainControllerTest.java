package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
//import org.jboss.seam.mock.EnhancedMockHttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.MainController;

@RunWith (MockitoJUnitRunner.class)
public class MainControllerTest {

    private MainController target; // = new MainController();

    private HttpServletRequest request;

    @Before
    public void setup() {
        target = new MainController();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void testMainGET() {
        ModelAndView mvAdmin = target.mainGET(request);
        assertEquals(mvAdmin.getViewName(), "adminsplash");
        when(request.isUserInRole(anyString())).thenReturn(true);
        ModelAndView mvInjuryReporting = target.mainGET(request);
        assertEquals(mvInjuryReporting.getViewName(), "injuredemployee");
    }
}
