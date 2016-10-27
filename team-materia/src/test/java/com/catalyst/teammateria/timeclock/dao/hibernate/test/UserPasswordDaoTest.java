package com.catalyst.teammateria.timeclock.dao.hibernate.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserPassword;
import com.catalyst.teammateria.timeclock.dao.hibernate.UserPasswordDaoHibernate;

@RunWith (MockitoJUnitRunner.class)
public class UserPasswordDaoTest {

    @InjectMocks
    UserPasswordDaoHibernate target = new UserPasswordDaoHibernate();

    @Mock
    protected EntityManager em;

    @Mock
    TypedQuery<String> typedQueryUserPass;

    @Mock
    Query query;

    UserPassword userPass = new UserPassword();

    @Test
    public void setEmTest() {
        target.setEm(em);
    }

    @Test
    public void addUserPasswordTest() {
        target.addUserPassword(userPass);
        verify(em, times(1)).persist(userPass);
    }

    @Test
    public void updateUserTest() {
        target.updateUserPassword(userPass);
        verify(em, times(1)).merge(userPass);
    }

    @Test
    public void userPasswordMatch() {
        Integer userId = 1;
        String userHash = "testUserHashValue";
        when(em.createQuery(anyString(), eq(String.class))).thenReturn(
                typedQueryUserPass);
        when(typedQueryUserPass.setParameter(anyString(), anyString()))
                .thenReturn(typedQueryUserPass);
        when(typedQueryUserPass.getSingleResult()).thenReturn(userHash);
        target.userPasswordMatch(userId, userHash);
        verify(typedQueryUserPass, times(1)).setParameter(eq("userId"),
                eq(userId));
        verify(typedQueryUserPass, times(1)).setParameter(eq("userHash"),
                eq(userHash));
    }

    @Test
    public void userPasswordMatchReturnNull() {
        Integer userId = 1;
        String userHash = "testUserHashValue";
        when(em.createQuery(anyString(), eq(String.class))).thenReturn(
                typedQueryUserPass);
        when(typedQueryUserPass.setParameter(anyString(), anyString()))
                .thenReturn(typedQueryUserPass);
        when(typedQueryUserPass.getSingleResult()).thenReturn(null);
        target.userPasswordMatch(userId, userHash);
        verify(typedQueryUserPass, times(1)).setParameter(eq("userId"),
                eq(userId));
        verify(typedQueryUserPass, times(1)).setParameter(eq("userHash"),
                eq(userHash));
    }
}
