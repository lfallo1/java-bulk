package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
//import org.jboss.seam.mock.EnhancedMockHttpServletRequest;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.ConfirmationController;

public class ConfirmationControllerTest {

    private ConfirmationController target;

    private HttpServletRequest request;

    @Before
    public void setup() {
        target = new ConfirmationController();
        request = mock(HttpServletRequest.class);
    }

    @Test
    public void testReviewGET() {
        HttpSession expectedSession = mock(HttpSession.class);
        when(request.getSession()).thenReturn(expectedSession);
        ModelAndView mvLogout = target.reviewGET(request, 1);
        assertEquals(mvLogout.getViewName(), "redirect:/logout");
    }

    @Test
    public void testConfirmationGET() {
        ModelAndView mvRedirect = target.confirmationGET(request);
        assertEquals("redirect:/", mvRedirect.getViewName());
        when(request.getAttribute(anyString())).thenReturn("foo");
        ModelAndView rvConfirmation = target.confirmationGET(request);
        assertEquals("confirmation", rvConfirmation.getViewName());
    }
}
