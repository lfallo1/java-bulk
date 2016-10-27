package com.catalyst.teammateria.timeclock.controllers.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import com.catalyst.teammateria.timeclock.controllers.AccountConfigController;

@RunWith (MockitoJUnitRunner.class)
public class AccountConfigControllerTest {

    @InjectMocks
    AccountConfigController     target;
    private static final String ACCOUNT_CONFIG_VIEW_NAME = "accountconfig";

    @Test
    public void testAccountConfigGET() {
        ModelAndView mv = target.accountConfigGET();
        assertEquals(mv.getViewName(), ACCOUNT_CONFIG_VIEW_NAME);
    }
}
