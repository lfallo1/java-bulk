package com.catalyst.teammateria.injuryreport.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.model.Employee;

@RunWith (MockitoJUnitRunner.class)
public class InjuredEmployeeServiceImplTest {

    @InjectMocks
    private InjuredEmployeeServiceImpl target;

    @Mock
    EmployeeDao employeeDao;

    @Mock
    UserDao userDao;

    @Mock
    Employee employee;

    Integer voidInt;

    Integer testInt = 1;

    String voidString;

    String testString = "string";

    @Test
    public void makeSafeTests() {
        assertEquals(target.makeSafe(voidInt), "%");
        assertEquals(target.makeSafe(voidString), "%");
        assertEquals(target.makeSafe(testInt), testInt.toString());
        assertEquals(target.makeSafe(testString), "%string%");
    }

    @Test
    public void getEmployeesWithFiltersTest() {
        List<Employee> expectedList = new ArrayList<Employee>();
        Mockito.when(
                employeeDao.getEmployeesForInjuredEmployee(anyString(),
                        anyString(), anyString(), anyString())).thenReturn(
                expectedList);
        Mockito.when(userDao.getEmployeeIdByUsername(anyString()))
                .thenReturn(1);
        List<Employee> actualList = target.getEmployeesWithFilters("", "",
                null, "1");
        assertEquals(actualList, expectedList);
    }
}
