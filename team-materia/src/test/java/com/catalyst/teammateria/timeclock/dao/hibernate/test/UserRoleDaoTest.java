package com.catalyst.teammateria.timeclock.dao.hibernate.test;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.UserRole;
import com.catalyst.teammateria.timeclock.dao.hibernate.UserRoleDaoHibernate;

@RunWith (MockitoJUnitRunner.class)
public class UserRoleDaoTest {

    private static final int USER_ROLE_INDEX = 1;

    @InjectMocks
    UserRoleDaoHibernate target = new UserRoleDaoHibernate();

    @Mock
    protected EntityManager em;

    @Test
    public void testGetUserRoleById() {
        UserRole expectedRole = new UserRole();
        Mockito.when(em.find(UserRole.class, USER_ROLE_INDEX)).thenReturn(
                expectedRole);
        UserRole testRole = target.getUserRoleById(USER_ROLE_INDEX);
        assertEquals(expectedRole, testRole);
    }
}
