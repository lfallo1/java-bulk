package com.catalyst.teammateria.injuryreport.dao.jdbc.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import com.catalyst.teammateria.injuryreport.dao.jdbc.RoleDaoJdbc;
import com.catalyst.teammateria.injuryreport.dao.jdbc.mappers.RoleRowMapper;
import com.catalyst.teammateria.injuryreport.model.Role;

public class RoleDaoJdbcTest {

    RoleDaoJdbc  target;
    JdbcTemplate mockJdbcTemplate;
    DataSource   mockedDataSrc = Mockito.mock(DataSource.class);

    @Before
    public void setup() {
        target = new RoleDaoJdbc();
        this.mockJdbcTemplate = mock(JdbcTemplate.class);
        target.setJdbcTemplate(mockedDataSrc);
        target.setJdbcTemplate(mockJdbcTemplate);
    }

    @Ignore
    public Role defaultRole() {
        Role role = new Role();
        role.setRoleId(1);
        role.setRoleName("ROLE_ADMIN");
        return role;
    }

    @Test
    public void addRoleTest() {
        Role role = defaultRole();
        target.addRole(role);
        verify(mockJdbcTemplate).update(anyString(), eq("ROLE_ADMIN"));
    }

    @Test
    public void getRoleByIdTest() {
        Role expectedRole = defaultRole();
        Mockito.when(
                mockJdbcTemplate.queryForObject(Mockito.anyString(),
                        new Object[] {Mockito.any(Integer.class)},
                        Mockito.any(RoleRowMapper.class))).thenReturn(
                expectedRole);
        Role actualRole = target.getRoleById(1);
        assertEquals(actualRole, expectedRole);
    }

    @Test
    public void updateRoleTest() {
        Role role = defaultRole();
        target.updateRole(role);
        verify(mockJdbcTemplate).update(anyString(), eq("ROLE_ADMIN"), eq(1));
    }

    @Test
    public void removeRoleTest() {
        Role role = defaultRole();
        target.removeRole(role);
        verify(mockJdbcTemplate).update(anyString(), eq(1));
    }

    @Test
    public void getAllTest() {
        List<Role> expectedList = new ArrayList<Role>();
        Mockito.when(
                mockJdbcTemplate.query(anyString(),
                        any(RoleRowMapper.class))).thenReturn(
                expectedList);
        List<Role> actualList = target.getAll();
        assertEquals(actualList, expectedList);
    }
}
