package com.catalyst.teammateria.timeclock.filters;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import com.catalsyt.teammateria.timeclock.filters.AuthenticationFilter;
import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;

@RunWith (MockitoJUnitRunner.class)
public class AuthenticationFilterTest {

    private static final String URI_CSS = "css";

    private static final String URI_REGISTER = "/register";

    private static final String URI_ADMINSPLASH = "/adminsplash";

    private static final String ROLE_OTHER = "Other";

    private static final String ROLE_USER = "User";

    private static final String SESSION_KEY_CURRENT_USER = "currentUser";

    private static final String ROLE_ADMIN = "Admin";

    private static final String URI_TIMETRACKING = "/timetracking";

    @InjectMocks
    AuthenticationFilter target = new AuthenticationFilter();

    @Mock
    FilterChain chain;

    User user;

    @Mock
    UserRole userRole;

    @Mock
    ServletRequest servletRequest;

    @Mock
    ServletResponse servletResponse;

    @Mock
    HttpSession session;

    @Mock
    ArrayList<String> adminIsRestrictedList;

    @Test
    public void doFilterStaticRequestsTest() throws IOException,
            ServletException {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        httpServletRequest.setRequestURI(URI_CSS);
        httpServletRequest.setSession(new MockHttpSession());
        target.doFilter(httpServletRequest, httpServletResponse, chain);
        verify(chain, times(1)).doFilter(httpServletRequest,
                httpServletResponse);
        assertEquals(httpServletRequest.getRequestURI(), URI_CSS);
    }

    @Test
    public void doFilterRegisterRequestsTest() throws IOException,
            ServletException {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        httpServletRequest.setRequestURI(URI_REGISTER);
        httpServletRequest.setSession(new MockHttpSession());
        target.doFilter(httpServletRequest, httpServletResponse, chain);
        verify(chain, times(1)).doFilter(httpServletRequest,
                httpServletResponse);
        assertEquals(httpServletRequest.getRequestURI(), URI_REGISTER);
    }

    @Test
    public void doFilterNonStaticRequestsNotLoggedInTest() throws IOException,
            ServletException {
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        session.setAttribute(SESSION_KEY_CURRENT_USER, null);
        target.doFilter(httpServletRequest, httpServletResponse, chain);
    }

    @Test
    public void doFilterUserLoggedIn() throws IOException, ServletException {
        session.setAttribute(SESSION_KEY_CURRENT_USER, user);
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        session = httpServletRequest.getSession();
        session.setAttribute(SESSION_KEY_CURRENT_USER, createUser(1, ROLE_USER));
        FilterConfig fConfig = new MockFilterConfig();
        target.init(fConfig);
        target.doFilter(httpServletRequest, httpServletResponse, chain);
        verify(chain, times(1)).doFilter(httpServletRequest,
                httpServletResponse);
    }

    @Test
    public void doFilterAdminLoggedIn() throws IOException, ServletException {
        session.setAttribute(SESSION_KEY_CURRENT_USER, user);
        HttpServletRequest httpServletRequest = new MockHttpServletRequest();
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();
        session = httpServletRequest.getSession();
        session.setAttribute(SESSION_KEY_CURRENT_USER,
                createUser(2, ROLE_ADMIN));
        FilterConfig fConfig = new MockFilterConfig();
        target.init(fConfig);
        target.doFilter(httpServletRequest, httpServletResponse, chain);
        verify(chain, times(1)).doFilter(httpServletRequest,
                httpServletResponse);
    }

    @Test
    public void doFilterOtherLoggedIn() throws IOException, ServletException {
        session.setAttribute(SESSION_KEY_CURRENT_USER, user);
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        session = httpServletRequest.getSession();
        session.setAttribute(SESSION_KEY_CURRENT_USER,
                createUser(3, ROLE_OTHER));
        FilterConfig fConfig = new MockFilterConfig();
        target.init(fConfig);
        target.doFilter(httpServletRequest, httpServletResponse, chain);
        verify(chain, times(1)).doFilter(httpServletRequest,
                httpServletResponse);
    }

    @Test
    public void doFilterUserRestricted() throws IOException, ServletException {
        session.setAttribute(SESSION_KEY_CURRENT_USER, user);
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        session = httpServletRequest.getSession();
        session.setAttribute(SESSION_KEY_CURRENT_USER, createUser(1, ROLE_USER));
        httpServletRequest.setRequestURI(URI_ADMINSPLASH);
        FilterConfig fConfig = new MockFilterConfig();
        target.init(fConfig);
        target.doFilter(httpServletRequest, httpServletResponse, chain);
    }

    @Test
    public void doFilterAdminRestricted() throws IOException, ServletException {
        session.setAttribute(SESSION_KEY_CURRENT_USER, user);
        MockHttpServletRequest httpServletRequest = new MockHttpServletRequest();
        MockHttpServletResponse httpServletResponse = new MockHttpServletResponse();
        session = httpServletRequest.getSession();
        session.setAttribute(SESSION_KEY_CURRENT_USER,
                createUser(2, ROLE_ADMIN));
        httpServletRequest.setRequestURI(URI_TIMETRACKING);
        FilterConfig fConfig = new MockFilterConfig();
        target.init(fConfig);
        target.doFilter(httpServletRequest, httpServletResponse, chain);
    }

    @Test
    public void destroyTest() {
        target.destroy();
    }

    @Test
    public void initTest() throws ServletException {
        FilterConfig fConfig = new MockFilterConfig();
        target.init(fConfig);
    }

    @Ignore
    private User createUser(Integer id, String role) {
        User user = new User();
        user.setRole(createUserRole(id, role));
        return user;
    }

    @Ignore
    private static UserRole createUserRole(Integer id, String role) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(id);
        userRole.setUserRole(role);
        return userRole;
    }
}
