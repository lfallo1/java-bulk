package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.controllers.MainController;

@RunWith (MockitoJUnitRunner.class)
public class MainControllerTest {

    private static final String USER_USER_ROLE = "User";

    private static final String ADMIN_USER_ROLE = "Admin";

    @InjectMocks
    MainController target;

    @Mock
    HttpSession session;

    private static final String REDIRECT_TITLE = "redirect:";

    private static final String USER_ROLE_NAME_EMPTY = "";

    @Test
    public void testMainGET() {
        // Return an empty user role when requesting it from the filter
        User expectedUser = new User();
        UserRole expectedUserRole = new UserRole();
        expectedUserRole.setUserRole(USER_ROLE_NAME_EMPTY);
        expectedUser.setRole(expectedUserRole);
        Mockito.when(session.getAttribute(Mockito.anyString())).thenReturn(
                expectedUser);
        // Call the get method and assert that a simple redirect occurs
        ModelAndView mv = target.mainGET(session);
        assertEquals(mv.getViewName(), REDIRECT_TITLE);
        // Call the get method under an admin role, assert redirect to admin
        // splash page
        expectedUserRole.setUserRole(ADMIN_USER_ROLE);
        mv = target.mainGET(session);
        assertEquals(mv.getViewName(), REDIRECT_TITLE + "/adminsplash");
        // Call the get method under a user role, assert a redirect to
        // timetracking page
        expectedUserRole.setUserRole(USER_USER_ROLE);
        mv = target.mainGET(session);
        assertEquals(mv.getViewName(), REDIRECT_TITLE + "/timetracking");
    }
}
