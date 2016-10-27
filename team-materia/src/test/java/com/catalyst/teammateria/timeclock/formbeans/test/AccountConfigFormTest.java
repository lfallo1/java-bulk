package com.catalyst.teammateria.timeclock.formbeans.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.formbeans.AccountConfigForm;

@RunWith (MockitoJUnitRunner.class)
public class AccountConfigFormTest {

    @InjectMocks
    private AccountConfigForm   target;
    private static final String PASSWORD = "Password0!";
    private User                user;

    @Test
    public void testUser() {
        target.setUser(user);
        assertEquals(target.getUser(), user);
    }

    @Test
    public void testPassword() {
        target.setPassword(PASSWORD);
        assertEquals(PASSWORD, target.getPassword());
    }
}
