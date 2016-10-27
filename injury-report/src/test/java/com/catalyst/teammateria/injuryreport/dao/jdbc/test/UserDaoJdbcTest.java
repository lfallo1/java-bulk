package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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

import com.catalyst.teammateria.injuryreport.dao.jdbc.UserDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.UserBoolean;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.UserRowMapper;
import com.catalyst.teammateria.injuryreport.model.Employee;
import com.catalyst.teammateria.injuryreport.model.Position;
import com.catalyst.teammateria.injuryreport.model.Role;
import com.catalyst.teammateria.injuryreport.model.User;

public class UserDaoJdbcTest {

    UserDaoJdbc target;

    JdbcTemplate mockJdbcTemplate;

    DataSource mockedDataSrc = Mockito.mock(DataSource.class);

    @Before
    public void setup() {
        target = new UserDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockedDataSrc);
        target.setJdbcTemplate(mockJdbcTemplate);
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

    @Test
    public void addUserTest() {
        User user = defaultUser();
        target.addUser(user);
        verify(mockJdbcTemplate).update(anyString(), eq("testUser1"),
                eq("Password1?"), eq(1), eq(2), eq(true));
    }

    @Test
    public void getUserByIdTest() {
        User expectedUser = defaultUser();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(UserRowMapper.class))).thenReturn(
                expectedUser);
        User actualUser = target.getUserById(1);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void updateUserTest() {
        User user = defaultUser();
        target.updateUser(user);
        verify(mockJdbcTemplate).update(anyString(), eq("testUser1"),
                eq("Password1?"), eq(1), eq(2), eq(true));
    }

    @Test
    public void removeUserTest() {
        User user = defaultUser();
        target.removeUser(user);
        verify(mockJdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        List<User> expectedList = new ArrayList<User>();
        Mockito.when(
                mockJdbcTemplate.query(anyString(), any(UserRowMapper.class)))
                .thenReturn(expectedList);
        List<User> actualList = target.getAll();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void getUserByEmployeeIdTest() {
        User expectedUser = new User();
        expectedUser.setUserId(1);
        when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(UserRowMapper.class))).thenReturn(
                expectedUser);
        User actualUser = target.getUserByEmployeeId(1);
        assertEquals(actualUser, expectedUser);
    }

    @Test
    public void isAlreadyAUserTest() {
        when(
                mockJdbcTemplate.queryForObject(anyString(),
                        new Object[] {any(Integer.class)},
                        Mockito.any(UserBoolean.class))).thenReturn(true);
        assertTrue(target.isAlreadyAUser(1));
        when(
                mockJdbcTemplate.queryForObject(anyString(),
                        new Object[] {any(Integer.class)},
                        Mockito.any(UserBoolean.class))).thenReturn(false);
        assertFalse(target.isAlreadyAUser(1));
    }

    @Test
    public void userNameTaken() {
        when(
                mockJdbcTemplate.queryForObject(anyString(),
                        new Object[] {any(String.class)},
                        any(UserBoolean.class))).thenReturn(true);
        assertTrue(target.userNameTaken("hello"));
        when(
                mockJdbcTemplate.queryForObject(anyString(),
                        new Object[] {any(String.class)},
                        any(UserBoolean.class))).thenReturn(false);
        assertFalse(target.userNameTaken("hello"));
    }

    @Test
    public void getUserByUsernameTest() {
        User expectedUser = new User();
        expectedUser.setUserId(1);
        when(
                mockJdbcTemplate.queryForObject(anyString(),
                        new Object[] {any(String.class)},
                        any(UserRowMapper.class))).thenReturn(expectedUser);
        User actualUser = target.getUserByUsername("helo");
        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getEmployeeIDByUsernameTest() {
        target.getEmployeeIdByUsername("");
        Mockito.verify(mockJdbcTemplate).queryForObject(anyString(),
                eq(Integer.class), anyString());
    }
}
