package com.catalyst.teammateria.timeclock.dao.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.dao.hibernate.UserDaoHibernate;

@RunWith (MockitoJUnitRunner.class)
public class UserDaoTest {

    private static final String ALL_ACTIVE_USERS_QUERY  = "SELECT new User(o.firstName, o.lastName, o.userId) FROM User o WHERE o.active = true ";
    private static final String ALL_USERS_QUERY         = "SELECT new User(o.firstName, o.lastName, o.userId) FROM User o ";
    private static final String USERNAME_PARAMETER_NAME = "username";
    private static final String EMAIL_VALID             = "adietrich@catalystitservices.com";
    private static final String EMAIL_INVALID           = "doesntmatter@somewhere.com";
    private static final String EMAIL_PARAMETER_NAME    = "email";
    private static final String USERNAME_VALID          = "adietrich";
    private static final String USERNAME_INVALID        = "invalidusername";
    private static final String ROLE_PARAMETER_NAME     = "role";
    @InjectMocks
    UserDaoHibernate            target;
    @Mock
    protected EntityManager     em;
    @Mock
    TypedQuery<User>            typedQueryUser;
    @Mock
    TypedQuery<Long>            typedQueryLong;
    @Mock
    Query                       query;
    @Mock
    List<User>                  listUser;
    User                        user                    = new User();
    UserRole                    userRole                = new UserRole();

    @Test
    public void setEmTest() {
        target.setEm(em);
    }

    @Test
    public void addUserTest() {
        target.addUser(user);
    }

    @Test
    public void updateUserTest() {
        target.updateUser(user);
    }

    @Test
    public void getAllTest() {
        when(em.createQuery(anyString(), eq(User.class))).thenReturn(
                typedQueryUser);
        when(typedQueryUser.getResultList()).thenReturn(listUser);
        List<User> testUser = target.getAll();
        assertEquals(listUser, testUser);
    }

    @Test
    public void userNameAvailablePassTest() {
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
                typedQueryLong);
        when(
                typedQueryLong.setParameter(USERNAME_PARAMETER_NAME,
                        USERNAME_VALID)).thenReturn(typedQueryLong);
        when(typedQueryLong.getSingleResult()).thenReturn(0L);
        assertTrue(target.userNameAvailable(USERNAME_VALID));
    }

    @Test
    public void userNameAvailableFailTest() throws NoResultException {
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
                typedQueryLong);
        when(
                typedQueryLong.setParameter(USERNAME_PARAMETER_NAME,
                        USERNAME_INVALID)).thenReturn(typedQueryLong);
        when(typedQueryLong.getSingleResult()).thenReturn(10L);
        assertFalse(target.userNameAvailable(USERNAME_INVALID));
    }

    @Test
    public void getUserByUsernameTest() {
        when(em.createQuery(anyString(), eq(User.class))).thenReturn(
                typedQueryUser);
        when(
                typedQueryUser.setParameter(USERNAME_PARAMETER_NAME,
                        USERNAME_VALID)).thenReturn(typedQueryUser);
        when(typedQueryUser.getSingleResult()).thenReturn(user);
        User testUser = target.getUserByUsername(USERNAME_VALID);
        assertEquals(testUser, user);
    }

    @Test
    public void emailAvailablePassTest() {
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
                typedQueryLong);
        when(typedQueryLong.setParameter(EMAIL_PARAMETER_NAME, EMAIL_VALID))
                .thenReturn(typedQueryLong);
        when(typedQueryLong.getSingleResult()).thenReturn(0L);
        assertTrue(target.emailAvailable(EMAIL_VALID));
    }

    // Same as the test above, except with an available email
    @Test
    public void emailAvailableFailTest() {
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
                typedQueryLong);
        when(typedQueryLong.setParameter(EMAIL_PARAMETER_NAME, EMAIL_INVALID))
                .thenReturn(typedQueryLong);
        when(typedQueryLong.getSingleResult()).thenReturn(1L);
        assertFalse(target.emailAvailable(EMAIL_INVALID));
    }

    @Test
    public void getUsersByRoleTest() {
        when(em.createQuery(anyString(), eq(User.class))).thenReturn(
                typedQueryUser);
        when(typedQueryUser.setParameter(ROLE_PARAMETER_NAME, userRole))
                .thenReturn(typedQueryUser);
        when(typedQueryUser.getResultList()).thenReturn(listUser);
        List<User> testUser = target.getUsersByRole(userRole);
        assertEquals(testUser, listUser);
    }

    @Test
    public void getUsersForSelectTest() {
        // Returning all users...
        when(em.createQuery(ALL_USERS_QUERY, User.class)).thenReturn(
                typedQueryUser);
        ArrayList<User> expectedUserListTrue = new ArrayList<User>();
        when(typedQueryUser.getResultList()).thenReturn(expectedUserListTrue);
        assertEquals(target.getUsersForSelect(true), expectedUserListTrue);
        // Returning active only...
        when(em.createQuery(ALL_ACTIVE_USERS_QUERY, User.class)).thenReturn(
                typedQueryUser);
        ArrayList<User> expectedUserListFalse = new ArrayList<User>();
        when(typedQueryUser.getResultList()).thenReturn(expectedUserListFalse);
        assertEquals(target.getUsersForSelect(false), expectedUserListFalse);
    }

    @Test
    public void getUserByIdTest() {
        when(em.find(eq(User.class), Mockito.anyInt())).thenReturn(user);
        User userTest = target.getUserById(1);
        assertEquals(userTest, user);
    }

    @Test
    public void usernameRemainedTheSameTest() {
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
                typedQueryLong);
        when(typedQueryLong.setParameter("userId", 1)).thenReturn(
                typedQueryLong);
        when(typedQueryLong.setParameter("username", USERNAME_VALID))
                .thenReturn(typedQueryLong);
        // Return 1 when getting a single result, assert that method returned
        // true
        when(typedQueryLong.getSingleResult()).thenReturn(1L);
        assertTrue(target.usernameRemainedTheSame(1, USERNAME_VALID));
        // Repeat test returning 0, assert that method returns false
        when(typedQueryLong.getSingleResult()).thenReturn(0L);
        assertFalse(target.usernameRemainedTheSame(1, USERNAME_VALID));
    }

    @Test
    public void emailRemainedTheSameTest() {
        when(em.createQuery(anyString(), eq(Long.class))).thenReturn(
                typedQueryLong);
        when(typedQueryLong.setParameter("userId", 1)).thenReturn(
                typedQueryLong);
        when(typedQueryLong.setParameter("email", EMAIL_VALID)).thenReturn(
                typedQueryLong);
        // Return 1 when getting a single result, assert true return
        when(typedQueryLong.getSingleResult()).thenReturn(1L);
        assertTrue(target.emailRemainedTheSame(1, EMAIL_VALID));
        // Return 0 when getting a single result, assert false return
        when(typedQueryLong.getSingleResult()).thenReturn(0L);
        assertFalse(target.emailRemainedTheSame(1, EMAIL_VALID));
    }

    @Test
    public void getAllSortByUsernameTest() {
        when(em.createQuery(anyString(), eq(User.class))).thenReturn(
                typedQueryUser);
        List<User> expectedList = new ArrayList<User>();
        when(typedQueryUser.getResultList()).thenReturn(expectedList);
        assertEquals(expectedList, target.getAllSortByUsername());
    }
}
