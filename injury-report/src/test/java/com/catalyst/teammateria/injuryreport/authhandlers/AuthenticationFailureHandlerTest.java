package com.catalyst.teammateria.injuryreport.authhandlers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.AuthenticationException;

public class AuthenticationFailureHandlerTest {

    private static final String KEY_ERROR_TYPE = "error_type";

    private static final String BAD_CREDENTIALS_ERROR = "Bad credentials";

    private static final String USER_IS_DISABLED_ERROR = "User is disabled";

    private AuthenticationFailureHandler target;

    @Before
    public void setup() {
        target = new AuthenticationFailureHandler();
    }

    @Test
    public void onAuthenticationFailureTest() {
        HttpSession session = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        AuthenticationException exception = mock(AuthenticationException.class);
        try {
            when(request.getSession()).thenReturn(session);
            when(request.getRequestDispatcher(anyString())).thenReturn(
                    dispatcher);
            // First branch
            target.onAuthenticationFailure(request, response, exception);
            verify(session).setAttribute(KEY_ERROR_TYPE, BAD_CREDENTIALS_ERROR);
            // Second branch
            when(exception.getMessage()).thenReturn(USER_IS_DISABLED_ERROR);
            target.onAuthenticationFailure(request, response, exception);
        } catch (IOException e) {
            assert false;
        } catch (ServletException e) {
            assert false;
        }
    }
}
