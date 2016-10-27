package com.catalyst.teammateria.timeclock.webservices.test;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;

import com.catalyst.teammateria.timeclock.businesslayer.model.User;
import com.catalyst.teammateria.timeclock.dao.UserDao;
import com.catalyst.teammateria.timeclock.formbeans.AccountConfigForm;
import com.catalyst.teammateria.timeclock.services.AccountConfigService;
import com.catalyst.teammateria.timeclock.webservices.AccountConfigWebService;

@RunWith (MockitoJUnitRunner.class)
public class AccountConfigWebServiceTest {

    @InjectMocks
    AccountConfigWebService target = new AccountConfigWebService();
    @Mock
    AccountConfigService    accountConfigService;
    @Mock
    AccountConfigForm       accountConfigForm;
    @Mock
    UserDao                 userDao;
    @Mock
    User                    user;
    @Mock
    MockHttpSession         session;

    @Test
    public void setAccountConfigServiceTest() {
        target.setAccountConfigService(accountConfigService);
    }

    @Test
    public void getUserListTest() {
        target.getUserList(anyBoolean());
        verify(accountConfigService).getUserList(anyBoolean());
    }

    @Test
    public void getUserTest() {
        target.getUser(anyInt());
        verify(accountConfigService).getUserById(anyInt());
    }

    @Test
    public void updateUserTest() {
        target.updateUser(accountConfigForm);
        verify(accountConfigService).updateUser(accountConfigForm);
    }

    @Test
    public void checkUsernameTest() {
        target.checkUsername(anyInt(), anyString());
        verify(accountConfigService).checkUsername(anyInt(), anyString());
    }

    @Test
    public void checkEmailTest() {
        target.checkEmail(anyInt(), anyString());
        verify(accountConfigService).checkEmail(anyInt(), anyString());
    }
}
