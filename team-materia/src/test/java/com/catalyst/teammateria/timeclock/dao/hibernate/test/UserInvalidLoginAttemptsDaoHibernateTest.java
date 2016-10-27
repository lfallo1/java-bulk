package com.catalyst.teammateria.timeclock.dao.hibernate.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.businesslayer.model.UserInvalidLoginAttempts;
import com.catalyst.teammateria.timeclock.dao.hibernate.UserInvalidLoginAttemptsDaoHibernate;

@RunWith (MockitoJUnitRunner.class)
public class UserInvalidLoginAttemptsDaoHibernateTest {

    @InjectMocks
    UserInvalidLoginAttemptsDaoHibernate target;

    @Mock
    EntityManager em;

    @Mock
    UserInvalidLoginAttempts attempt;

    @Mock
    TypedQuery<UserInvalidLoginAttempts> typedQueryLoginAttempt;

    @Test
    public void testGetUserInvalidLoginAttemptsByUser() {
        User expectedUser = new User();
        when(em.createQuery(anyString(), eq(UserInvalidLoginAttempts.class)))
                .thenReturn(typedQueryLoginAttempt);
        when(typedQueryLoginAttempt.setParameter(anyString(), anyString()))
                .thenReturn(typedQueryLoginAttempt);
        target.getUserInvalidLoginAttemptsByUser(expectedUser);
        verify(typedQueryLoginAttempt).setParameter(anyString(),
                eq(expectedUser.getUserId()));
    }

    @Test
    public void testAddUserInvalidLoginAttempts() {
        target.addUserInvalidLoginAttempts(attempt);
        verify(em, times(1)).persist(attempt);
    }

    @Test
    public void testUpdateUserInvalidLoginAttempts() {
        target.updateUserInvalidLoginAttempts(attempt);
        verify(em, times(1)).merge(attempt);
    }

    @Test
    public void testDeleteUserInvalidLoginAttempts() {
        // When the user exists, the invalid attempt should be removed
        when(em.contains(attempt)).thenReturn(true);
        target.deleteUserInvalidLoginAttempts(attempt);
        verify(em, times(1)).remove(attempt);
        // When the user does not exist, the invalid attempt should be both
        // merged and removed
        when(em.contains(attempt)).thenReturn(false);
        target.deleteUserInvalidLoginAttempts(attempt);
        verify(em, times(1)).remove(attempt);
        verify(em, times(1)).merge(Mockito.any(Object.class));
    }
}
