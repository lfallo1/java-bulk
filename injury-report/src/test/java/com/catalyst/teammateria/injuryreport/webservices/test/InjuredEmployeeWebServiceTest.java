package com.catalyst.teammateria.injuryreport.webservices.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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

import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.service.InjuredEmployeeService;
import com.catalyst.teammateria.injuryreport.webservices.InjuredEmployeeWebService;

@RunWith (MockitoJUnitRunner.class)
public class InjuredEmployeeWebServiceTest {

    private static final String EXPECTED_LASTNAME = "lastname";

    private static final String EXPECTED_FIRSTNAME = "firstname";

    private static final String EXPECTED_USERNAME = "username";

    @InjectMocks
    private InjuredEmployeeWebService target;

    @Mock
    private InjuredEmployeeService injuredEmployeeService;

    @Mock
    private HttpServletRequest request;

    @Test
    public void getEmployeeListTest() {
        Principal expectedPrincipal = mock(Principal.class);
        List<Employee> expectedEmployeeList = new ArrayList<Employee>();
        expectedEmployeeList.add(new Employee());
        when(
                injuredEmployeeService.getEmployeesWithFilters(
                        EXPECTED_FIRSTNAME, EXPECTED_LASTNAME, 1,
                        EXPECTED_USERNAME)).thenReturn(expectedEmployeeList);
        when(request.getUserPrincipal()).thenReturn(expectedPrincipal);
        when(expectedPrincipal.getName()).thenReturn(EXPECTED_USERNAME);
        List<Employee> actualEmployeeList = target.getEmployeeList(
                EXPECTED_FIRSTNAME, EXPECTED_LASTNAME, 1, request);
        assertEquals(expectedEmployeeList, actualEmployeeList);
    }
}
