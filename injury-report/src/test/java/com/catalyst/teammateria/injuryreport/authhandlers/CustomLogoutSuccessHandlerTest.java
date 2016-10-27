package com.catalyst.teammateria.injuryreport.authhandlers;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;

public class CustomLogoutSuccessHandlerTest {

    private static final String URI_CONFIRMATION = "/confirmation";

    private static final String URI_LOGIN = "/login";

    private CustomLogoutSuccessHandler target;

    @Before
    public void setup() {
        target = new CustomLogoutSuccessHandler();
    }

    @Test
    public void onLogoutSuccessTest() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        Authentication authentication = mock(Authentication.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        try {
            when(request.getSession()).thenReturn(session);
            when(session.getAttribute(anyString())).thenReturn(1);
            when(request.getRequestDispatcher(anyString())).thenReturn(
                    dispatcher);
            target.onLogoutSuccess(request, response, authentication);
            verify(request).getRequestDispatcher(URI_CONFIRMATION);
            when(session.getAttribute(anyString())).thenReturn(null);
            target.onLogoutSuccess(request, response, authentication);
            verify(request).getRequestDispatcher(URI_LOGIN);
        } catch (IOException e) {
            assert false;
        } catch (ServletException e) {
            assert false;
        }
    }
}
