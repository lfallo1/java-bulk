package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import com.catalyst.teammateria.injuryreport.dao.jdbc.EmployeeDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.EmployeeRowMapper;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Position;

public class EmployeeDaoJdbcTest {

    EmployeeDaoJdbc target;

    JdbcTemplate jdbcTemplate;

    @Before
    public void setup() {
        target = new EmployeeDaoJdbc();
        this.jdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(jdbcTemplate);
    }

    @Test
    public void coverDataSource() {
        DataSource mockedDataSrc = Mockito.mock(DataSource.class);
        target.setJdbcTemplate(mockedDataSrc);
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
        Employee employee = new Employee();
        Position position = defaultPosition();
        employee.setEmployeeId(1);
        employee.setFirstName("Jerk");
        employee.setLastName("Face");
        employee.setPosition(position);
        return employee;
    }

    @Test
    public void addEmployeeTest() {
        Employee employee = defaultEmployee();
        Position position = defaultPosition();
        target.addEmployee(employee);
        verify(jdbcTemplate).update(anyString(), eq("Jerk"), eq("Face"),
                eq(position.getPositionId()));
    }

    @Test
    public void getEmployeeByIdTest() {
        Employee expectedEmployee = defaultEmployee();
        Mockito.when(
                jdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(EmployeeRowMapper.class))).thenReturn(
                expectedEmployee);
        Employee actualEmployee = target.getEmployeeById(1);
        assertEquals(actualEmployee, expectedEmployee);
    }

    @Test
    public void updateEmployeeTest() {
        Employee employee = defaultEmployee();
        Position position = defaultPosition();
        target.updateEmployee(employee);
        verify(jdbcTemplate).update(anyString(), eq("Jerk"), eq("Face"),
                eq(position.getPositionId()), eq(1));
    }

    @Test
    public void removeEmployeeTest() {
        Employee employee = defaultEmployee();
        target.removeEmployee(employee);
        verify(jdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        List<Employee> expectedList = new ArrayList<Employee>();
        expectedList.add(new Employee());
        Mockito.when(
                jdbcTemplate.query(anyString(), any(EmployeeRowMapper.class)))
                .thenReturn(expectedList);
        List<Employee> actualList = target.getAll();
        assertEquals(actualList, expectedList);
    }

    @Test
    public void getEmployeesForInjuredEmployeeTest() {
        List<Employee> expectedList = new ArrayList<Employee>();
        expectedList.add(new Employee());
        when(
                jdbcTemplate.query(anyString(), any(Object[].class),
                        any(EmployeeRowMapper.class))).thenReturn(expectedList);
        List<Employee> actualList = target.getEmployeesForInjuredEmployee(
                "firstname", "lastname", "1", "1");
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getAdminToolsEmployeeListWithFiltersTest() {
        List<Employee> expectedList = new ArrayList<Employee>();
        expectedList.add(new Employee());
        when(
                jdbcTemplate.query(anyString(), any(Object[].class),
                        any(EmployeeRowMapper.class))).thenReturn(expectedList);
        List<Employee> actualList = target
                .getAdminToolsEmployeeListWithFilters("foo", "bar", "1", "2");
        assertEquals(expectedList, actualList);
    }
}
