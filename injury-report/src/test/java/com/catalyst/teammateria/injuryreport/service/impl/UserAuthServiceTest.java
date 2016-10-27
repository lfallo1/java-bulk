package com.catalyst.teammateria.injuryreport.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.User;

public class UserAuthServiceTest {

    private UserAuthServiceImpl target;

    private UserDao userDao;

    @Before
    public void setup() {
        target = new UserAuthServiceImpl();
        userDao = mock(UserDao.class);
        target.setUserDao(userDao);
    }

    @Test
    public void userHasIdTest() {
        Employee expectedEmployee = new Employee();
        expectedEmployee.setEmployeeId(1);
        User expectedUser = new User();
        expectedUser.setEmployee(expectedEmployee);
        when(userDao.getUserByUsername("username")).thenReturn(expectedUser);
        assertTrue(target.userHasId("username", 1));
        assertFalse(target.userHasId("username", 2));
    }
}
