package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;

import com.catalyst.teammateria.injuryreport.dao.jdbc.ReportDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.FullReportRowMapper;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.ReportEmployeeRowMapper;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.ReportRowMapper;
import com.catalyst.teammateria.injuryreport.formbeans.AdminProofingBean;
import com.catalyst.teammateria.injuryreport.formbeans.InjuryReportBean;
import com.catalyst.teammateria.injuryreport.model.BodyPart;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.InjuryType;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Report;
import com.catalyst.teammateria.injuryreport.model.Role;
import com.catalyst.teammateria.injuryreport.model.User;
import com.catalyst.teammateria.injuryreport.model.Weather;

public class ReportDaoJdbcTest {

    ReportDaoJdbc target;

    JdbcTemplate mockJdbcTemplate; 
    
    DataSource mockedDataSrc = Mockito.mock(DataSource.class);

    @Before
    public void setup() {
        target = new ReportDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockJdbcTemplate);
    }
    
    @Test
    public void coverDataSource() {
        target.setJdbcTemplate(mockedDataSrc);
    }

    @Test
    public void addReportTest() {
        Report report = defaultReport();
        Date date = null;
        Time time = null;
        Boolean bool = true;
        String str = "";
        InjuryType injuryType = defaultInjuryType();
        BodyPart bodypart = defaultBodyPart();
        Weather weather = defaultWeather();
        Employee employee = defaultEmployee();
        User user = defaultUser();
        target.addReport(report);
        verify(mockJdbcTemplate).update(anyString(),
                eq(employee.getEmployeeId()), eq(user.getUserId()),
                eq(weather.getWeatherId()), eq(bodypart.getBodyPartId()),
                eq(injuryType.getTypeId()), eq(user.getUserId()), eq(date),
                eq(date), eq(time), eq(str), eq(str), eq(date), eq(bool));
    }

    // addReport is an overloaded method
    @Test
    public void addReportTest2() {
        final InjuryReportBean injuryReportBean = new InjuryReportBean();//
        KeyHolder keyHolder = mock(KeyHolder.class);
        target.setKeyHolder(keyHolder);
        when(keyHolder.getKey()).thenReturn(1);
        Integer returnedId = target.addReport(injuryReportBean);
        assertTrue(returnedId == 1);
    }
    
    @Test
    public void getReportByIdTest() {
        Report expectedReport = defaultReport();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(ReportRowMapper.class))).thenReturn(
                expectedReport);
        Report actualReport = target.getReportById(1);
        assertEquals(actualReport, expectedReport);
    }

    @Test
    public void updateReportTest() {
        Report report = defaultReport();
        target.updateReport(report);
        verify(mockJdbcTemplate).update(anyString(), eq(2), eq(1), eq(1),
                eq(1), eq(1), eq(1), eq(null), eq(null), eq(null), eq(""),
                eq(""), eq(null), eq(true), eq(1));
    }

    @Test
    public void removeReportTest() {
        Report report = defaultReport();
        target.removeReport(report);
        verify(mockJdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        target.getAll();
        verify(mockJdbcTemplate, times(1)).query(anyString(),
                any(ReportRowMapper.class));
    }

    @Ignore
    public Position defaultPosition() {
        Position position = new Position();
        position.setPositionId(1);
        position.setPositionName("Management");
        return position;
    }

    @Ignore
    public Employee defaultEmployee() {
        Position defaultPosition = defaultPosition();
        Employee employee = new Employee();
        employee.setEmployeeId(2);
        employee.setFirstName("test");
        employee.setLastName("user1");
        employee.setPosition(defaultPosition);
        return employee;
    }

    @Ignore
    public Role defaultRole() {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("ROLE_ADMIN");
        return role;
    }

    @Ignore
    public User defaultUser() {
        Employee employee = defaultEmployee();
        Role role = defaultRole();
        User user = new User();
        user.setActive(true);
        user.setEmployee(employee);
        user.setRole(role);
        user.setUserId(1);
        user.setUserName("testUser1");
        user.setUserPass("Password1?");
        return user;
    }

    @Ignore
    public BodyPart defaultBodyPart() {
        BodyPart bodyPart = new BodyPart();
        bodyPart.setBodyPartId(1);
        bodyPart.setBodyPartName("Head");
        return bodyPart;
    }

    @Ignore
    public Weather defaultWeather() {
        Weather weather = new Weather();
        weather.setWeatherId(1);
        weather.setWeatherCondition("N/A");
        return weather;
    }

    @Ignore
    public InjuryType defaultInjuryType() {
        InjuryType injuryType = new InjuryType();
        injuryType.setTypeId(1);
        injuryType.setTypeName("Slip/Fall");
        return injuryType;
    }

    @Ignore
    public Report defaultReport() {
        Report report = new Report();
        Weather weather = defaultWeather();
        InjuryType injuryType = defaultInjuryType();
        Employee employee = defaultEmployee();
        BodyPart bodyPart = defaultBodyPart();
        User user = defaultUser();
        report.setApprover(user);
        report.setApproverComments("");
        report.setBodyPart(bodyPart);
        report.setDateOfReport(null);
        report.setDateReportedToManagement(null);
        report.setDateUpdated(null);
        report.setDescription("");
        report.setEmployee(employee);
        report.setEnabled(true);
        report.setInjuryType(injuryType);
        report.setReporter(user);
        report.setReportId(1);
        report.setTimeOfInjury(null);
        report.setWeather(weather);
        return report;
    }

    @Test
    public void getFiltered() {
        target.getFilterableReports("", "", "", "", true);
        verify(mockJdbcTemplate, times(1)).query(anyString(),
                any(Object[].class), any(ReportRowMapper.class));
    }

    @Test
    public void getEmployee() {
        target.getAllWithEmployee();
        verify(mockJdbcTemplate, times(1)).query(anyString(),
                any(ReportEmployeeRowMapper.class));
    }

    @Test
    public void getFullReport() {
        target.getFullReportById(0);
        verify(mockJdbcTemplate, times(1)).queryForObject(anyString(),
                any(Object[].class), any(FullReportRowMapper.class));
    }

    @Test
    public void update() {
        target.updateReport(0, new AdminProofingBean(), 0);
        verify(mockJdbcTemplate, times(1)).update(anyString(), anyInt(),
                anyInt(), anyInt(), anyInt(), anyString(), anyString(),
                anyString(), anyString(), anyString(),
                any(java.sql.Date.class), anyInt());
    }
}
