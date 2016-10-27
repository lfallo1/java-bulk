package com.catalyst.teammateria.timeclock.filters;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalsyt.teammateria.timeclock.filters.AuthSuccessHandler;
import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;

@RunWith (MockitoJUnitRunner.class)
public class AuthSuccessHandlerTest {
    private static final String EMPTY_TARGET_URL = "";
    private static final String NONEXISTANT_USER_ROLE = "somethingelese";
    private static final String CURRENT_USER_ATTRIBUTE = "currentUser";
    private static final String ADMIN_USER_ROLE = "Admin";
    private static final String USER_USER_ROLE = "User";
    HttpSession session = new AuthSuccessHandlerSessionStub();
    private static final String ADMIN_REDIRECT_TITLE = "/adminsplash";
    private static final String USER_REDIRECT_TITLE = "/timetracking";
    
    @Test
    public void testDetermineTargetUrlAdmin(){
        // Create an expected user and user role
        User expectedUser = new User();        
        UserRole expectedUserRole = new UserRole();
        // Add an admin user to the session
        expectedUserRole.setUserRole(ADMIN_USER_ROLE);
        expectedUser.setRole(expectedUserRole);
        session.setAttribute(CURRENT_USER_ATTRIBUTE, expectedUser);  
        // Assert the admin is redirected to the admin redirect title
        String returnedAdminRedirect = AuthSuccessHandler.determineTargetUrl(session);
        assertEquals(ADMIN_REDIRECT_TITLE, returnedAdminRedirect);        
    }
    
    @Test 
    public void testDetermineTargetUrlUser(){       
        // Create an expected user and user role
        User expectedUser = new User();        
        UserRole expectedUserRole = new UserRole();
        // Add a regular user to the session
        expectedUserRole.setUserRole(USER_USER_ROLE);
        expectedUser.setRole(expectedUserRole);        
        session.setAttribute(CURRENT_USER_ATTRIBUTE, expectedUser);
        // Assert the admin is redirected to the admin redirect title
        String returnedUserRedirect = AuthSuccessHandler.determineTargetUrl(session);
        assertEquals(USER_REDIRECT_TITLE, returnedUserRedirect);       
    }
    
    @Test 
    public void testDetermineTargetUrlOther(){
        // Create an expected user and user role
        User expectedUser = new User();        
        UserRole expectedUserRole = new UserRole();
        // Add a regular user to the session
        expectedUserRole.setUserRole(NONEXISTANT_USER_ROLE);
        expectedUser.setRole(expectedUserRole);        
        session.setAttribute(CURRENT_USER_ATTRIBUTE, expectedUser);
        // Assert the admin is redirected to the admin redirect title
        String returnedRedirect = AuthSuccessHandler.determineTargetUrl(session);        
        assertEquals(EMPTY_TARGET_URL, returnedRedirect);     
    }
    
    @Test
    public void privateConstructorTest() throws Exception {
        final Constructor<?>[] constructors = AuthSuccessHandler.class
                .getDeclaredConstructors();
        constructors[0].setAccessible(true);
        constructors[0].newInstance((Object[])null);
    }
}
