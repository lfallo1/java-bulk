package com.catalyst.teammateria.timeclock.webservices.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.timeclock.formbeans.RegistrationForm;
import com.catalyst.teammateria.timeclock.services.RegisterService;
import com.catalyst.teammateria.timeclock.webservices.RegisterWebService;

@RunWith (MockitoJUnitRunner.class)
public class RegisterWebServiceTest {

    @InjectMocks
    RegisterWebService target = new RegisterWebService();

    @Mock
    RegisterService regService;

    RegistrationForm form;

    @Test
    public void setServiceTest() {
        target.setService(regService);
    }

    @Test
    public void addUser() {
        target.addUser(form);
        verify(regService).add(form);
    }

    @Test
    public void getUsernameAvailTest() {
        target.getUsernameAvail(anyString());
        verify(regService).checkUsername(anyString());
    }

    @Test
    public void getEmailAvailTest() {
        target.getEmailAvail(anyString());
        verify(regService).checkEmail(anyString());
    }
}
