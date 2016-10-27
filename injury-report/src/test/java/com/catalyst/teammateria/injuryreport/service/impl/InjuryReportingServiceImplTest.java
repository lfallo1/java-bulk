package com.catalyst.teammateria.injuryreport.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.catalyst.teammateria.injuryreport.dao.BodyPartDao;
import com.catalyst.teammateria.injuryreport.dao.EmployeeDao;
import com.catalyst.teammateria.injuryreport.dao.InjuryTypeDao;
import com.catalyst.teammateria.injuryreport.dao.ReportDao;
import com.catalyst.teammateria.injuryreport.dao.UserDao;
import com.catalyst.teammateria.injuryreport.dao.WeatherDao;
import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;
import com.catalyst.teammateria.injuryreport.service.InjuryReportingService;

@RunWith (MockitoJUnitRunner.class)
public class InjuryReportingServiceImplTest {

    @InjectMocks
    private InjuryReportingServiceImpl target;

    @Mock
    BodyPartDao bodyPartDao;

    @Mock
    BodyPart bodyPart;

    @Mock
    InjuryTypeDao injuryTypeDao;

    @Mock
    InjuryType injuryType;

    @Mock
    WeatherDao weatherDao;

    @Mock
    Weather weather;

    @Mock
    Employee employee;

    @Mock
    EmployeeDao employeeDao;

    @Mock
    Report report;

    @Mock
    ReportDao reportDao;

    @Mock
    User user;

    @Mock
    UserDao userDao;

    @Mock
    private InjuryReportingService injuryReportingService;

    HttpServletRequest request;

    @Test
    public void getWeatherForSelectTest() {
        List<Weather> expectedList = new ArrayList<Weather>();
        Mockito.when(weatherDao.getAll()).thenReturn(expectedList);
        List<Weather> actualList = target.getWeatherForSelect();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getInjuryTypeForSelectTest() {
        List<InjuryType> expectedList = new ArrayList<InjuryType>();
        Mockito.when(injuryTypeDao.getAll()).thenReturn(expectedList);
        List<InjuryType> actualList = target.getInjuryTypeForSelect();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getBodyPartForSelectTest() {
        List<BodyPart> expectedList = new ArrayList<BodyPart>();
        Mockito.when(bodyPartDao.getAll()).thenReturn(expectedList);
        List<BodyPart> actualList = target.getBodyPartForSelect();
        assertEquals(actualList, expectedList);
    }
    
    @Test
    public void getUserDetails(){
        //Return the expected User and expected Employee from userDao
        User expectedUser = new User();
        Employee expectedEmployee1 = new Employee();
        expectedEmployee1.setEmployeeId(1);
        expectedUser.setEmployee(expectedEmployee1);
        //Return the expected Employee from employeeDao
        Employee expectedEmployee2 = new Employee();
        expectedEmployee2.setEmployeeId(1);
        expectedEmployee2.setFirstName("foo");
        expectedEmployee2.setLastName("bar");
        when(userDao.getUserByUsername("hello")).thenReturn(expectedUser);
        when(employeeDao.getEmployeeById(1)).thenReturn(expectedEmployee2);
        //Assert the employee was set to the user
        User actualUser = target.getUserDetails("hello");
        assertEquals(actualUser.getEmployee().getFirstName(), "foo" );
        assertEquals(actualUser.getEmployee().getLastName(), "bar" );
        
        
    }

    @Test
    public void addInjuryReportTest() {
        InjuryReportBean injuryReportBean = injuryReportBean();
        target.addInjuryReport(injuryReportBean);
        Mockito.verify(reportDao).addReport(injuryReportBean);
        
    }
    
    @Ignore
    private InjuryReportBean injuryReportBean() {
        InjuryReportBean reportBean = new InjuryReportBean();
        reportBean.setEmployeeId(1);
        reportBean.setWeatherId(2);
        reportBean.setBodyPartId(3);
        reportBean.setInjuryTypeId(2);
        reportBean.setTimeOfInjury("10:00");
        reportBean.setReporterId(5);
        reportBean.setDescription("TEST");
        reportBean.setReportDate("11/25/2014");
        reportBean.setReportManagementDate("11/25/2014");
        return reportBean;
    }
}
