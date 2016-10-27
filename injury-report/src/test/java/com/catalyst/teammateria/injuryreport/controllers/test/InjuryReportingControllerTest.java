package com.catalyst.teammateria.injuryreport.controllers.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.injuryreport.controllers.InjuryReportingController;
import com.catalyst.teammateria.injuryreport.service.UserAuthService;

@RunWith (MockitoJUnitRunner.class)
public class InjuryReportingControllerTest {

    @InjectMocks
    private InjuryReportingController target;

    @Mock
    private UserAuthService userAuthService;

    @Test
    public void testReportingGET() {        
        HttpServletRequest request = mock(HttpServletRequest.class);
        Principal principal = mock(Principal.class);
        when(request.getUserPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn("foo");
        // Return a valid username and id match
        when(userAuthService.userHasId("foo", 1)).thenReturn(true);
        ModelAndView mvRedirect = target.reportingGET(request, 1, "Test",
                "Test2");
        // Assert that a redirect to an injured employee occurred
        assertEquals("redirect:/injuredemployee", mvRedirect.getViewName());
        // Return an invalid username and id match
        when(userAuthService.userHasId("foo", 1)).thenReturn(false);
        ModelAndView mvNoRedirect = target.reportingGET(request, 1, "Test",
                "Test2");
        // Assert that a redirect to an injured employee did not occur
        assertEquals("injuryreporting", mvNoRedirect.getViewName());
    }
}
