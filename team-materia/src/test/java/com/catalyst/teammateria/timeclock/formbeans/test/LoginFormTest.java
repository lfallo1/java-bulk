package com.catalyst.teammateria.timeclock.formbeans.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.formbeans.LoginForm;

@RunWith (MockitoJUnitRunner.class)
public class LoginFormTest {

    @InjectMocks
    private LoginForm target;

    private static final Boolean ACCOUNT_LOCKED = false;

    private static final Boolean IS_VALID = false;   

    @Test
    public void testAccountLocked() {
        target.setAccountLocked(ACCOUNT_LOCKED);
        assertEquals(target.isAccountLocked(), ACCOUNT_LOCKED);
    }

    @Test
    public void testValid() {
        target.setValid(IS_VALID);
        assertEquals(target.isValid(), IS_VALID);
    }
}
