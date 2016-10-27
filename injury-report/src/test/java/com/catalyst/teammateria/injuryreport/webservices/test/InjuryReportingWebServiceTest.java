package com.catalyst.teammateria.injuryreport.webservices.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.InjuryReportingService;
import com.catalyst.teammateria.injuryreport.webservices.InjuryReportingWebService;

@RunWith (MockitoJUnitRunner.class)
public class InjuryReportingWebServiceTest {

    private static final String EXPECTED_USERNAME = "username";

    @InjectMocks
    private InjuryReportingWebService target;

    @Mock
    private InjuryReportingService injuryReportingService;
    
    @Mock
    private InjuryReportBean injuryReportBean;

    @Test
    public void getWeatherListTest() {
        // Return the expected weather list when requested from
        // injuryReportingService
        List<Weather> expectedWeatherList = new ArrayList<Weather>();
        expectedWeatherList.add(new Weather());
        when(injuryReportingService.getWeatherForSelect()).thenReturn(
                expectedWeatherList);
        // Assert that the expected weather list was returned
        List<Weather> actualWeatherList = target.getWeatherList();
        assertEquals(expectedWeatherList, actualWeatherList);
    }

    @Test
    public void getnjuryTypeListTest() {
        // Return the expected injury type list when requested from service
        List<InjuryType> expectedInjuryTypeList = new ArrayList<InjuryType>();
        expectedInjuryTypeList.add(new InjuryType());
        when(injuryReportingService.getInjuryTypeForSelect()).thenReturn(
                expectedInjuryTypeList);
        // Assert that the expected list was returned
        List<InjuryType> returnedInjuryTypeList = target.getInjuryTypeList();
        assertEquals(expectedInjuryTypeList, returnedInjuryTypeList);
    }

    @Test
    public void getBodyPartListTest() {
        // Return the expected bodypart list when requested from service
        List<BodyPart> expectedBodyParts = new ArrayList<BodyPart>();
        expectedBodyParts.add(new BodyPart());
        when(injuryReportingService.getBodyPartForSelect()).thenReturn(
                expectedBodyParts);
        // Assert that the expected list was returned
        List<BodyPart> returnedBodyParts = target.getBodyPartList();
        assertEquals(expectedBodyParts, returnedBodyParts);
    }

    @Test
    public void getUserTest() {
        // Return the expected username when requested from the httpservlet
        // request
        HttpServletRequest request = mock(HttpServletRequest.class);
        Principal expectedPrincipal = mock(Principal.class);
        when(request.getUserPrincipal()).thenReturn(expectedPrincipal);
        when(expectedPrincipal.getName()).thenReturn(EXPECTED_USERNAME);
        // Return the expected user when getting user details from the
        // injuryReportingService
        User expectedUser = new User();
        expectedUser.setUserName(EXPECTED_USERNAME);
        when(injuryReportingService.getUserDetails(EXPECTED_USERNAME))
                .thenReturn(expectedUser);
        // Assert that the expected user was returned
        User returnedUser = target.getUser(request);
        assertEquals(returnedUser, expectedUser);
    }
    
    @Test
    public void addUserTest(){
        target.addReport(injuryReportBean);
        verify(injuryReportingService, times(1)).addInjuryReport(injuryReportBean);
    }
}
